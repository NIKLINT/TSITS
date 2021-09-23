package com.tsits.tsits_webrtc.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tsits.tsits_webrtc.R;

import java.io.FileNotFoundException;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/23 16:58
 */
public class VoiceCallActivity extends AppCompatActivity {
    private ImageView toolbar_navigation_voice_chat;
    private ImageView iv_hand_voice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_voice_chat);
        toolbar_navigation_voice_chat=findViewById(R.id.toolbar_navigation_voice_chat);
        iv_hand_voice=findViewById(R.id.iv_hand_voice);
        turnToVideo();
    }

    private void turnToVideo() {
        toolbar_navigation_voice_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoiceCallActivity.this,VideoCallActivity.class);
                startActivity(intent);
            }
        });
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
                iv_hand_voice.setImageBitmap(bitmap);


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
