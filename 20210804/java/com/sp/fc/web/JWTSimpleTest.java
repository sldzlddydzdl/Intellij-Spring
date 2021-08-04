package com.sp.fc.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JWTSimpleTest {

    private void printToken(String token){
        String[] tokens = token.split("\\.");
        System.out.println("header : " + new String(Base64.getDecoder().decode(tokens[0])));
        System.out.println("body : " + new String(Base64.getDecoder().decode(tokens[1])));
//        System.out.println("header : " + new String(Base64.getDecoder().decode(tokens[0])));
    }

    @DisplayName("1. jjwt 를 이용한 토큰 테스트")
    @Test
    void test_1(){
        String okta_token = Jwts.builder().addClaims(
                Map.of("name" , "jongwon", "price" , 3000)
        ).signWith(SignatureAlgorithm.HS256, "jongwon")
                .compact();
        System.out.println(okta_token);
//        eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiam9uZ3dvbiIsInByaWNlIjozMDAwfQ.GZWsMS0PSCnR6_2cutvHemUhcJ_CspLUpPFpN4huTik
//                  header                       body                            header 와 body 를 서명한 hash 값이다.

        printToken(okta_token);
//        header : {"alg":"HS256"}
//        body : {"name":"jongwon","price":3000}

        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey("jongwon").parseClaimsJws(okta_token);
        System.out.println(tokenInfo);

    }



    @DisplayName("2. java-jwt 를 이용한 토큰 테스트")
    @Test
    void test_2() {

        byte[] SEC_KEY = DatatypeConverter.parseBase64Binary("jongwon");

//        String oauth0_token = JWT.create().withClaim("name", "jongwon").withClaim("price", 3000)
//                .sign(Algorithm.HMAC256("jongwon"));

//        System.out.println(oauth0_token);
//        eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcmljZSI6MzAwMCwibmFtZSI6Impvbmd3b24ifQ.M8S80xADlZxrQnKSUj7gYuG9pPuU8GTZXNawGy0itxE
//        printToken(oauth0_token);
//        header : {"typ":"JWT","alg":"HS256"}
//        body : {"price":3000,"name":"jongwon"}

        String oauth0_token = JWT.create().withClaim("name", "jongwon").withClaim("price", 3000)
                .sign(Algorithm.HMAC256(SEC_KEY));
        System.out.println(oauth0_token);
        printToken(oauth0_token);

        DecodedJWT verified = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(oauth0_token);
        System.out.println(verified.getClaims());

        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey(SEC_KEY).parseClaimsJws(oauth0_token);
        System.out.println(tokenInfo);

//        DecodedJWT verified = JWT.require(Algorithm.HMAC256("jongwon")).build().verify(auth0_token);
//        System.out.println(verified.getClaims());
//
//        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey("jongwon").parseClaimsJws(auth0_token);
//        System.out.println(tokenInfo);
        // 오류난다 이유는 key 에 문제가 있따 jwt 에서 key 를 넣는다는거는 이값을 다시 passing 을 한다 반면에 java-jwt 는 그값을 passing 하지않고 그대로 키값으로 사용한다
        // 그래서 서로 키값이 같아보이지만 내부적으로는 다릏게 되있다.

    }


    @DisplayName("3. 만료 시간 테스트")
    @Test
    void test_3() throws InterruptedException {

        final Algorithm AL = Algorithm.HMAC256("jongwon");

        String token = JWT.create().withSubject("a1234")
                .withNotBefore(new Date(System.currentTimeMillis() + 1000)) // 1초 지나고 나서 토큰을 부여해준다.
                .withExpiresAt(new Date(System.currentTimeMillis() + 3000))
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000))
                .sign(AL);

//        Thread.sleep(2000);
        // 토근 만료시간을 1초로 해주엇기때문에 2초가 지나면 토큰이 만료되서 verify 를 불러올수없게된다.
//        DecodedJWT verify = JWT.require(AL).build().verify(token);
//        System.out.println(verify.getClaims());
        try {
            DecodedJWT verify = JWT.require(AL).build().verify(token);
            System.out.println(verify.getClaims());
        }catch(Exception ex){
            System.out.println("유효하지 않은 토큰입니다...");
            DecodedJWT decode = JWT.decode(token);
            System.out.println(decode.getClaims());
        }

    }



}
