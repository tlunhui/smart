package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class ClassHelper {
    static Set<Class<?>> classSet;

    static {
        String appBasePackage = ConfigConstant.APP_BASE_PACKAGE;
        classSet = ClassUtil.getClassSet(appBasePackage);
    }

    public static Set<Class<?>> getClasses() {
        return classSet;
    }

    public static Set<Class<?>> getServiceClassSet() {
        return getAnnotationPresent(classSet,Service.class);
    }

    public static Set<Class<?>> getControllerClassSet(){
        return getAnnotationPresent(classSet, Controller.class);
    }

    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet=new HashSet<>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }

    private static Set<Class<?>> getAnnotationPresent(Set<Class<?>> classSet, Class<? extends Annotation> controllerClass) {
        for (Class<?> cls : classSet) {
            if (cls.isAnnotationPresent(controllerClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
