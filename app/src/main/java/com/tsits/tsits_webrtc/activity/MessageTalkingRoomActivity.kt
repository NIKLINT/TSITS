package com.tsits.tsits_webrtc.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_talking.*


class MessageTalkingRoomActivity : AppCompatActivity(), View.OnTouchListener{
    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_talking)
        MenuPackUp()
        MenuOpen()
        talkingroomtextview.setOnTouchListener(this)
        changeTalkKeyboard()
    }

    fun changeTalkKeyboard() {
        var i = 0
        imageView.setImageResource(R.drawable.ic_icon_material_record_voice_over)
        talkingroomtextview.visibility=View.GONE
        talkingroomedittext.visibility=View.VISIBLE
        imageButton19.visibility=View.VISIBLE
        imageButton.setOnClickListener {
            //创建点击事件
            i++
            if (i % 2 == 0) {
                imageView.setImageResource(R.drawable.ic_icon_material_record_voice_over)
                talkingroomtextview.visibility=View.GONE
                talkingroomedittext.visibility=View.VISIBLE
                imageButton19.visibility=View.VISIBLE
            } else {
                imageView.setImageResource(R.drawable.ic_icon_awesome_keyboard)
                talkingroomtextview.visibility=View.VISIBLE
                talkingroomedittext.visibility=View.GONE
                imageButton19.visibility=View.GONE

            }
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
        var fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            return
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}




