package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
public class Encoder {

    private IEncoder iEncoder;

    // Base64Encoder 를 매칭할지
    // UrlEncoder 를 매칭할지
    // 모른다. 그래서 @Qualifier 를 해준다

    public Encoder(/*@Qualifier("base74Encoder")*/ IEncoder iEncoder){
//        this.iEncoder = new Base64Encoder();
//        this.iEncoder = new UrlEncoder();

        // 매번 Base64Encoder 를 쓸지 UrlEncoder 를 쓸지 결정해줘야하므로
        // 클래스내부의 본체를 바꿈으로써 매우매우 비효율적이다.
        // 따라서 의존성을 받을수 있게 하기위해서 Interface 를 파라미터에 넣는다.

        this.iEncoder = iEncoder;

    }

    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }


    public String encode(String message){
        return iEncoder.encode(message);
    }

}


