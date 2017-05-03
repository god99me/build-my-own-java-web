package io.github.god99me.chapter3.helper;

import io.github.god99me.chapter2.util.CollectionUtil;
import io.github.god99me.chapter3.annotation.Action;
import io.github.god99me.chapter3.bean.Handler;
import io.github.god99me.chapter3.bean.Request;
import io.github.god99me.chapter3.util.ArrayUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            // lint url rules

                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMthod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMthod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMthod, String requestPath) {
        Request request = new Request(requestMthod, requestPath);
        return ACTION_MAP.get(request);
    }
}
