package com.sp.fc.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/greeting")
    public String greeting(){
        return "hello";
    }

    @PostMapping("/greeting")    // post 한다는 것은 뭔가 서버에 데이타를 변경요청을 하거나 변경을 가하는 작업을 한다.
    public String greeting(@RequestBody String name){
        return "hello "+name;
    }



}
