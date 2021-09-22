package com.tsits.tsits_webrtc.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.adapter.RoomMsgAdapter;
import com.tsits.tsits_webrtc.entity.RoomMsg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/14 11:15
 */
public class MessageTalkingRoomActivity extends AppCompatActivity implements View.OnTouchListener {
    private static final String TAG = "MessageTalkingRoomFragment";
    private List<RoomMsg> msgList = new ArrayList<>();
    private Dialog dialog;//按下语音输入的dialog
    private InputMethodManager inputMethodManager;//输入方法管理
    private RoomMsgAdapter adapter;
    private RecyclerView msgRecyclerView;
    private boolean tof = false;
    private String recMsg;
    private String name;
    private String time;
    //    private Socket socketSend;
//    private String ip = "192.168.1.66";
//    private String port = "6666";
    DataInputStream dis;
    DataOutputStream dos;
    boolean isRunning = false;
    private boolean isSend = false;


    private ImageView iv_change_input_mode;
    private ImageView iv_hand_right;
    private ImageView toolbar_navigation;
    private TextView talkingroomtextview;
    private TextView current_group_name;
    private EditText talkingroomedittext;
    private ImageButton btn_menu_popup;
    private ImageButton menu_fold;
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
        InitView();
        InitMethod();
    }

    private void InitMethod() {
//        inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.showSoftInput(talkingroomedittext, InputMethodManager.SHOW_FORCED);
//        Intent intent = getIntent();
//        name = intent.getStringExtra("name");
        changeInputMode();//切换输入模式
        MenuOrKeyBoard();//打开关闭菜单
        sendMessage();//发送信息
        MenuItemClick();//菜单Item点击事件


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MessageTalkingRoomActivity.this);
                msgRecyclerView.setLayoutManager(layoutManager);
                adapter = new RoomMsgAdapter(msgList);
                msgRecyclerView.setAdapter(adapter);
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if ((socketSend = new Socket(ip, Integer.parseInt(port))) == null) {
//                        Log.d(TAG, "发送了一条消息1");
//                    } else {
//                        isRunning = true;
//                        Log.d(TAG, "发送了一条消息2");
//                        dis = new DataInputStream(socketSend.getInputStream());//从服务器取
//                        dos = new DataOutputStream(socketSend.getOutputStream());//发送到服务器
//                        new Thread(new receive(), "接收线程").start();
//                        new Thread(new Send(), "发送线程").start();
//                    }
//                } catch (Exception e) {
//                    isRunning = false;
//                    e.printStackTrace();
//                    Looper.prepare();
//                    Toast.makeText(MessageTalkingRoomActivity.this, "连接服务器失败！！！", Toast.LENGTH_SHORT).show();
//                    Looper.loop();
//                    try {
//                        socketSend.close();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                    finish();
//                }
//            }
//        }).start();
    }

    /*
    * 初始化视图
    * */
    private void InitView() {
        iv_hand_right=findViewById(R.id.iv_hand_right);
        msgRecyclerView = findViewById(R.id.talking_log);//聊天列表
        talkingroomtextview = findViewById(R.id.talkingroomtextview);//说话按钮
        talkingroomtextview.setOnTouchListener(this);
        current_group_name = findViewById(R.id.current_group_name);//此聊天组的组名
        talkingroomedittext = findViewById(R.id.talkingroomedittext);//聊天输入框
        talkingroomedittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if(menu.getVisibility() == View.VISIBLE) {
                        menu.setVisibility(View.GONE);
                    }
                }else{
                        if(menu.getVisibility()==View.GONE){
                            menu.setVisibility(View.VISIBLE);
                        }
                }
            }
        });
        toolbar_navigation = findViewById(R.id.toolbar_navigation_message_talking_room);//右上角toolbar
        btn_menu_popup = findViewById(R.id.btn_menu_popup);//回形针弹出菜单按钮
        ibtn_change_input_mode = findViewById(R.id.ibtn_change_input_mode);//切换输入模式按钮
        iv_change_input_mode = findViewById(R.id.iv_change_input_mode);//切换输入模式按钮中的图样
        menu_fold = findViewById(R.id.menu_fold);//菜单折叠按钮
        menu = findViewById(R.id.menu);//菜单
        imageButton1 = findViewById(R.id.imageButton1);//菜单里图片按钮

        //首界面文字输入为先
        iv_change_input_mode.setImageResource(R.drawable.ic_icon_material_record_voice_over);
        talkingroomtextview.setVisibility(View.GONE);
        talkingroomedittext.setVisibility(View.VISIBLE);
        btn_menu_popup.setVisibility(View.VISIBLE);
    }

    void MenuItemClick(){
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPic();//菜单Item项
            }
        });

    }


    /*
     * 在信息列表中插入信息
     * */
    public void addNewMessage(String msg, int type) {
        RoomMsg message = new RoomMsg(msg, type);
        msgList.add(message);//msgList新增信息
        adapter.notifyItemInserted(msgList.size() - 1);//通知item插入
        msgRecyclerView.scrollToPosition(msgList.size() - 1);//RecyclerView视图定位，新增一项信息视图就移动到新信息上
    }



//    class receive implements Runnable {
//        public void run() {
//            recMsg = "";
//            while (isRunning) {
//                try {
//                    recMsg = dis.readUTF();
//                    Log.d(TAG, "收到了一条消息" + "recMsg: " + recMsg);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (!TextUtils.isEmpty(recMsg)) {
//                    Log.d(TAG, "inputStream:" + dis);
//                    Message message = new Message();
//                    message.obj = recMsg;
//                    handler.sendMessage(message);
//                }
//            }
//        }
//    }


