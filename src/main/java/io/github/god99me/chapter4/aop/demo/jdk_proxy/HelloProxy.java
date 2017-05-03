package io.github.god99me.chapter4.aop.demo.jdk_proxy;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class HelloProxy implements Hello {

    private Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void after() {
        System.out.println("After call method");
    }

    private void before() {
        System.out.println("Before call method");
    }

    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.say("CYY");
    }
}
