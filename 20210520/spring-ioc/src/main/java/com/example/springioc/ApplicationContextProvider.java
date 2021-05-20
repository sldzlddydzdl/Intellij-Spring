package com.example.springioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 이 코드는 인터넷에 엄청많이 나와있다.
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override // Spring 이 알아서 외부로부텅 주입을 해준다.
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext; // static 변수에 주입
    }

    public static ApplicationContext getContext(){
        return context; // 가져다 쓰기.
    }

}
