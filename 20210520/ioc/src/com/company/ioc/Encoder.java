package com.company.ioc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder){
//        this.iEncoder = new Base64Encoder();
//        this.iEncoder = new UrlEncoder();

        // 매번 Base64Encoder 를 쓸지 UrlEncoder 를 쓸지 결정해줘야하므로
        // 클래스내부의 본체를 바꿈으로써 매우매우 비효율적이다.
        // 따라서 의존성을 받을수 있게 하기위해서 Interface 를 파라미터에 넣는다.

        this.iEncoder = iEncoder;

    }



    public String encode(String message){
        return iEncoder.encode(message);
    }

}
