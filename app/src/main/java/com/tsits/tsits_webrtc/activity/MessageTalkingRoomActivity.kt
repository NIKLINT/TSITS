package com.tsits.tsits_webrtc.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_talking.*


class MessageTalkingRoomActivity : AppCompatActivity(), View.OnTouchListener {
    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_talking)
        MenuPackUp()
        MenuOpen()
        imageButton.setOnTouchListener(this)
        bottomNavigation1.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.material_group -> {
                    title = resources.getString(R.string.group)
                    findFragment(GroupFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.feather_message_square -> {
                    title = resources.getString(R.string.message)
                    findFragment(MessageFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_microphone -> {
                    title = resources.getString(R.string.microphone)
                    findFragment(MicrophoneFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.awesome_map -> {
                    title = resources.getString(R.string.map)
                    findFragment(MapFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.material_group_work -> {
                    title = resources.getString(R.string.work)
                    findFragment(WorkFragment())

                    return@setOnNavigationItemSelectedListener true
                }


            }
            false
        }
    }



    protected fun SetDialogStyle(context: Context) {
        val dialogContent =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog, null)
        dialog = Dialog(context, R.style.DialogStyle)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.setContentView(dialogContent)
        dialog.window?.decorView?.setPadding(0, 0, 0, 0)
        dialog.window?.attributes?.width = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.setCancelable(false)
        dialog.show()

    }


    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                isButtonDown(view)
            }
            MotionEvent.ACTION_UP -> {
                isButtonUp(view)
            }
        }
        return true
    }

    fun isButtonDown(view: View?) {
        AlertDialog.Builder(this).apply {
            SetDialogStyle(context)
        }
    }

    fun isButtonUp(view: View?) {
        dialog.cancel()
    }

    fun MenuOpen() {
        imageButton19.setOnClickListener() {
            constraintLayout1.visibility = View.VISIBLE

        }
    }

    fun MenuPackUp() {
        imageButton8.setOnClickListener() {
            constraintLayout1.visibility = View.GONE
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun findFragment(fragment: Fragment) {
        var fragment=supportFragmentManager.findFragmentById(R.id.container)
        if(fragment==null){
            return
        }
    }




    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}




