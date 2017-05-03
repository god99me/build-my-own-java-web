package io.github.god99me.chapter4.aop.demo.jdk_proxy;


/**
 * Created by L.M.Y on 2017/5/3.
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello, " + name);
    }
}
