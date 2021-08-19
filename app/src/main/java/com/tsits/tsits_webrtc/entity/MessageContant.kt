package com.tsits.tsits_webrtc.entity

class MessageContant {
    var messageImageUrl: String? = null
    var group: String? = null
    var time: String? = null

    constructor(messageImageUrl: String?, group: String?, time: String?) {
        this.messageImageUrl = messageImageUrl
        this.group = group
        this.time = time

    }
}