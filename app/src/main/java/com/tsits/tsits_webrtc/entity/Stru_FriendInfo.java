package com.tsits.tsits_webrtc.entity;


/**
 * @Description
 * @author: reykou
 * @date: 2021/8/31 10:16 PM
 */
public class Stru_FriendInfo {
    int deviceId;
    String nickName;

    public Stru_FriendInfo(int deviceId, String nickName) {
        this.deviceId = deviceId;
        this.nickName = nickName;
    }
    public Stru_FriendInfo(String deviceId, String nickName) {
        //200104@localhostmac -> 200104
        String sub = deviceId.substring(0,deviceId.indexOf("@"));
        this.deviceId = Integer.parseInt(sub);
        this.nickName = nickName;
    }
}
