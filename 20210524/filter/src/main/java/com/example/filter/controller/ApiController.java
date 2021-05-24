package com.example.filter.controller;

import com.example.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//  Spring boot 에서는 lombok 을 써서 Slf4j 하면 log 를 쓸수 있다.
@Slf4j // system.out.println 으로 찎는게 아니라 log 으로 찍는다.
@RestController
@RequestMapping("/api/user")
public class ApiController {

    @PostMapping("")
    public User user(@RequestBody User user){
        log.info("User : {} " , user);

        return user;
    }


}
