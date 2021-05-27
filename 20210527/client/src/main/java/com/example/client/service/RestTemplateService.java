package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http:// localhost/api/server/hello
    // response
    // RestTemplate 을 통해서 get 으로 통신하는 방법 ,
    // UriComponent 써서 주소를 만드는 방법 을 알아봤다.
    // client 가 server 로 요청해서 응답을 받는 구조이다 지금하는게

    public UserResponse hello() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/hello")
                .queryParam("name", "aaaaa")
                .queryParam("age", 99)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        // getForEntity , getForObject 는 가져오겠다는 의미의 get 이아니라
        // http 의 get 메소드를 부르겠다는 것의 get 이다.

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody()); // 내용


        return result.getBody();
    }

    public void post() {
        // http://localhost:9090/api/serevr/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
//                .expand(100)          // 지금 상황은 이렇게 두개의 expand 를 쓰면
//                .expand("steve")      // 에러가 발생한다.
                .toUri();

        System.out.println(uri);

        // http body 가 있어야 한다 하지만 우리는
        // http body -> object -> object mapper -> Json -> rest template -> http body json

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(uri, req, String.class);
        // 니가 뭘 보낼줄 모르니까 나는 일단 String 으로 받을게

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

//        return response.getBody();
    }

    public UserResponse exchange() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
//                .expand(100)          // 지금 상황은 이렇게 두개의 expand 를 쓰면
//                .expand("steve")      // 에러가 발생한다.
                .toUri();

        System.out.println(uri);

        // http body 가 있어야 한다 하지만 우리는
        // http body -> object -> object mapper -> Json -> rest template -> http body json

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
//                .expand(100)          // 지금 상황은 이렇게 두개의 expand 를 쓰면
//                .expand("steve")      // 에러가 발생한다.
                .toUri();

        System.out.println(uri);

        // http body 가 있어야 한다 하지만 우리는
        // http body -> object -> object mapper -> Json -> rest template -> http body json

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(
                new Req.Header()
        );

        req.setResBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();



//        Req<UserResponse>.class
//            제네릭에는 .class 를 붙일수가 없다.
//              이러한 제네릭을 대응하기위해서 ParameterizedTypeReference 을 써야한다.
//        new ParameterizedTypeReference<Req<UserResponse>>(){};

        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<>() {
        });

//        return response.getBody().getResBody();
        return response.getBody();
        // reponse.getBody() 는 ResponseEntity 의 getBody 를 가져온것이고
        // .getrBody() 를 써서 Req 에 있는 정보를 가져오기위해서 쓴것이다.
    }
}
