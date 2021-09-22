package com.tsits.tsits_webrtc.sdk;

import com.tsits.pocvideosdk.Stru_GPSData;
import com.tsits.tsits_webrtc.entity.Stru_FriendInfo;
import com.tsits.tsits_webrtc.entity.Stru_GroupInfo;

import java.util.List;

/**
 * @Description
 *
 * @author: reykou
 * @date: 2021/8/12 11:29 AM
 */
public interface ITSPocVideoCallback {
    void TSPocVideoCallback_TestFun(String str);

    /** 1
     * 1.deviceID == 本机ID:本机登录服务器结果上报
     * 2.deviceID != 本机ID:远端设备状态上报
     * @param deviceID 设备ID
     * @param isOnline 0-离线 1-上线
     * @param cause 离线原因 参见 Enum_LoginCause
     */
    void LoginServiceResuilt(int deviceID,byte isOnline, byte cause);

    /** 2
     * 1.本机设备信息上报/更新 2.远端服务器上报更新
     * @param nickName 昵称
     * @param deviceType 设备类型
     * @param defaltGroupID 默认呼叫组
     * @param killState 0-遥启，1-遥晕，2-遥毙
     * @param priority 1/2/3 高优先级打断低
     */
    void LocationDeviceInfo(String nickName, byte deviceType, int defaltGroupID,byte killState,byte priority);

    /** 3
     * 本机设备的好友列表
     * @param stru_friendInfoList
     */
    void Friend_List(List<Stru_FriendInfo> stru_friendInfoList);

    /** 4
     * 1.本机添加删除好友 2.远端提示要添加好友
     * @param result 0- 失败 1-成功
     * @param cause 失败原因 0-没找到设备id 1-拒绝添加好友
     * @param fromDeviceID 发起请求的ID
     * @param toDeviceID 被请求添加的ID
     * @param isAdd 0-删除 1-增加
     */
    void Friend_AddorDelete(byte result, byte cause, int fromDeviceID, int toDeviceID, byte isAdd);

    /** 5
     * 本机设备的群组列表
     * @param stru_groupInfos
     */
    void Group_List(List<Stru_GroupInfo> stru_groupInfos);

    /** 6
     * 组相关:1.申请增加组确认上报 2.远端上报增加群组
     * @param result 0- 失败 1-成功
     * @param stru_groupInfo
     */
    void Group_AddGroup(byte result,Stru_GroupInfo stru_groupInfo);

    /** 7
     *  组相关: 1.删除组确认回调 2.远端上报删除组
     * @param result 0- 失败 1-成功
     * @param groupID
     */
    void Group_DeleteGroup(byte result,int groupID);

    /** 8
     * 组相关: 1.变更组确认回调 2.远端上报变更组信息
     * @param result 0- 失败 1-成功
     * @param groupID 组ID
     * @param deviceIDList 需变更的设备ID列表
     * @param action 0-删除指定设备 1-增加指定设备 2-替换整个组列表
     */
    void Group_ChangeItem(byte result,int groupID,List<Integer> deviceIDList, char action);

    /** 9
     * 更新GPS信息
     * @param stru_gpsData
     */
    void GPS_UpdateData(Stru_GPSData stru_gpsData);

    /** 10
     * 来电提示
     * @param callerID 发起方id号码
     * @param callID 对方id号码、可是单呼ID或组呼ID
     * @param callType 1-单呼 2-组呼
     * @param mediaType 1-监控音视频 2-监控音频 3-语音 4-视频
     * @param isReceiverBell 0-无振铃 1-振铃
     * @param priority 1/2/3 高优先级打断低
     */
    void Call_Create(int callerID,int callID, byte callType, byte mediaType, byte isReceiverBell,byte priority);

    /** 11
     * 呼叫相关:远端设备接听状态
     * @param isAnswer 0-挂断 1-接听 2-超时未接听
     * @param deviceID 远端设备ID
     * @param callerID 发起者
     * @param callID 被呼叫号码
     * @param callType 呼叫类型
     * @param priority 优先级
     */
    void Call_AnswerOrHangup(byte isAnswer,int deviceID,int callerID,int callID, byte callType,byte priority);

    /** 12
     * 单工呼叫相关:变更默认守候组（单工呼叫）结果上报
     * @param groupID
     */
    void ChangeDefaultGroupID(int groupID);

    /** 13
     * 单工呼叫相关:发送 ptton 结果上报
     * @param result
     */
    void Call_PttonResult(byte result);

    /** 14
     * 单工呼叫相关:接收
     * @param isOn 0-RxOff 1-RxOn
     * @param deviceID 远端设备ID
     */
    void Call_RxonOrRxoff(byte isOn,int deviceID);


}
