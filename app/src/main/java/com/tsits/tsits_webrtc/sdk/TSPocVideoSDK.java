package com.tsits.tsits_webrtc.sdk;

import com.tsits.pocvideosdk.Stru_GPSData;
import com.tsits.pocvideosdk.Stru_GPSSetting;
import com.tsits.tsits_webrtc.entity.Stru_GroupInfo;
import com.tsits.tsits_webrtc.entity.Stru_XMPPService;
import com.tsits.tsits_webrtc.entity.Stru_TSRTCService;
import java.util.List;

import com.tsits.tsits_webrtc.entity.Stru_JanusService;

/**
 * @Description 接口类
 * @author: reykou
 * @date: 2021/8/31 3:41 PM
 */
public interface TSPocVideoSDK  {

    /** 1
     * 初始化SDK
     * @param locationDeviceID 本机ID、在服务器登记过的唯一ID号码、用于设备登录
     * @param esn   本机唯一串号ESN码、和本机ID绑定
     * @param sVersion  软件版本号等
     * @param stru_janusService 服务器相关信息
     * @param stru_tsrtcService
     * @param stru_xmppService
     * @param stru_gpsSetting gps相关配置
     * @return 返回值
     */
    byte Init(int locationDeviceID, String esn, String sVersion, Stru_JanusService stru_janusService,
                           Stru_TSRTCService stru_tsrtcService, Stru_XMPPService stru_xmppService, Stru_GPSSetting stru_gpsSetting);

    /** 2
     * 结束SDK
     */
    void Close();

     /** 3
     * 呼叫相关:申请呼叫
     * @param callID 对方id号码、可是单呼ID或组呼ID
     * @param callType 1-单呼 2-组呼
     * @param mediaType 1-监控音视频 2-监控音频 3-语音 4-视频
     * @param isReceiverBell 0-无振铃 1-振铃
     * @param priority 1/2/3 高优先级打断低
     */
    void Call_Create(int callID, byte callType, byte mediaType, byte isReceiverBell,byte priority);

    /** 4
     * 呼叫相关: 增加设备到当前会话
     * @param deviceID 增加的设备id
     * @param callType 1-单呼 2-组呼
     * @param mediaType 1-监控音视频 2-监控音频 3-语音 4-视频
     * @param isReceiverBell 0-无振铃 1-振铃
     * @param priority 1/2/3 高优先级打断低
     */
    void Call_AddDeviceTo(int deviceID, byte callType, byte mediaType, byte isReceiverBell,byte priority);

    /** 5
     * 呼叫相关:发送用户接听或挂机操作、
     * @param isAnswer 0-挂断 1-接听
     */
    void Call_AnswerOrHangup(boolean isAnswer);

    /** 6
     * 单工呼叫相关:变更默认守候组（单工呼叫）、异步-界面收到变更确认后才可切换显示。
     * @param groupID
     */
    void ChangeDefaultGroupID(int groupID);

    /** 7
     * 单工呼叫相关:发送 ptton 或 pttoff
     * @param isOn 0-pttoff 1-ptton
     */
    void Call_PttonOrPttoff(byte isOn);

    /** 8
     * 组相关:申请增加组、组号等由异步回调决定。 异步-界面收到变更确认后才可切换显示。
     * @param stru_groupInfo
     */
    void Group_AddGroup(Stru_GroupInfo stru_groupInfo);

    /** 9
     *  组相关:删除组 异步-界面收到变更确认后才可切换显示。
     * @param groupID
     */
    void Group_DeleteGroup(int groupID);

    /** 10
     * 组相关:组成员变更信息
     * @param groupID 组ID
     * @param deviceIDList 需变更的设备ID列表
     * @param action 0-删除指定设备 1-增加指定设备 2-替换整个组列表
     */
    void Group_ChangeItem(int groupID,List<Integer> deviceIDList, char action);

    /** 11
     * 允许用户更改的本机信息
     * @param nickName 昵称
     */
    void Device_ChangeInfo(String nickName);

    /** 12
     * 添加删除好友 异步
     * @param deviceID
     * @param isAdd 0-删除 1-增加
     */
    void Friend_AddorDelete(int deviceID, byte isAdd);

    /** 13
     * 更新最新的本地GPS信息、具体更新方式根据
     * @param stru_gpsData
     */
    void GPS_UpdateLocationData(Stru_GPSData stru_gpsData);

}
