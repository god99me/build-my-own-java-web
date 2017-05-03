package io.github.god99me.chapter3.helper;

import io.github.god99me.chapter2.util.CollectionUtil;
import io.github.god99me.chapter3.annotation.Inject;
import io.github.god99me.chapter3.util.ArrayUtil;
import io.github.god99me.chapter3.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by L.M.Y on 2017/5/3.
 */

/*
 * 所有的对象都是单例的
 * 在静态块中实现相关逻辑，当该类被加载时就会管理所有对象
 */
public class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                Field[] beanFields = beanClass.getDeclaredFields();

                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
