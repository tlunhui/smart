package org.smart4j.framework.util;

public class CastUtil {

    public static String CastString(Object obj, String defaultValue) {
        return obj!=null?String.valueOf(obj):defaultValue;
    }

    public static String CastString(Object obj){
        return CastString(obj,"");
    }

    public static int CastInt(Object obj,int defaultValue){
        return 0;
    }

    public static int CastInt(Object obj){
        return CastInt(obj,0);
    }

    public static boolean CastBoolean(Object obj,boolean defaultValue){
        return true;
    }
    public static boolean CastBoolean(Object obj){
        return CastBoolean(obj,true);
    }

}
