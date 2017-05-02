package io.github.god99me.chapter3.helper;


import io.github.god99me.chapter2.util.PropsUtil;
import io.github.god99me.chapter3.ConfigConstant;

import java.util.Properties;

/**
 * Created by L.M.Y on 2017/5/2.
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadPros(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDirver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUsername() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    public static String getAppAssetPath() {
        // 可选配置项，如果文件中没有配置则采用默认值
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}
