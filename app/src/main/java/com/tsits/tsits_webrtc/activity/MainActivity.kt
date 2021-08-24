package com.tsits.tsits_webrtc.activity

import android.app.ProgressDialog.show
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.*
import com.tsits.tsits_webrtc.fragment.*
import com.tsits.tsits_webrtc.service.ChannelService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_message.*


class MainActivity : AppCompatActivity() {

    private var groupFragment: GroupFragment? = null
    private var messageFragment: MessageFragment? = null
    private var microphoneFragment: MicrophoneFragment? = null
    private var mapFragment: MapFragment? = null
    private var workFragment: WorkFragment? = null
    private var currentFragment: Fragment? = null
    private var receiver:MyReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver()
        startService(Intent(this,ChannelService::class.java))

        chip1.setOnClickListener(){
            sendBroadcast("com.TSITS.AudioService.setMode")
        }

        supportActionBar?.hide()  //隐藏顶部状态栏
        groupFragment = GroupFragment()
        messageFragment = MessageFragment()
        microphoneFragment = MicrophoneFragment()
        mapFragment = MapFragment()
        workFragment = WorkFragment()
        groupFragment?.let { its -> loadFragment(its) }

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

    //注册一个广播
    class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context,intent: Intent?) {
            Toast.makeText(context, "接收广播", Toast.LENGTH_SHORT).show()
        }
    }

    fun registerReceiver() {
         receiver=MyReceiver()
        val filter = IntentFilter()
        filter.addAction("123")
        registerReceiver(receiver, filter)
    }


    fun sendBroadcast(action:String) {
        var intent = Intent()
        intent.action = action
        sendBroadcast(intent)
    }



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
        receiver?.let {
            unregisterReceiver(it)
        }
    }


}
