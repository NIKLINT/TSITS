package com.tsits.tsits_webrtc.activity

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.*
import com.tsits.tsits_webrtc.fragment.*
import com.tsits.tsits_webrtc.service.ChannelService
import kotlinx.android.synthetic.main.activity_group_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_message.*


class MainActivity : AppCompatActivity(){

    private var TAG = "MainActivity"
    private var i = 0


    //由AIDL文件生成的Java类
//    private var data: aidl_Data? = null

    //标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
    private var mBound = false

    private var groupFragment: GroupFragment? = null
    private var messageFragment: MessageFragment? = null
    private var microphoneFragment: MicrophoneFragment? = null
    private var mapFragment: MapFragment? = null
    private var workFragment: WorkFragment? = null
    private var currentFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, ChannelService::class.java))
//
//        chip1.setOnClickListener(this)
//        chip2.setOnClickListener(this)
        whichIsCliclk()



        supportActionBar?.hide()  //隐藏顶部状态栏
        groupFragment = GroupFragment()
        messageFragment = MessageFragment()
        microphoneFragment = MicrophoneFragment()
        mapFragment = MapFragment()
        workFragment = WorkFragment()
        groupFragment?.let { its -> loadFragment(its) }
    }

//    //如果与服务的连接处于未连接状态，则尝试连接
//    override fun onClick(p0: View?) {
//        if (!mBound) {
//            Log.e(TAG, " connected now")
//            attemptToBindService()
//            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (data == null)
//            return
//
//        //发送数据，接收数据
//        when (p0?.id) {
//            R.id.chip1 -> {
//                data!!.setData("setMode" + 18)
//                Toast.makeText(this, "Audio Mode Set To :$data", Toast.LENGTH_SHORT).show()
//
//            }
//            R.id.chip2 -> {
//                list = data!!.data as ArrayList<String>
//                var modeShow = StringBuffer()
//                for (d in list) {
//                    modeShow.append(d).append("\n")
//                }
//                Toast.makeText(this, "Audio Mode Get Mode: $data", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    //连接服务
//    private fun attemptToBindService() {
//        val intent = Intent()
//        intent.action = "com.tsits.tsits_webrtc.AIDL" //在AndroidManifest.xml进行配置隐形启动action
//        intent.`package` = "com.tsits.tsits_webrtc" //你的包名
//        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
//    }
//
//    private val mServiceConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName, service: IBinder) {
//            Log.e(TAG, "service connected")
//            data = aidl_Data.Stub.asInterface(service)
//            mBound = true
//            if (data != null) {
//                try {
//                    list = data!!.data as ArrayList<String>
//                } catch (e: RemoteException) {
//                    e.printStackTrace()
//                }
//
//            }
//        }
//
//        //未连接服务状态
//        override fun onServiceDisconnected(name: ComponentName) {
//            Log.e(TAG, "service disconnected")
//            mBound = false
//        }
//    }

    //载入Fragment
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        //判断当前Fragment存在不存在
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
            }
            transaction.add(R.id.container, fragment).commit()
        } else {
            transaction.hide(currentFragment!!)
            transaction.show(fragment).commit()
        }
        //更改当前的fragment所指向的值
        currentFragment = fragment
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    //按钮点击事件
    fun whichIsCliclk() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.material_group -> {
                    title = resources.getString(R.string.group)
                    groupFragment?.let { its -> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.feather_message_square -> {
                    title = resources.getString(R.string.message)
                    messageFragment?.let { its -> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_microphone -> {
                    title = resources.getString(R.string.microphone)
                    microphoneFragment?.let { its -> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_map -> {
                    title = resources.getString(R.string.map)
                    mapFragment?.let { its -> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.material_group_work -> {
                    title = resources.getString(R.string.work)
                    workFragment?.let { its -> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }
    }





}
