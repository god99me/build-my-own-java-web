package io.github.god99me.chapter2.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by L.M.Y on 2017/4/28.
 */

// fake String tools class
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }

        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String[] splitString(String source, String delimiter) {
        // TODO: 2017/5/3 dummy implemented
        return source.split(delimiter);
    }
}
