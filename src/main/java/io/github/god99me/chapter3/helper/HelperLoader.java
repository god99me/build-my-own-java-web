package io.github.god99me.chapter3.helper;


import io.github.god99me.chapter3.util.ClassUtil;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }

    }
}
