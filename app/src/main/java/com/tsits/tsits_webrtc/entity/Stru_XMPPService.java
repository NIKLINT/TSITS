package com.tsits.tsits_webrtc.entity;

/**
 * @Description
 * @author: reykou
 * @date: 2021/8/31 3:48 PM
 */
public class Stru_XMPPService {
    String ip ;
    int port;
    String serviceName ;

    public Stru_XMPPService() {
    }

    public Stru_XMPPService(String ip, int port, String serviceName) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getServiceName() {
        return serviceName;
    }
}
