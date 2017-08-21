package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class IocHelper {

    static Logger LOGGER= LoggerFactory.getLogger(IocHelper.class);

    static {
        Map<Class<?>, Object> classMap = BeanHelper.GetBeanMap();
        if (classMap==null||classMap.size()==0){
            LOGGER.error("map no date");
        }
        for (Map.Entry<Class<?>, Object> cls : classMap.entrySet()) {
            Class<?> beanClass=cls.getKey();
            Object bean=cls.getValue();
            for (Field field:beanClass.getFields()){

            }
        }
    }
}
