package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

//    @PostMapping("/post")
//    public void post(Map<String, Object> requestData){
//        requestData.entrySet().forEach(stringObjectEntry -> {
//            System.out.println("key : " + stringObjectEntry.getKey());
//            System.out.println("key : " + stringObjectEntry.getValue());
//        });
//    }


    // Post 는 파라미터에 @RequestBody 를 붙여줘야한다.
    // Map 으로 하기애는 뭘보낼지 미리 다 알아야해서 살짝 쓰기 애매하다.
//    @PostMapping("/post")
//    public void post(@RequestBody Map<String, Object> requestData){
//        requestData.forEach((key, value) -> {
//            System.out.println("key : " + key);
//            System.out.println("value : " + value);
//        });
//    }

    // 값을 받아 줄 수 있도록 해줄수 있다.
    @PostMapping("/post")
    public void post(@RequestBody PostRequestDto requestData){
        System.out.println(requestData);
    }
}
