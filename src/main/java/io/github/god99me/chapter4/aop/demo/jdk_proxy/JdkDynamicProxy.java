package io.github.god99me.chapter4.aop.demo.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class JdkDynamicProxy implements InvocationHandler {
    private Object proxied;

    public JdkDynamicProxy(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(proxied, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("After call jdk dynamic proxy");
    }

    private void before() {
        System.out.println("Before call jdk dynamic proxy");
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                proxied.getClass().getClassLoader(),
                proxied.getClass().getInterfaces(),
                this
        );
    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        JdkDynamicProxy proxy = new JdkDynamicProxy(hello);

        // helloProxy 既不是 hello 也不是 proxy 而是它们"织入"后的结果
        Hello helloProxy = (Hello) Proxy.newProxyInstance(
            hello.getClass().getClassLoader(),
            hello.getClass().getInterfaces(),
            proxy
        );

        helloProxy.say("CYY");
        System.out.println();

        // Pro
        JdkDynamicProxy proxyPro = new JdkDynamicProxy(
            new HelloImpl()
        );

        Hello helloProxyPro = proxyPro.getProxy();
        helloProxyPro.say("CYY");
        System.out.println();

        System.out.println("toString() 方法同样被动态代理了");
        helloProxy.toString();
    }
}
