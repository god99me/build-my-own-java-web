package io.github.god99me.chapter4.aop.aspectJ;

import org.springframework.stereotype.Component;

/**
 * Created by L.M.Y on 2017/5/4.
 */
@Aspect
@Component
public class GreetingAspect {

    @Around("execution(* aop.demo.GreetingImpl.*(..))")
    public Object around(ProceedingJointPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void before() {
        System.out.println();
        System.out.println("Before aspectJ");
    }

    private void after() {
        System.out.println("After aspectJ");
        System.out.println();
    }
}
