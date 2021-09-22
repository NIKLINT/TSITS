package com.tsits.tsits_webrtc.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback;

import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/7 17:35
 */
public class MicrophoneFragment extends Fragment implements View.OnTouchListener {
    private ITSPocVideoCallback itsPocVideoCallback;

    private ImageView imageHead;
    private ImageView tool_navigation;
    private ImageView imageView5 = null;
    private ImageView imageView6 = null;
    private ImageView iv_talking_state = null;
    private ImageView iv_green_background = null;
    private ImageView iv_red_background = null;
    private ImageView iv_red_background2 = null;
    private ImageView iv_green_background2 = null;
    private TextView tv_talking_state = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_microphone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView5 = getActivity().findViewById(R.id.imageView5);
        imageView6 = getActivity().findViewById(R.id.imageView6);
        iv_green_background = getActivity().findViewById(R.id.iv_green_background);
        iv_red_background = getActivity().findViewById(R.id.iv_red_background);
        iv_red_background2 = getActivity().findViewById(R.id.iv_red_background2);
        iv_green_background2 = getActivity().findViewById(R.id.iv_green_background2);
        tv_talking_state = getActivity().findViewById(R.id.tv_talking_state);
        iv_talking_state = getActivity().findViewById(R.id.iv_talking_state);
        imageHead=getActivity().findViewById(R.id.imageView4);
        tool_navigation=getActivity().findViewById(R.id.toolbar_navigation3);
        imageView5.setEnabled(true);
        imageView6.setEnabled(true);
        imageView5.setOnTouchListener(this);
        imageView6.setOnTouchListener(this);

        tool_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseHeadImageFromGallery();
            }
        });

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == imageView5.getId()) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    iv_talking_state.setVisibility(View.VISIBLE);
                    tv_talking_state.setVisibility(View.VISIBLE);
                    iv_green_background.setVisibility(View.VISIBLE);
                    iv_green_background2.setVisibility(View.VISIBLE);
                    Log.d("message", "IS CLICK!!!!---------------------------->");

//                    TSPocVideo.getInstance().Call_PttonOrPttoff(true);

                    break;
                case MotionEvent.ACTION_UP:
                    iv_talking_state.setVisibility(View.GONE);
                    tv_talking_state.setVisibility(View.GONE);
                    iv_green_background.setVisibility(View.GONE);
                    iv_green_background2.setVisibility(View.GONE);
//                    TSPocVideo.getInstance().Call_PttonOrPttoff(false);
                    break;
            }
            return true;
        } else if (view.getId() == imageView6.getId()) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    iv_talking_state.setVisibility(View.VISIBLE);
                    tv_talking_state.setVisibility(View.VISIBLE);
                    iv_red_background.setVisibility(View.VISIBLE);
                    iv_red_background2.setVisibility(View.VISIBLE);
//                    TSPocVideo.getInstance().Call_PttonOrPttoff(true);
                    break;
                case MotionEvent.ACTION_UP:
                    iv_talking_state.setVisibility(View.GONE);
                    tv_talking_state.setVisibility(View.GONE);
                    iv_red_background.setVisibility(View.GONE);
                    iv_red_background2.setVisibility(View.GONE);
//                    TSPocVideo.getInstance().Call_PttonOrPttoff(false);
                    break;
            }
        }
        return true;
    }

    /**
     * 打开本地相册选择图片
     */
    private void choseHeadImageFromGallery(){
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
            ContentResolver cr = this.getContext().getContentResolver();
            try {
                //获取图片
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                imageHead.setImageBitmap(bitmap);
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

