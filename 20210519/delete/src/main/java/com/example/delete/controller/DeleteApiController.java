package com.example.delete.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){
        System.out.println(userId);
        System.out.println(account);

        // delete 자체가 리소스 삭제 200 oK
    }

    // http://localhost:8080/api/delete/100?account=user100
//    100
//    user100
}
