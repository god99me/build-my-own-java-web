package io.github.god99me.chapter4.aop;

import org.springframework.stereotype.Component;

/**
 * Created by L.M.Y on 2017/5/3.
 */
@Component
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);

        // dummy throw
//        throw new RuntimeException("error");
    }

    public void goodMorning(String name) {
        System.out.println("Good Morning! " + name);
    }

    public void goodNight(String name) {
        System.out.println("Good Night!" + name);
    }
}
