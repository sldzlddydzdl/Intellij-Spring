package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ForkJoinTask;

@Aspect
@Component
public class ParameterAop {


    // @Pointcut // 굉장히 많은 연산 수식들이 있다. 인터넷에 찾아서 더해보길
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
                // com.example.aop.controller 하위에있는
                // 모든 메소드를 다 aop 로 보겠다는 문구
    private void cut(){}

    @Before("cut()")
    // 언제 실행을 시킬거냐 위의 메소드이름 cut() 을 넣어준다.
    // cut 이 실행되는지점 Pointcut 이 실행되는 지점에
    // before 때 해당메소드를 실행 시키겠다.
    public void before(JoinPoint joinPoint){
                    // 들어가는 지점에 대한 정보들을 가진 객체가 있다.
                    // JoinPoint

        // 함수명을 알고 싶을때
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        // 메소드에 들어가고있는 agrs 들 즉 매개변수들의 배열이다.
        for(Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()" , returning =  "returnObj")
    // 반환 값을 알고 싶을때 반환 값을 어디서 할거냐
    // returning 에 내가 받고싶음 Object 이름을 넣어준다
    // @AfterReturning 은 Object 도 받을 수 있다.
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj");
        System.out.println(returnObj);
    }

    // com.example.aop.controller 하위에 있는 메소드들이 실행되기전에
    // before 메소드가 먼저 호출될거고
    // 정상 실행이되고 리턴을 해주게 되면 해당 Object 값을 볼수가 있다.

}
