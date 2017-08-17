package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {

    private static Logger logger = LoggerFactory.getLogger(PropUtil.class);

    public static Properties LoadFile(String filePath) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
            if (is == null) {
                throw new FileNotFoundException(filePath + "file is not fount");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            logger.error("loda properties file failed" + e);
        }
        return props;
    }

    public static String GetString(Properties props, String key) throws Exception {
        if (props != null && props.contains(key)) {
            return props.getProperty(key);
        }
        throw new Exception(key + "not found in properties");
    }

    public static int GetInt(Properties props, String key) throws Exception {
        String stringValue = GetString(props, key);
        return CastUtil.CastInt(stringValue);
    }

    public static double GetDouble(Properties props, String key) throws Exception {
        String stringValue = GetString(props, key);
        return CastUtil.CastDouble(stringValue);
    }

    public static boolean GetBoolean(Properties props, String key) throws Exception {
        String stringValue = GetString(props, key);
        return CastUtil.CastBoolean(stringValue);
    }
}
