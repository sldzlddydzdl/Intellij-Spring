package com.example.put;

import com.example.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

//    @PutMapping("/put/{userId}")
//    public PostRequestDto put(@RequestBody PostRequestDto postRequestDto, @PathVariable Long userId){
//        System.out.println(postRequestDto);
//        return postRequestDto;
//    }

    @PutMapping("/put/{userId}")
    public PostRequestDto put(@RequestBody PostRequestDto postRequestDto, @PathVariable(name = "userId") Long id){
//        System.out.println(postRequestDto);
        System.out.println(id);
        return postRequestDto;
    }


}
