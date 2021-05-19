package com.example.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequestDto {

    private String account; // JSON 의 KEY 값과 동일해야한다.
    private String email;
    private String address;
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber; // phone_number

    @JsonProperty("OTP")
    private String OTP; // 이런 경우는 kamel case 도 아니고
                        // snake case 도 아니다.
                        // 얘는 약어라든지 이런건 대문자로 표현한다.
                        // 이런 특수한 경우에는
                        // @JsonProperty 를 사용하면된다.


//    {
//        "account" : "user01",
//            "email" : "steve@gmail.com",
//            "address" : "패스트캠퍼스",
//            "password" : "abcd",
//            "phone_number" : "010-1111-2222"
//         위와같이 java에서는 kamel case로 phoneNumber라 되있고
//            JSON 에서는 snake case로 되어있다.
//            이를 방지하기 위한 방법이 여러가지가 있다.
//          @JsonProperty("phone_number")
//          private String phoneNumber; // phone_number
//            위와 같이 @JsonProperty 를 쓰는 방법도 있따.
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", OTP='" + OTP + '\'' +
                '}';
    }
}
