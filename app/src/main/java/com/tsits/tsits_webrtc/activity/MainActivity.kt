package com.tsits.tsits_webrtc.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.*
import com.tsits.tsits_webrtc.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_talking.*
import kotlinx.android.synthetic.main.activity_message_talking.toolbar_navigation
import kotlinx.android.synthetic.main.fragment_message.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(GroupFragment())

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.material_group -> {
                    title = resources.getString(R.string.group)
                    loadFragment(GroupFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.feather_message_square -> {
                    title = resources.getString(R.string.message)
                    loadFragment(MessageFragment())

                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_microphone -> {
                    title = resources.getString(R.string.microphone)
                    loadFragment(MicrophoneFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_map -> {
                    title = resources.getString(R.string.map)
                    loadFragment(MapFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.material_group_work -> {
                    title = resources.getString(R.string.work)
                    loadFragment(WorkFragment())

                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }

        toolbar_navigation.setOnClickListener() {
            loadFragment(MessageTalkingRoomFragment())


        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
