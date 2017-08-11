package org.smart4j.framework.util;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.rmi.ServerException;

public class CastUtil {

    public static String CastString(Object obj, String defaultValue) throws ServerException {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new ServerException(obj + "不是一个字符串");
    }

    public static String CastString(Object obj) throws ServerException {
        return CastString(obj, "");
    }

    public static int CastInt(Object obj, int defaultValue) throws ServerException {
        return (int) CastDouble(obj);
    }

    public static int CastInt(Object obj) throws ServerException {
        return CastInt(obj, 0);
    }

    public static boolean CastBoolean(Object obj, boolean defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (!(obj instanceof String)) {
            return defaultValue;
        }
        return BooleanUtils.toBoolean((String)obj);
    }

    public static boolean CastBoolean(Object obj) {
        return CastBoolean(obj, false);
    }

    public static double CastDouble(Object obj) throws ServerException {
        return CastDouble(obj,0.0);
    }

    private static double CastDouble(Object obj, double defaultValue) throws ServerException {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            String value = (String) obj;
            if (value.length() < 1) {
                return defaultValue;
            }
            if (!StringUtils.isNumeric(value)) {
                return defaultValue;
            }
            return NumberUtils.toInt(value);
        }
        throw new ServerException(obj + "不是以个数字");
    }
}
