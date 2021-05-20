package com.company.ioc;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // Base 64 encoding

        // url encoding

//        Encoder encoder = new Encoder();

//        Encoder encoder = new Encoder(new UrlEncoder());
        Encoder encoder = new Encoder(new Base64Encoder());
//         위와 같이 해야 클래스 내부를 건드리지않고 의존성으로 바꿈으로
//           제일 바람직한 방법이다.

//        IEncoder encoder = new Base64Encoder();
        String result = encoder.encode(url);

//        UrlEncoder urlEncoder = new UrlEncoder();
//        IEncoder urlEncoder = new UrlEncoder();
//        String urlResult = urlEncoder.encode(url);

        System.out.println(result);
//        System.out.println(urlResult);
    }
}
