package com.fastcampus.jpa.bookmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // Mock Mvc 를 만들어준다
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorld() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/hello-world"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("hello-world"));

    }

}