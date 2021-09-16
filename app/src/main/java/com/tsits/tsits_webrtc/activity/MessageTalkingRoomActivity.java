package com.tsits.tsits_webrtc.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRouter2;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.activity.MainActivity;
import com.tsits.tsits_webrtc.adapter.RoomMsgAdapter;
import com.tsits.tsits_webrtc.entity.RoomMsg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import kotlin.jvm.internal.Intrinsics;

import static android.app.Activity.RESULT_OK;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/14 11:15
 */
public class MessageTalkingRoomActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private List<RoomMsg> msgList = new ArrayList<>();
    private static final String TAG = "MessageTalkingRoomFragment";
    private int isMenuOpen = 0;
    private Dialog dialog;
    private InputMethodManager inputMethodManager;
    private String recMsg;
    private RoomMsgAdapter adapter;
    private RecyclerView msgRecyclerView;
    private String name;
    private Socket socketSend;
    private String ip = "192.168.1.66";
    private String port = "6666";
    DataInputStream dis;
    DataOutputStream dos;
    boolean isRunning = false;
    private boolean isSend = false;


    private ImageView iv_change_input_mode;
    private ImageView toolbar_navigation;
    private TextView talkingroomtextview;
    private TextView current_group_name;
    private EditText talkingroomedittext;
    private ImageButton btn_menu_popup;
    private ImageButton imageButton8;
    private ImageButton imageButton1;
    private ImageButton ibtn_change_input_mode;
    private ConstraintLayout menu;
    private Handler handler = new Handler(Looper.myLooper()) {//获取当前进程的Looper对象传给handler
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (!recMsg.isEmpty()) {
                addNewMessage(recMsg, RoomMsg.TYPE_RECEIVED);//添加新数据
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_message_talking);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        InitView();
        changeTalkKeyboard();
        MenuOpen();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MessageTalkingRoomActivity.this);
                msgRecyclerView = findViewById(R.id.talking_log);
                msgRecyclerView.setLayoutManager(layoutManager);
                adapter = new RoomMsgAdapter(msgList);
                msgRecyclerView.setAdapter(adapter);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if ((socketSend = new Socket(ip, Integer.parseInt(port))) == null) {
                        Log.d(TAG, "发送了一条消息1");
                    } else {
                        isRunning = true;
                        Log.d(TAG, "发送了一条消息2");
                        dis = new DataInputStream(socketSend.getInputStream());
                        dos = new DataOutputStream(socketSend.getOutputStream());
                        new Thread(new receive(), "接收线程").start();
                        new Thread(new Send(), "发送线程").start();
                    }
                } catch (Exception e) {
                    isRunning = false;
                    e.printStackTrace();
                    Looper.prepare();
                    Toast.makeText(MessageTalkingRoomActivity.this, "连接服务器失败！！！", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    try {
                        socketSend.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    finish();
                }
            }
        }).start();
    }

    private void InitView() {
        talkingroomtextview = findViewById(R.id.talkingroomtextview);
        current_group_name = findViewById(R.id.current_group_name);
        iv_change_input_mode = findViewById(R.id.iv_change_input_mode);
        talkingroomedittext = findViewById(R.id.talkingroomedittext);
        toolbar_navigation = findViewById(R.id.toolbar_navigation);
        btn_menu_popup = findViewById(R.id.btn_menu_popup);
        ibtn_change_input_mode = findViewById(R.id.ibtn_change_input_mode);
        imageButton8 = findViewById(R.id.imageButton8);
        menu = findViewById(R.id.menu);
        imageButton1 = findViewById(R.id.imageButton1);
        talkingroomtextview.setOnTouchListener(this);
        imageButton8.setOnClickListener(this);
        btn_menu_popup.setOnClickListener(this);
        toolbar_navigation.setOnClickListener(this);
    }

    public void addNewMessage(String msg, int type) {
        RoomMsg message = new RoomMsg(msg, type);
        msgList.add(message);
        adapter.notifyItemInserted(msgList.size() - 1);
        msgRecyclerView.scrollToPosition(msgList.size() - 1);
    }

    class receive implements Runnable {
        public void run() {
            recMsg = "";
            while (isRunning) {
                try {
                    recMsg = dis.readUTF();
                    Log.d(TAG, "收到了一条消息" + "recMsg: " + recMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(recMsg)) {
                    Log.d(TAG, "inputStream:" + dis);
                    Message message = new Message();
                    message.obj = recMsg;
                    handler.sendMessage(message);
                }
            }
        }
    }


    /*
     * 获取从GroupFragment传入的数据
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 102) {
            Log.d(TAG, "resultCode: " + resultCode + "requestCode: " + requestCode);
            String result = data.getStringExtra("getItem");
            Log.d(TAG, "result " + result);
            current_group_name.setText(result);
        }
    }

    class Send implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                String content = talkingroomedittext.getText().toString();
                Log.d(TAG, "发了一条消息");
                if (!"".equals(content) && isSend) {
                    @SuppressLint("SimpleDateFormat")
                    String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
                    StringBuilder sb = new StringBuilder();
                    sb.append(content).append("\n\n来自：").append(name).append("\n" + date);
                    content = sb.toString();
                    try {
                        dos.writeUTF(content);
                        sb.delete(0, sb.length());
                        Log.d(TAG, "发送了一条消息");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isSend = false;
                    talkingroomedittext.setText("");
                }
            }
        }
    }


    /*
     * 切换键盘输入/语音输入按钮
     * */
    private void changeTalkKeyboard() {
        iv_change_input_mode.setImageResource(R.drawable.ic_icon_material_record_voice_over);
        talkingroomtextview.setVisibility(View.GONE);
        talkingroomedittext.setVisibility(View.VISIBLE);
        btn_menu_popup.setVisibility(View.VISIBLE);
        ibtn_change_input_mode.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View view) {
                //创建点击事件
                i++;
                if (i % 2 == 0) {
                    iv_change_input_mode.setImageResource(R.drawable.ic_icon_material_record_voice_over);
                    talkingroomtextview.setVisibility(View.GONE);
                    talkingroomedittext.setVisibility(View.VISIBLE);
                    btn_menu_popup.setVisibility(View.VISIBLE);
                } else {
                    iv_change_input_mode.setImageResource(R.drawable.ic_icon_awesome_keyboard);
                    talkingroomtextview.setVisibility(View.VISIBLE);
                    talkingroomedittext.setVisibility(View.GONE);
                    btn_menu_popup.setVisibility(View.GONE);
                    menu.setVisibility(View.GONE);
                    inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(talkingroomedittext.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    /*
     * 设置dialog的style参数
     * */
    protected void setDialogStyle(Context context) {
        View dialogContent =
                LayoutInflater.from(context).inflate(R.layout.fragment_dialog, null);
        this.dialog = new Dialog(context, R.style.DialogStyle);
//        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(dialogContent);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.setCancelable(false);
        dialog.show();
    }

    /*
    打开菜单并关闭输入法
    */
    private void MenuOpen() {
        btn_menu_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(talkingroomedittext.getWindowToken()
                        , InputMethodManager.HIDE_NOT_ALWAYS);
                menu.setVisibility(View.VISIBLE);
                isMenuOpen = 1;
                talkingroomedittext.setFocusable(true);
            }
        });
        talkingroomedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.GONE);
                Log.d(TAG, "menu.getVisibility(5)-----------" + menu.getVisibility());
            }
        });

    }


    /*
     * 讲话弹出dialog的按压监听
     * */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (view.getId() == talkingroomtextview.getId()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "is click down");
                    setDialogStyle(this);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d(TAG, "is click up");
                    dialog.cancel();
                    break;
            }
        }
        return true;
    }

    /*
    * 点击事件
    * 发送信息按钮、菜单栏显示与隐藏
    * */
    @Override
    public void onClick(View view) {
        String content = talkingroomedittext.getText().toString();
        @SuppressLint("SimpleDateFormat")
        String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(content).append("\n\n" + date);
        content = sb.toString();
        if (!"".equals(content)) {
            RoomMsg msg = new RoomMsg(content, RoomMsg.TYPE_SENT);
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size() - 1);
            msgRecyclerView.scrollToPosition(msgList.size() - 1);
            isSend = true;
        }
        sb.delete(0, sb.length());

        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.GONE);
            }
        });

        btn_menu_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMenuOpen++;
                if (isMenuOpen % 2 == 0) {
                    menu.setVisibility(View.GONE);
                } else {
                    menu.setVisibility(View.VISIBLE);
                }

            }
        });
    }


//    /**
//     * 打开本地相册选择图片
//     */
//    private void selectPic(){
//        //intent可以应用于广播和发起意图，其中属性有：ComponentName,action,data等
//        Intent intent=new Intent();
//        intent.setType("image/*");
////        intent.setType("audio/*"); //选择音频
////        intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）
////        intent.setType("video/*;image/*");//同时选择视频和图片
//        //action表示intent的类型，可以是查看、删除、发布或其他情况；我们选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
//        //类型的内容给你选择
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        //如果第二个参数大于或等于0，那么当用户操作完成后会返回到本程序的onActivityResult方法
//        startActivityForResult(intent, 1);
//    }
//    /**
//     *把用户选择的图片显示在imageview中
//     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        //用户操作完成，结果码返回是-1，即RESULT_OK
//        if(resultCode==RESULT_OK){
//            //获取选中文件的定位符
//            Uri uri = data.getData();
//            Log.e("uri", uri.toString());
//            //使用content的接口
//            ContentResolver cr = this.getContext().getContentResolver();
//            try {
//                //获取图片
//                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//                image.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                Log.e("Exception", e.getMessage(),e);
//            }
//        }else{
//            //操作错误或没有选择图片
//            Log.i("MainActivtiy", "operation error");
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}


