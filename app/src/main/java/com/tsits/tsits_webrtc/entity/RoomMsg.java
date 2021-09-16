package com.tsits.tsits_webrtc.entity;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/16 13:46
 */
public class RoomMsg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;


    public String getContent() {
        return content;
    }

    public String getRightHand() {
        return rightHand;
    }

    public int getType() {
        return type;
    }

    private String rightHand;
    private String content;
    private int type;

    public RoomMsg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public RoomMsg(String rightHand) {
        this.rightHand = rightHand;
    }

}
