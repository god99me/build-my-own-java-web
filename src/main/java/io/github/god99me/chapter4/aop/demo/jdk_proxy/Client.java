package io.github.god99me.chapter4.aop.demo.jdk_proxy;


import io.github.god99me.chapter4.aop.GreetingAfterAdvice;
import io.github.god99me.chapter4.aop.GreetingAroundAdvice;
import io.github.god99me.chapter4.aop.GreetingBeforeAdvice;
import io.github.god99me.chapter4.aop.GreetingBeforeAndAfterAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class Client {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImpl());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        Hello helloProxy = (Hello) proxyFactory.getProxy();
        helloProxy.say("Spring aop");

        System.out.println();

        ProxyFactory proxyFactory1 = new ProxyFactory();
        proxyFactory1.setTarget(new HelloImpl());
        proxyFactory1.addAdvice(new GreetingBeforeAndAfterAdvice());

        Hello helloProxy1 = (Hello) proxyFactory1.getProxy();
        helloProxy1.say("before and after advice");

        System.out.println();
        ProxyFactory proxyFactory2 = new ProxyFactory();
        proxyFactory2.setTarget(new HelloImpl());
        proxyFactory2.addAdvice(new GreetingAroundAdvice());

        Hello helloProxy2 = (Hello) proxyFactory2.getProxy();
        helloProxy2.say("aop alliance");
    }
}
