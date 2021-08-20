package com.tsits.tsits_webrtc.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.*
import com.tsits.tsits_webrtc.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_talking.*
import kotlinx.android.synthetic.main.activity_message_talking.toolbar_navigation
import kotlinx.android.synthetic.main.fragment_message.*


class MainActivity : AppCompatActivity() {

    private var groupFragment: GroupFragment? = null
    private var messageFragment: MessageFragment? = null
    private var microphoneFragment: MicrophoneFragment? = null
    private var mapFragment: MapFragment? = null
    private var workFragment: WorkFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        groupFragment = GroupFragment()
        messageFragment = MessageFragment()
        microphoneFragment = MicrophoneFragment()
        mapFragment = MapFragment()
        workFragment = WorkFragment()
        groupFragment?.let { its-> loadFragment(its) }

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.material_group -> {
                    title = resources.getString(R.string.group)
                    groupFragment?.let { its-> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.feather_message_square -> {
                    title = resources.getString(R.string.message)
                    messageFragment?.let { its-> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_microphone -> {
                    title = resources.getString(R.string.microphone)
                    microphoneFragment?.let { its-> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_map -> {
                    title = resources.getString(R.string.map)
                    mapFragment?.let { its-> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.material_group_work -> {
                    title = resources.getString(R.string.work)
                    workFragment?.let { its-> loadFragment(its) }
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
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
