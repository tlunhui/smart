package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropUtil;

import java.util.Properties;

public class ConfigHelper {
    private static final Properties ConfigProperties = PropUtil.LoadFile(ConfigConstant.CONFIG_FILE);

    public static String GetJdbcDriver() throws Exception {
        return PropUtil.GetString(ConfigProperties, ConfigConstant.JDBC_DRIVER);
    }

    public static String GetJdbcUrl() throws Exception {
        return PropUtil.GetString(ConfigProperties, ConfigConstant.JDBC_URL);
    }

    public static String GetJdbcUserName() throws Exception {
        return PropUtil.GetString(ConfigProperties, ConfigConstant.JDBC_USERNAME);
    }

    public static String GetJdbcPassword() throws Exception {
        return PropUtil.GetString(ConfigProperties,ConfigConstant.JDBC_PASSWORD);
    }
}
