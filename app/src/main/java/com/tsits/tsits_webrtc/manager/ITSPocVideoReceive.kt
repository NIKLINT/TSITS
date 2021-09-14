package com.tsits.tsits_webrtc.manager

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tsits.pocvideosdk.Stru_FriendInfo
import com.tsits.pocvideosdk.Stru_GPSData
import com.tsits.pocvideosdk.Stru_GroupInfo
import com.tsits.tsits_webrtc.fragment.GroupFragment
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback

/**
 * @author YUAN
 * @description:
 * @date :2021/9/9 10:22
 */
class ITSPocVideoReceive : AppCompatActivity(), ITSPocVideoCallback {

    lateinit var groupFragment: GroupFragment
    lateinit var hardwareInformationUtil: HardwareInformationUtil

    override fun TSPocVideoCallback_TestFun(str: String?) {
        TODO("Not yet implemented")
    }

    override fun LoginServiceResuilt(deviceID: Int, isOnline: Byte, cause: Byte) {
        if(deviceID==hardwareInformationUtil.fingerprint.toInt()){
            Toast.makeText(this,"本机登陆成功",Toast.LENGTH_SHORT).show()
        }else if (deviceID!=hardwareInformationUtil.fingerprint.toInt()){
            val result=isOnline.toInt()
            when(result){
                0->Toast.makeText(this,"离线",Toast.LENGTH_SHORT).show()
                1->Toast.makeText(this,"在线",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun LocationDeviceInfo(
        nickName: String?,
        deviceType: Byte,
        defaltGroupID: Int,
        killState: Byte,
        priority: Byte,
    ) {

    }

    override fun Friend_List(stru_friendInfoList: List<Stru_FriendInfo>?){
        if (stru_friendInfoList != null) {

        }
    }

    override fun Friend_AddorDelete(
        result: Byte,
        cause: Byte,
        fromDeviceID: Int,
        toDeviceID: Int,
        isAdd: Byte,
    ) {
        TODO("Not yet implemented")
    }

    override fun Group_List(stru_groupInfos: MutableList<Stru_GroupInfo>?) {

    }

    override fun Group_AddGroup(result: Byte, stru_groupInfo: Stru_GroupInfo?) {
        TODO("Not yet implemented")
    }

    override fun Group_DeleteGroup(result: Byte, groupID: Int) {
        TODO("Not yet implemented")
    }

    override fun Group_ChangeItem(
        result: Byte,
        groupID: Int,
        deviceIDList: MutableList<Int>?,
        action: Char,
    ) {
        TODO("Not yet implemented")
    }

    override fun GPS_UpdateData(stru_gpsData: Stru_GPSData?) {
        TODO("Not yet implemented")
    }

    override fun Call_Create(
        callerID: Int,
        callID: Int,
        callType: Byte,
        mediaType: Byte,
        isReceiverBell: Byte,
        priority: Byte,
    ) {
        TODO("Not yet implemented")
    }

    override fun Call_AnswerOrHangup(
        isAnswer: Byte,
        deviceID: Int,
        callerID: Int,
        callID: Int,
        callType: Byte,
        priority: Byte,
    ) {
        TODO("Not yet implemented")
    }

    override fun ChangeDefaultGroupID(groupID: Int) {

    }


    override fun Call_PttonResult(result: Byte) {
        var intResult = result.toInt()
        when (intResult) {
            1 ->
                Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show()
            0 ->
                Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show()
        }

    }

    override fun Call_RxonOrRxoff(isOn: Byte, deviceID: Int) {

    }
}