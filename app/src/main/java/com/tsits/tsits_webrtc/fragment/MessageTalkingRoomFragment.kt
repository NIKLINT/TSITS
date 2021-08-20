package com.tsits.tsits_webrtc.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.R
import kotlinx.android.synthetic.main.activity_message_talking.*

typealias OnBackPressedTypeAlias = () -> Unit

class MessageTalkingRoomFragment : Fragment(), View.OnTouchListener {

    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.activity_message_talking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MenuPackUp()
        MenuOpen()
        talkingroomtextview.setOnTouchListener(this)
        changeTalkKeyboard()
        setOnHandleBackPressed()
    }


    fun changeTalkKeyboard() {
        var i = 0
        imageView.setImageResource(R.drawable.ic_icon_material_record_voice_over)
        talkingroomtextview.visibility = View.GONE
        talkingroomedittext.visibility = View.VISIBLE
        imageButton19.visibility = View.VISIBLE
        imageButton.setOnClickListener {
            //创建点击事件
            i++
            if (i % 2 == 0) {
                imageView.setImageResource(R.drawable.ic_icon_material_record_voice_over)
                talkingroomtextview.visibility = View.GONE
                talkingroomedittext.visibility = View.VISIBLE
                imageButton19.visibility = View.VISIBLE
            } else {
                imageView.setImageResource(R.drawable.ic_icon_awesome_keyboard)
                talkingroomtextview.visibility = View.VISIBLE
                talkingroomedittext.visibility = View.GONE
                imageButton19.visibility = View.GONE

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
        AlertDialog.Builder(activity!!).apply {
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

    /**
     * 解决 Fragment 中 OnBackPressed 事件, 默认结束当前Fragment依附的Activity
     * @param type true:结束当前Activity，false：响应callback回调
     */
    fun Fragment.setOnHandleBackPressed(
        type: Boolean = true,
        callback: OnBackPressedTypeAlias? = null,
    ) {
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (type) {
                        requireActivity().finish()
                    } else {
                        callback?.invoke()
                    }
                }
            })
    }


}

