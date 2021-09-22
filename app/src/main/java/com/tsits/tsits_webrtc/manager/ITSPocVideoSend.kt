package com.tsits.tsits_webrtc.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tsits.pocvideosdk.Stru_GPSData
import com.tsits.pocvideosdk.Stru_GPSSetting
import com.tsits.tsits_webrtc.entity.*
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback
import com.tsits.tsits_webrtc.sdk.TSPocVideoSDK

/**
 * @author YUAN
 * @description:
 * @date :2021/9/9 10:22
 */
class ITSPocVideoSend : AppCompatActivity(),TSPocVideoSDK {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun Init(
        locationDeviceID: Int,
        esn: String?,
        sVersion: String?,
        stru_janusService: Stru_JanusService?,
        stru_tsrtcService: Stru_TSRTCService?,
        stru_xmppService: Stru_XMPPService?,
        stru_gpsSetting: Stru_GPSSetting?
    ): Byte {
        TODO("Not yet implemented")
    }

    override fun Close() {
        TODO("Not yet implemented")
    }

    override fun Call_Create(
        callID: Int,
        callType: Byte,
        mediaType: Byte,
        isReceiverBell: Byte,
        priority: Byte
    ) {
        TODO("Not yet implemented")
    }

    override fun Call_AddDeviceTo(
        deviceID: Int,
        callType: Byte,
        mediaType: Byte,
        isReceiverBell: Byte,
        priority: Byte
    ) {
        TODO("Not yet implemented")
    }

    override fun Call_AnswerOrHangup(isAnswer: Boolean) {
        TODO("Not yet implemented")
    }

    override fun ChangeDefaultGroupID(groupID: Int) {
        TODO("Not yet implemented")
    }

    override fun Call_PttonOrPttoff(isOn: Byte) {
        TODO("Not yet implemented")
    }

    override fun Group_AddGroup(stru_groupInfo: Stru_GroupInfo?) {
        TODO("Not yet implemented")
    }

    override fun Group_DeleteGroup(groupID: Int) {
        TODO("Not yet implemented")
    }

    override fun Group_ChangeItem(groupID: Int, deviceIDList: MutableList<Int>?, action: Char) {
        TODO("Not yet implemented")
    }

    override fun Device_ChangeInfo(nickName: String?) {
        TODO("Not yet implemented")
    }

    override fun Friend_AddorDelete(deviceID: Int, isAdd: Byte) {
        TODO("Not yet implemented")
    }

    override fun GPS_UpdateLocationData(stru_gpsData: Stru_GPSData?) {
        TODO("Not yet implemented")
    }


}