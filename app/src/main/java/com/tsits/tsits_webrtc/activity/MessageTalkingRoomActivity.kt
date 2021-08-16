package com.tsits.tsits_webrtc.activity

import android.app.Dialog
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tsits.tsits_webrtc.R
import kotlinx.android.synthetic.main.activity_message_talking.*


class MessageTalkingRoomActivity : AppCompatActivity(), View.OnTouchListener {
    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_talking)
        imageButton.setOnTouchListener(this)
        MenuPackUp()
        MenuOpen()
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
                Toast.makeText(this, "手指上来啦！", Toast.LENGTH_SHORT).show()
                isButtonDown(view)
            }
            MotionEvent.ACTION_UP -> {
                Toast.makeText(this, "手指放开啦！", Toast.LENGTH_SHORT).show()
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


}




