package com.tsits.tsits_webrtc.fragment;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRouter2;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tsits.tsits_webrtc.R;

import java.io.FileNotFoundException;
import java.util.Set;

import kotlin.jvm.internal.Intrinsics;

import static android.app.Activity.RESULT_OK;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/14 11:15
 */
public class MessageTalkingRoomFragment extends Fragment implements View.OnTouchListener {
    private static final String TAG="MessageTalkingRoomFragment";
    private Dialog dialog;

    private ImageView imageView;
    private TextView talkingroomtextview;
    private EditText talkingroomedittext;
    private ImageButton imageButton19;
    private ImageButton imageButton8;
    private ImageButton imageButton1;
    private ImageButton imageButton;
    private ConstraintLayout constraintLayout1;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_talking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        talkingroomtextview = getActivity().findViewById(R.id.talkingroomtextview);
        imageView = getActivity().findViewById(R.id.imageView);
        talkingroomedittext = getActivity().findViewById(R.id.talkingroomedittext);
        imageButton19 = getActivity().findViewById(R.id.imageButton19);
        imageButton = getActivity().findViewById(R.id.imageButton);
        imageButton8 = getActivity().findViewById(R.id.imageButton8);
        constraintLayout1 = getActivity().findViewById(R.id.constraintLayout1);
        imageButton1 = getActivity().findViewById(R.id.imageButton1);
        MenuPackUp();
        MenuOpen();
        talkingroomtextview.setOnTouchListener(this);
        changeTalkKeyboard();
    }


    private void changeTalkKeyboard() {
        imageView.setImageResource(R.drawable.ic_icon_material_record_voice_over);
        talkingroomtextview.setVisibility(View.GONE);
        talkingroomedittext.setVisibility(View.VISIBLE);
        imageButton19.setVisibility(View.VISIBLE);
        imageButton.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                //创建点击事件
                i++;
                if (i % 2 == 0) {
                    imageView.setImageResource(R.drawable.ic_icon_material_record_voice_over);
                    talkingroomtextview.setVisibility(View.GONE);
                    talkingroomedittext.setVisibility(View.VISIBLE);
                    imageButton19.setVisibility(View.VISIBLE);
                } else {
                    imageView.setImageResource(R.drawable.ic_icon_awesome_keyboard);
                    talkingroomtextview.setVisibility(View.VISIBLE);
                    talkingroomedittext.setVisibility(View.GONE);
                    imageButton19.setVisibility(View.GONE);
                }
            }
        });

//        imageButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectPic();
//            }
//        });
    }

    protected void setDialogStyle(Context context) {
        View dialogContent =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog, null);
         this.dialog= new Dialog(context, R.style.DialogStyle);
//        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(dialogContent);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.setCancelable(false);
        dialog.show();


    }





    private void MenuOpen() {
        imageButton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout1.setVisibility(View.VISIBLE);
            }
        });
    }

    private void MenuPackUp() {
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout1.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                    Log.d(TAG,"is click down");
                setDialogStyle(getContext());
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"is click up");
                dialog.cancel();
                break;
        }
        return true;
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


