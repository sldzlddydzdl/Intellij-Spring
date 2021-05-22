package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = "com.example.exception.controller")// 전체적으로 에러를 다잡을때
// (basePackages = "com.example.exception.controller") 이 하위에 있는 에러들은 다 잡을거야
@RestControllerAdvice
//@ControllerAdvice // 뷰를 사용하는?
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        System.out.println("----------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("----------------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
