package com.company.design;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.AClazz;
import com.company.design.singleton.BClazz;
import com.company.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

public class Main {


//    singleton pattern
//
//    singleton 패턴은 어떠한 클래스(객체)가 유일하게 1개만 존재 할 때 사용한다.
//
//    이를 주로 사용한는 곳은 서로 자원을 공유 할 때 사용하는데 ,실물 세계에서는
//    프린터가 해당되며, 실제 프로그래밍에서는 TCP Socket 통신에서 서버와 연결된
//    connect 객체에 주로 사용한다.
//
//
//    Adapter pattern
//
//    Adapter 는 실생활에서는 110v를 220v로 변경해주거나 , 그 반대로 해주는
//    흔히 돼지코라고 불리는 변환기를 예로 들 수 있다.
//    호환성이 없는 기존 클래스의 인터페이스를 변환하여 재사용 할 수 있도록한다.
//    SOLID중에서 개방폐쇄 원칙 (OCP)를 따른다.
//
//
//    Proxy pattern
//
//    Proxy는 대리인 이라는 뜻으로써, 뭔가를 대신해서 처리하는 것
//    Proxy Class 를 통해서 대신 전달 하는 형태로 설계되며, 실제 Client는 Proxy로
//    부터 결과를 받는다.
//    Cache의 기능으로도 활용이 가능하다.
//    SOLID 중에서 개방폐쇄 원칙(Ocp)과 의존 역전 원칙(DIP)를 따른다.

    public static void main(String[] args) {
        /*
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClien = aClazz.getSocketClient();
        SocketClient bClien = bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가?");
        System.out.println(aClien.equals(bClien));


        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
//        connect(cleaner);

        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airapdapter =  new SocketAdapter(airConditioner);
        connect(airapdapter);


        Browser browser = new Browser("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();


        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        */

        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()-> {
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                }
        );

        aopBrowser.show();
        System.out.println("loading time : " + end.get());

        aopBrowser.show();
        System.out.println("loading time : " + end.get());

    }

    // 콘센트
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }

}
