package com.tsits.tsits_webrtc.entity

/**
 * @author YUAN
 * @description:
 * @date :2021/9/17 16:19
 */

class RoomMsg {
    var rightHand:String? = null
    var content:String? = null
    var type:Int? = null


    constructor(content: String?, type: Int?) {
        this.content = content
        this.type = type

    }
    constructor(rightHand: String?){
        this.rightHand = rightHand
    }
}