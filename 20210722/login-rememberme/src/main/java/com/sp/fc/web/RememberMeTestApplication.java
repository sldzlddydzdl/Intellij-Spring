package com.sp.fc.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.sp.fc.user",
        "com.sp.fc.web"
})
public class RememberMeTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RememberMeTestApplication.class, args);
    }
}
