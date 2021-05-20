package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String name;
    private int age;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public User(){
        this.name = null;
        this.age = 0;
        this.phoneNumber = null;
    }

    public User(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


//    public User getDefaultUser(){
//        return new User("default" , 0);
//    }
    // ObjectMapper 가 참조하는 클래스에는 get method 를 사용하기 때문에
    // 위와 같이 어떤함수를 만들때 get 이라고 붙이면 serialize 에러가나는데
    // ex) 함수명이 getDefaultUser 이면 에러가나고,
    // DefaultUser 이면 에러가 안난다.

    public User DefaultUser(){
        return new User("default" , 0, "010-1111-2222");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
