package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
@Validated
public class ApiController {

    @GetMapping("")
    public User get(
            @Size(min = 2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age){
                // required 옵션은 requestparam 이 없어도 동작을하되
                // name 은 null 이된다.
        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

//    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
//    public ResponseEntity methodArgumentNotValidException(Exception e){
//        System.out.println("api controller");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }



}

//  Exception 에러가 나면 Client 에서는
//  매우 불친절하게 뭘 고쳐야 되는지도 안알려주고 400 에러 500 에러만 보내준다.
// GlobalControllerAdvice 에 전체 Exception 을 잡아줘도
// Controller 에 ExceptionHandler 를 해주면 Controller 의 Exception 을 먼저 타게 된다.
