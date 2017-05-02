package io.github.god99me.chapter3.helper;

import io.github.god99me.chapter3.annotation.Controller;
import io.github.god99me.chapter3.annotation.Service;
import io.github.god99me.chapter3.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by L.M.Y on 2017/5/2.
 */
public final class ClassHelper {

    // 用于存放所加载的类
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    // 获取应用包名下的所有类
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    // 获取应用包名下的所有 Service 类
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    // 获取应用包下的所有 Bean 类，包括 Service, Controller 等
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

}
