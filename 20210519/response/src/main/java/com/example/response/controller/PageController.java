package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // ResponseEntity

//    @ResponseBody
//    @GetMapping("/user")
//    public User user(){
////        User user = new User();
////        // var 타입 추론 java 11버젼 이후 새로생긴 명령어
//
//        var user = new User();
//        user.setName("steve");
//        user.setAddress("패스트캠퍼스");
//        return user;
//    }
//

//    보통은 @ResponseBody를 안내려야하고
//    따로 특수한상황에 @ResponseBody 를 써야되면 위와같이 쓰면된다.

}
