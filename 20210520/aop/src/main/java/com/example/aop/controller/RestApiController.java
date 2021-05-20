package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
//        System.out.println("get method");
//        System.out.println("get method : " + id);
//        System.out.println("get method : " + name);

//        // 함수실행전 시간
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();

        // TODO

//        // 끝나고 나면 시간을 젠다
//        stopWatch.stop();
//        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
//        // 걸리는데 지난 시간을 찍어보자

        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
//        System.out.println("post method : " + user);
//        // 함수실행전 시간
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();

        // TODO

//        // 끝나고 나면 시간을 젠다
//        stopWatch.stop();
//        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
//        // 걸리는데 지난 시간을 찍어보자
        return user;
    }

    @Timer // 직접 만든 Annotation
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

//        // 함수실행전 시간
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();

        // TODO
        // db logic // delete 가 함수가 2초걸린다고 가정하자.
        Thread.sleep(1000* 2);

//        // 끝나고 나면 시간을 젠다
//        stopWatch.stop();
//        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
//        // 걸리는데 지난 시간을 찍어보자

    }

    @Decode
    @PutMapping ("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }

}

//    // 지금 AOP 를 안써주면 모든 함수에 이런 로직을 넣어야하는
//    // 반복적인 행동을 해야하므로 AOP 를 써줘야한다.
//    // 함수실행전 시간
//    StopWatch stopWatch = new StopWatch();
//    stopWatch.start();
//
//    // TODO
//
//    // 끝나고 나면 시간을 젠다
//    stopWatch.stop();
//    System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
//    // 걸리는데 지난 시간을 찍어보자