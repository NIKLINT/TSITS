package com.tsits.tsits_webrtc.entity;

import android.view.Menu;

import java.util.List;

/**
 * @Description
 * @author: reykou
 * @date: 2021/8/31 10:17 PM
 */
public class Stru_GroupInfo {
    int GroupID;
    int GroupName;
    char isDynamic;
    List<Integer> MemberList;

    /**
     *
     * @param groupID  组唯一标识id
     * @param groupName 组别名
     * @param isDynamic 0-普通组 1-动态组
     * @param memberList 成员列表、默认list[0]为创建者ID、一般填写本机号码
     */
    public Stru_GroupInfo(int groupID, int groupName, char isDynamic,List<Integer> memberList) {
        GroupID = groupID;
        GroupName = groupName;
        MemberList = memberList;
    }
}
