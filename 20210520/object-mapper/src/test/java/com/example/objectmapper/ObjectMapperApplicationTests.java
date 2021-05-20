package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-------------");

        // Text JSON -> Object
        // Object -> Text JSON

        // controller reg json(text) -> object
        // response object -> json(text)

        var objectMapper = new ObjectMapper();

        // object -> text
        // object mapper get method 를 활용한다.
        // User 에  get method 가 없으면 에러난다.
        var user = new User("steve", 10, "010-1111-2222");
        var text = objectMapper.writeValueAsString(user); // object -> json
        System.out.println(text);
        //{"name":"steve","age":10}

        // text -> object
        // object manager 는 default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, User.class); // json -> object
        System.out.println(objectUser);
        // User 에 default 생성자가 필요하다
        //User{name='steve', age=10}

    }

}
