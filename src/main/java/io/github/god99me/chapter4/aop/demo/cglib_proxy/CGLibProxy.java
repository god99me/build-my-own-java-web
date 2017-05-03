package io.github.god99me.chapter4.aop.demo.cglib_proxy;

import io.github.god99me.chapter4.aop.demo.jdk_proxy.Hello;
import io.github.god99me.chapter4.aop.demo.jdk_proxy.HelloImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class CGLibProxy implements MethodInterceptor {
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // methodProxy : CGLib provide method-level proxy
        before();
        Object result = methodProxy.invokeSuper(o, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("Before call cglib proxy");
    }

    private void after() {
        System.out.println("After call cglib proxy");
    }

    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("Chen YY");
    }
}
