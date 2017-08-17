package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /*
    * 获取类加载器
    * */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 通过类全名加载类
     * */
    public static Class<?> loadClass(String className, boolean isInitialed) {
        Class<?> obj = null;
        try {
            obj = Class.forName(className, isInitialed, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error(className + "load class failed" + e);
            throw new RuntimeException(e);
        }catch (NoClassDefFoundError  e0){
            LOGGER.error(className + "load class failed" + e0);
            throw new RuntimeException(e0);
        }
        return obj;
    }


    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> set = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urlList = Thread.currentThread().getContextClassLoader().getResources(packageName.replace('.', '/'));
            while (urlList.hasMoreElements()) {
                URL url = urlList.nextElement();
                if (url == null) {
                    continue;
                }
                String protocol = url.getProtocol();
                if (protocol.equals("jar")) {
                    JarURLConnection jarURLConnection= (JarURLConnection) url.openConnection();
                    if (jarURLConnection==null){
                        return set;
                    }
                    JarFile jarFile= jarURLConnection.getJarFile();
                    Enumeration<JarEntry> jarEntries=jarFile.entries();
                    while (jarEntries.hasMoreElements()){
                        JarEntry jarEntry=jarEntries.nextElement();
                        String jarName=jarEntry.getName();
                        if (jarName.endsWith(".class")){
                            String className=jarName.substring(0,jarName.lastIndexOf('.')).replace('/','.');
                            doAddClass(set,className);
                        }
                    }
                } else {
                    String packagePath = url.getPath().replace("%20", " ");
                    addClass(set, packagePath, packageName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    private static void addClass(Set<Class<?>> set, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(file -> {
            return (file.isFile() && file.getName().endsWith("class") || file.isDirectory());
        });
        for (File file : files) {
            String fileName=file.getName();
            if (file.isFile()) {
                String className= fileName.substring(0,fileName.lastIndexOf('.'));
                doAddClass(set,packageName+"."+className);
            } else {
                 String subPackagePath=fileName;
                 if (StringUtils.isNotEmpty(packagePath)){
                     packagePath+="/"+subPackagePath;
                 }
                 String subPackageName=fileName;
                 if (StringUtils.isNotEmpty(packageName)){
                     packageName+="."+subPackageName;
                 }
                 addClass(set,packagePath,packageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> set, String className){
        set.add(loadClass(className,false));
    }
}
