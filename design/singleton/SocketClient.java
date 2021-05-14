package com.company.design.singleton;

public class SocketClient {

    private static SocketClient socketClient = null;

    private SocketClient(){
    // default 생성자를 막는다
        // 여러 객체가 생기는걸 방지하기위해서
    }

    public static SocketClient getInstance(){
        if(socketClient == null) {
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }

}
