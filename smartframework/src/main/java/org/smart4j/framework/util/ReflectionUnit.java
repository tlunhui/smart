package org.smart4j.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.relation.RelationNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUnit {
    private static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUnit.class);

     public static  <T> T newInstance(Class<T> cls)
             throws IllegalAccessException, InstantiationException,RuntimeException {
        T t=cls.newInstance();
        return t;
    }

    public static Object invokMethod(Object obj, Method method, Object... arg)
            throws InvocationTargetException, IllegalAccessException ,RuntimeException{
         Object result;
        method.setAccessible(true);
        result= method.invoke(obj,arg);
        return result;
    }

    public static  void setField(Object obj, Field field,Object value) throws IllegalAccessException ,RuntimeException{
         field.setAccessible(true);
         field.set(obj,value);
    }
}
