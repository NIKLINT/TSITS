// aidl_Data.aidl
package com.tsits.tsits_webrtc;


// Declare any non-default types here with import statements

interface aidl_Data {
    //查询数据
           List<String> getData();
           //添加数据
           void setData(String s);
}