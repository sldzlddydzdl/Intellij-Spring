package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest
//@Import({MarketApi.class , DollarCalculator.class}) // Main 에 있는 MarketApi 를 사용하겠다. import 하겠다.
public class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    public void dollarCalculatorTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);
//        dollarCalculator.init();

        int sum = calculator.sum(10, 10);
        int minus = calculator.minus(10, 10);

        System.out.println(sum);
        System.out.println(minus);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);

    }

}
