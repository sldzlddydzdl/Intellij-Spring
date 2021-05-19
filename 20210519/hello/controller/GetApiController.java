package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") // http://localhost:9090/api/get/hello
    // 메소드 자체가 Get 이다.
    public String hello(){
        return "get Hello";
    }

    // @RequestMapping("/hi") //get /post/ put /delete
    // 위와같이 하면 get, post, put ,delete 모든 메소드가 다 동작한다.
    @RequestMapping(path = "/hi", method = RequestMethod.GET) // get http://localhost/api/get/hi
    // 그래서 get만 하고싶으면 위와같이 해주면 get만호출되고 나머지
    // post , put , delete 는 호출이안된다.
    // 위와 같은 방법은 옛날 방식이고
    // @GetMapping 해주면 같은 역할을 해주게 된다.
    public String hi(){
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{name}
                                                    // 변하는 값

    // http://localhost:9090/api/get/path-variable/{spring-boot}
    // http://localhost:9090/api/get/path-variable/{spring}
    // http://localhost:9090/api/get/path-variable/{java}
    // 위와같이 GetMapping 주소를 계속 만드는것은 힘들다.
    // 주소는 "/path-variable/{name} 이라해주고
    // 따라서 파라미터에 @PathVariable String name 이라 해주면 된다.


    // 중요한점!! 변수의 name 을 똑같이 맞춰저야한다.
    // GetMapping 안에 {name} 과 @PathVariable String name 변수 이름이 같아햔다.
//    @GetMapping("/path-variable/{name}")
//    public String pathVariable(@PathVariable String name){
//
//        System.out.println("PathVariable : " + name);
//
//        return name;
//    }
    // 그러나! 위와같이 변수명을 맞춰줄수 없는 상황이 올때는
    // 아래와 같이 해주면 된다.
    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable(name = "id") String PathName){

        System.out.println("PathVariable : " + PathName);

        return PathName;
    }

    // 구굴에 intellij라고 치면 인터넷 주소가 다음과같이나온다.
//    search?q = intellij
//    &oq = inte
//    &aqs = chrome.0.35i39j69i57j35i39j0i433l3j0i20i263j0j0i433l2.748j0j15
//    &sourceid = chrome
//    &ie = UTF-8
//            ?에서 부터 queryParameter가 되는거다
//            queryParameter는 검색을 할때 여러가지 매개변수 인자를 얘기한다.
//            주로 검색할때 사용한다.

    // ?key=value&key2=value2 의 형식으로 되어있다.


    // http://localhost:9090?api/get/query-param?user=steve&email=steve@gmail.com&age=30
    // 위와같이 query-param을 사용하는 방법은 Map컬렉션을 쓰는방법도있따.
    @GetMapping(path ="query-param")
    public String queryParam(@RequestParam Map<String,String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });


        return sb.toString();
    }

    @GetMapping("query-param02")
    public String queryParam02(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }

//    객체가 들어오게되면은 query-param에 들어오는 주소들
//    즉 ?뒤에 있는 값들을 Spring-boot에서 판단을 한다.
//    key 해당하는 이름들을 해당 객체에서 변수와 이름 매칭을 해준다.
//    ?user=steve&email=steve@gmail.com&age=30
//    아래와 같은 방법을 제일 선호한다.
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }

    // queryParam01 은 Map을 썻다.
    // queryParam02 는 @RequestParam 일일이 붙여줬다.
    // queryParma03 은 @RequestParam 을 안붙여주고 dto 패키지안에
    // 클래스 하나를 사용해서 객체를 생성햇다.
}
