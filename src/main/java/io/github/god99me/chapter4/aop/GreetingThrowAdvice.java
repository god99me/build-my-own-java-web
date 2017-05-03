package io.github.god99me.chapter4.aop;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by L.M.Y on 2017/5/3.
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice{

    // not override
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("----------Throw exception--------");
        System.out.println("Target class:" + target.getClass().getName());
        System.out.println("Method name:"  + method.getName());
        System.out.println("Exception message:" + e.getMessage());
        System.out.println("--------------------------------");
    }
}
