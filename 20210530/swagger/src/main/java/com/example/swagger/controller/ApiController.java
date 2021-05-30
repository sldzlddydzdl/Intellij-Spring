package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // 아래와 같은 경우는 변수가 많아지면 불편해지는 단점이 있다.
    // 따라서 ApiImplicitParams 를 쓰자!
//    @GetMapping("/plus/{x}")
//    public int plus(
//
//                @ApiParam(value = "x값" /*, defaultValue = "20"*/)
//                @PathVariable int x,
//
//                @ApiParam(value = "y값" /*, defaultValue = "5"*/)
//                @RequestParam int y){
//        return x + y;
//    }

    // required 는 필수값인지 아닌지 판단
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "x" , value = "x 값" , required = true, dataType = "int", paramType = "path"),
                    @ApiImplicitParam(name = "y" , value = "y 값" , required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int plus(@PathVariable int x, @RequestParam int y){
        return x + y;
    }



    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴 하는 메소드")
    @GetMapping("/user")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName() , userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }

}
