package io.github.god99me.chapter3.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] objs) {
        return !ArrayUtil.isEmpty(objs);
    }

    public static boolean isEmpty(Object[] objs) {
        return ArrayUtils.isEmpty(objs);
    }
}
