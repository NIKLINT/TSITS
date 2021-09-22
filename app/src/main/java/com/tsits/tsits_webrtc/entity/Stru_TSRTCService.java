package com.tsits.tsits_webrtc.entity;

/**
 * @Description
 * @author: reykou
 * @date: 2021/8/31 3:47 PM
 */
public class Stru_TSRTCService {
    String ip ;
    int port ;

    public Stru_TSRTCService() {
    }

    public Stru_TSRTCService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