//    class Send implements Runnable {
//        @Override
//        public void run() {
//            while (isRunning) {
//                String content = talkingroomedittext.getText().toString();
//                Log.d(TAG, "发了一条消息");
//                if (!"".equals(content) && isSend) {
//                    @SuppressLint("SimpleDateFormat")
//                    String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
//                    StringBuilder sb = new StringBuilder();
//                    sb.append(content).append("\n\n来自：").append(name).append("\n" + date);
//                    Log.d(TAG,name+date);
//                    content = sb.toString();
//                    try {
//                        dos.writeUTF(content);
//                        sb.delete(0, sb.length());
//                        Log.d(TAG, "发送了一条消息");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    isSend = false;
//                    talkingroomedittext.setText("");
//                }
//            }
//        }
//    }

    /*
     * 获取从GroupFragment传入的数据
     * */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == 102) {
//            Log.d(TAG, "resultCode: " + resultCode + "requestCode: " + requestCode);
//            String result = data.getStringExtra("getItem");
//            Log.d(TAG, "result " + result);
//            current_group_name.setText(result);
//        }
//    }


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
        dialog.setCancelable(false);
        dialog.show();
    }

    /*
     *点击回形针按钮弹出/隐藏菜单
     */
    private void MenuOrKeyBoard() {
        btn_menu_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menu.getVisibility() == View.GONE) {
                    inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(talkingroomedittext.getWindowToken()
                            , InputMethodManager.HIDE_NOT_ALWAYS);
                    talkingroomedittext.setFocusable(true);
                    menu.setVisibility(View.VISIBLE);
                } else if (menu.getVisibility() == View.VISIBLE) {
                    menu.setVisibility(View.GONE);
                }
            }
        });
        menu_fold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.GONE);
            }
        });
        talkingroomedittext.setOnClickListener(new View.OnClickListener() {//点击输入框弹出输入法并关闭菜单
            @Override
            public void onClick(View view) {
//                inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                inputMethodManager.showSoftInput(talkingroomedittext, InputMethodManager.SHOW_FORCED);
                menu.setVisibility(View.GONE);
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
     * 发送信息
     * */
    void sendMessage() {
        talkingroomedittext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && keyEvent.getAction()
                        == android.view.KeyEvent.ACTION_DOWN) {
                    String content = talkingroomedittext.getText().toString();
                    @SuppressLint("SimpleDateFormat")
                    String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                    StringBuilder sb = new StringBuilder();
                    sb.append(content).append("\n\n" + date);
                    content = sb.toString();
                    if (!"".equals(content)) {
                        Log.d(TAG, "is send");
                        RoomMsg msg = new RoomMsg(content, RoomMsg.TYPE_SENT);
                        msgList.add(msg);
                        adapter.notifyItemInserted(msgList.size() - 1);
                        msgRecyclerView.scrollToPosition(msgList.size() - 1);
                        isSend = true;
                        talkingroomedittext.setText("");
                    }
                    sb.delete(0, sb.length());
                }
                return false;
            }

        });
    }


    /*
     * 切换键盘输入(true)/语音输入(false)实现
     * */
    void changeInputMode() {
        ibtn_change_input_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建点击事件
                if (tof == true) {
                    Log.d(TAG, "1--------------------------------->  ibtn_change_input_mode is click!!!");
                    iv_change_input_mode.setImageResource(R.drawable.ic_icon_material_record_voice_over);
                    talkingroomtextview.setVisibility(View.GONE);
                    talkingroomedittext.setVisibility(View.VISIBLE);
                    btn_menu_popup.setVisibility(View.VISIBLE);
                    tof = false;
                } else if (tof == false) {
                    Log.d(TAG, "2--------------------------------->  ibtn_change_input_mode is click!!!");
                    inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(talkingroomedittext.getWindowToken()
                            , InputMethodManager.HIDE_NOT_ALWAYS);
                    iv_change_input_mode.setImageResource(R.drawable.ic_icon_awesome_keyboard);
                    talkingroomtextview.setVisibility(View.VISIBLE);
                    talkingroomedittext.setVisibility(View.GONE);
                    btn_menu_popup.setVisibility(View.GONE);
                    menu.setVisibility(View.GONE);
                    tof = true;
                } else {
                    finish();
                }
            }
        });
    }


    /**
     * 打开本地相册选择图片
     */
    private void selectPic(){
        //intent可以应用于广播和发起意图，其中属性有：ComponentName,action,data等
        Intent intent=new Intent();
        intent.setType("image/*");
//        intent.setType("audio/*"); //选择音频
//        intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）
//        intent.setType("video/*;image/*");//同时选择视频和图片
        //action表示intent的类型，可以是查看、删除、发布或其他情况；我们选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
        //类型的内容给你选择
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //如果第二个参数大于或等于0，那么当用户操作完成后会返回到本程序的onActivityResult方法
        startActivityForResult(intent, 1);
    }
    /**
     *把用户选择的图片显示在imageview中
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //用户操作完成，结果码返回是-1，即RESULT_OK
        if(resultCode==RESULT_OK){
            //获取选中文件的定位符
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            //使用content的接口
            ContentResolver cr = this.getContentResolver();
            try {
                //获取图片
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                iv_hand_right.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }else{
            //操作错误或没有选择图片
            Log.i("MainActivtiy", "operation error");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


