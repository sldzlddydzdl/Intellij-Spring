package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
// @Bean 은 클래스에 붙일수가 없다.
// @Bean 은 method 에 적용시킬수 잇는 Annotation 이다.
public class TimerAop {

    // @Pointcut // 굉장히 많은 연산 수식들이 있다. 인터넷에 찾아서 더해보길
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    // com.example.aop.controller 하위에있는
    // 모든 메소드를 다 aop 로 보겠다는 문구
    private void cut(){}

    // @Pointcut 으로 Timer 로써 동작을 할 수 있도록 annotation 에 대한 제약 걸기
    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer(){}

//    @before , @AfterReturning 을 쓰게되면 타임을 공유 할 수가 없다
//    이때 쓸수 있는게 @Around 가 있다.

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        // 함수실행전 시간
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 함수실행
        Object result = joinPoint.proceed();
        // joinPoint.proceed() 를 하게되면 실질적으로 메소드가 실행되는거다.
        // 만약에 실행이되서 return 타입이있으면 Object 로 리턴을 받게된다.

        // 끝나고 나면 시간을 젠다
        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
        // 걸리는데 지난 시간을 찍어보자
    }
}

