package com.example.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
// @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
// 위는 해당클래스에 일관적으로 모든 JSON 룰을 적용 시켜줄수 있다.

//        {
//                "name" : "steve",
//                "age" : 20,
//                "car_list" : [
//                    {
//                    "name" : "BMW",
//                    "car_number" : "11가 1234"
//                    },
//                    {
//                    "name" : "A4",
//                    "car_number" : "22가 3456"
//                    }
//                ]
//        }

// 위에 json 에는 car_list , car_number로 snake case 로 되잇고
// java 에서는 carList로 되있다. 따라서 @JsonNaming 으로
// class 전체를 JSON룰을 적용시킬수도 있꼬
// @JsonProperty는 하나만 해줄수 있다.
public class PostRequestDto {

    private String name;
    private int age;
    private List<CarDto> carList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CarDto> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDto> carList) {
        this.carList = carList;
    }

    @Override
    public String  toString() {
        return "PostRequestDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carList=" + carList +
                '}';
    }
}
