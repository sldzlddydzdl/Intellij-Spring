package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication // Spring boot 로 동작하게된다
public class SpringIocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIocApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();
        // 지금은 예제라서 Spring context 를 선언을 했지만.
        // 실질적으로 아까 주입받기위해서는 생성자 라던지 또는 set method 또는 변수에다가
        // 오토 와이어드 라던지 인젝트라던지 이런 Annotation 통해가지고 직접
        // 객체를 받아올 것이다.

        // Spring 에서 직접 객체를 관리하는걸 Bean 이라고 하고
        // 이런 Bean 들이 관리되고 있는 장소가 Spring Container 이고
        // Spring container 가 이런 제어하는 권한을 다 가져갔기 때문에
        // 제어의 역전인거고 이것이 IoC 인것이다.

        // 결론은 Spring container 관리하는거 IoC
        // 나는 주입을 받으니까 DI 이것이 바로 Spring 의 핵심기술중의 하나이다.

//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

//        Encoder encoder = new Encoder(base64Encoder);
        Encoder encoder = context.getBean("urlEncode", Encoder.class);
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";


        String result = encoder.encode(url);
        System.out.println(result);

//        encoder.setIEncoder(urlEncoder);
//        result = encoder.encode(url);
//        System.out.println(result);

    }

}

// spring container 에서 따로관리하는 객체들을 Bean 이라고 부른다.
// 그래서 앞으로는 특정 클래스를 new 로 생성한 객체가 Bean 이다 라고 생각해라.


// 위의 방법은 하나의 Bean 만 사용하는거여서 한개의 클래스 내에서
// 여러개의 Bean 을 등록하고싶을땐 아래와 같이 하면된다.
//
@Configuration // 한개의 클래스에서 여러개의 Bean 을 등록할때 쓰는 Annotation
class AppConfig{

    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode") // 기존 UrlEncoder 클래스 Bean 이름과 같에서 충돌나서
    // urlEncode 라 해주자
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }

}

// 내가 코딩을 할때는 실질적으로 new 로 객체를 만들지 않는다.
// 우리의 서비스 로직에서는 항상 Spring context 를 사용해서 가져올것이다.
//