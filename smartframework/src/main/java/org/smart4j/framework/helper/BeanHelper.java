package org.smart4j.framework.helper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.ClassUtil;
import org.smart4j.framework.util.ReflectionUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {
    static Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
    static Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : classSet) {
            try {
                Object obj = ReflectionUnit.newInstance(cls);
                BEAN_MAP.put(cls, obj);
            } catch (IllegalAccessException e) {
                LOGGER.error("IllegalAccessException" + e);
                e.printStackTrace();
            } catch (InstantiationException e) {
                LOGGER.error("InstantiationException" + e);
                e.printStackTrace();
            }
        }
    }

    public static Map<Class<?>, Object> GetBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class"+cls);
        }
        return (T)BEAN_MAP.get(cls);
    }

}
