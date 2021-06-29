package com.fastcampus.jpa.bookmanager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // slice 테스트 전체 스프링테스트 전체를 로딩하지않고
            // controller 에 대한 일부 테스트의 bean 들만 테스트하기 때문에
            // jpa 옵션에서 발생하는 에러이다.
            // 해결방법 1. @MockBean(JpaMetamodelMappingContext.class)
            //         2. BookmanagerApplication 에서 @EnableJpaAuditing 은 없애주고
            //            새로운 클래스를 생성주고 생성한 클래스에 @EnableJpaAuditing 을 달아주고
            //            @Configuration 도 달아주므로 해결이된다.

//                          @WebMvcTest를 지워주고
//                          @MockBean 도 지워주고
//                        3. @SpringBootTest를 해주고
//                            @Autowired
//                            private WebApplicationContext wac;
//
//                            private MockMvc mockMvc;
//
//                            @BeforeEach
//                            void before(){
//                                mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//                            }
//@MockBean(JpaMetamodelMappingContext.class)  // jpa 관련이 필요가 없기 때문에 로딩을 할 수없었는데
                                             // @MockBean 으로 있는것처럼 해줘서 막아주는 것이다.
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