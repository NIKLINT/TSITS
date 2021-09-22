package com.tsits.tsits_webrtc.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.manager.ITSPocVideoSend;
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback;
import com.tsits.tsits_webrtc.sdk.TSPocVideoSDK;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/13 10:59
 */
public class GroupDetailActivity extends AppCompatActivity {
    private static final String TAG = "GroupDetailActivity";



    private ImageButton phone_call_detail;
    private ImageButton phone_call_detail_background;
    private ImageView toolbar_navigation_detail;
    private TextView name_details;
    private TextView phone_details;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        InitView();
        sendPhoneCall();
    }

    private void InitView() {
//        phone_call_detail = findViewById(R.id.phone_call_detail);
        phone_call_detail_background = findViewById(R.id.phone_call_detail_background);
        phone_call_detail = findViewById(R.id.phone_call_detail);
        phone_call_detail.setImageResource(R.drawable.ic_icon_feather_phone);
        toolbar_navigation_detail = findViewById(R.id.toolbar_navigation_detail);
        phone_details = findViewById(R.id.phone_details);
        name_details = findViewById(R.id.name_details);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d(TAG,"onActivityResult is GO ON");
//        if (resultCode == RESULT_OK && requestCode == 101) {
//            Log.d(TAG, "resultCode: " + resultCode + "requestCode: " + requestCode);
//            String result = data.getStringExtra("getItem");
//            Log.d(TAG, "result "+result);
//            phone_details.setText(result);
//        }
//    }

    /*
     * 设置Dialog
     * */
    protected void SetDialog(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("选择拨号方式");
        final String[] items = {"单拨", "组拨"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==0){

                }else if(i==1){
                    Toast.makeText(GroupDetailActivity.this
                            , "You choiced this1" + items[i], Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
