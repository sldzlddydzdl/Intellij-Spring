package com.example.springioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder") // bean 으로 등록된다.
//@Component 를 쓰면 이 클래스는 Spring 이 관리를 해준다!
//@Qualifier 로 부를때 클래스 앞에는 소문자로 해서 호출해야하고
// 따로 별칭을 주고싶으면 @Component("base74Encoder") 이렇게
// 따로 별칭을 줘도된다.
public class Base64Encoder implements IEncoder {

    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

}
