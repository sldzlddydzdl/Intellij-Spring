package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    // @Pointcut // 굉장히 많은 연산 수식들이 있다. 인터넷에 찾아서 더해보길
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    // com.example.aop.controller 하위에있는
    // 모든 메소드를 다 aop 로 보겠다는 문구
    private void cut(){}

    // @Pointcut 으로 Decode 로써 동작을 할 수 있도록 annotation 에 대한 제약 걸기
    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {

        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
                // arg 돌다가 내가 원하는 User 라는 클래스가 매칭이되면
                if(arg instanceof User) {
                    // User 라는 클래스로 형변환 시켜라
                    User user = User.class.cast(arg);
                    // 기존의 encoding 되어있던 email 을 꺼낸다.
                    String base64Email = user.getEmail();
                    // 애를 다시 decoding 을 해서 다시 set 을 해준다.
                    String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                    user.setEmail(email);

                    // 위를 거치면 실질적인 controller 코드는 User 라는 객체르 decode 할 일이 없게 된다.
                }
        }

    }

    @AfterReturning(value = "cut() && enableDecode()" , returning =  "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        // Decode 와 반대로 한다.
        if(returnObj instanceof User){
            // Object 에서 User 를 찾아가지고
            User user = User.class.cast(returnObj);
            // 평문 이메일을 다시
            String email = user.getEmail();
            // 인코딩을 해줘서 다시 set 을 해주고 리턴을 합니다.
            String base64Email =  Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);

        }
    }


}
