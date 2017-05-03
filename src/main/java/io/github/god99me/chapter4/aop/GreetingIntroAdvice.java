package io.github.god99me.chapter4.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by L.M.Y on 2017/5/3.
 */
/*
 * 对方法的增强叫 Weaving 织入
 * 对类的增强叫 Introduction advice 引入增强，它是对类的增强
 * 扩展了 DelegatingIntroductionInterceptor 类，实现了新定义的接口 Apology
 * 用这个增强类去丰富 GreetingImpl 类的功能， 则无需使 GreetingImpl 实现 Apology 接口
 */

@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}
