package com.example.springcalculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {

    private final ICalculator iCalculator;

//    public Calculator(ICalculator iCalculator){
//        this.iCalculator = iCalculator;
//    }

    public int sum(int x, int y){
        this.iCalculator.init();
        return this.iCalculator.sum(x,y);
    }

    public int minus(int x, int y){
        this.iCalculator.init(); // 시세를 받아와서 달러에 반영시키기
        return this.iCalculator.minus(x, y);
    }

}
