package io.github.god99me.chapter4.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class ProxyClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Greeting helloProxy = (Greeting) context.getBean("greetingProxy");
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy"); // 强制转换为实现类而不是接口
//        helloProxy.sayHello("Spring xml");
        greetingImpl.sayHello("CYY");

        greetingImpl.goodMorning("CYY");
        greetingImpl.goodNight("CYY");
//        Apology apology = (Apology) greetingImpl; // 将目标类强制向上转型为 Apology 接口
//        apology.saySorry("CYY");
    }
}
