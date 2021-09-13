package com.tsits.tsits_webrtc.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.sdk.TSPocVideo;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/13 10:59
 */
public class GroupDetailActivity extends AppCompatActivity {
    private ImageView phone_call_detail;
    private ImageView phone_call_detail1;
    private ImageView toolbar_navigation_detail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        InitView();
        sendPhoneCall();
    }

    private void InitView() {
        phone_call_detail = findViewById(R.id.phone_call_detail);
        toolbar_navigation_detail = findViewById(R.id.toolbar_navigation_detail);
    }

    void sendPhoneCall() {
        phone_call_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TSPocVideo.getInstance().Call_Create();
                SetDialog(view);
            }
        });
    }


    /*
    * 设置Dialog
    * */
    protected void SetDialog(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("选择拨号方式");
        final String[] items = {"单拨","组拨"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(GroupDetailActivity.this
                        ,"You choiced this"+items[i],Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
