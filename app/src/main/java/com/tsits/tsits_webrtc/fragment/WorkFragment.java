package com.tsits.tsits_webrtc.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tsits.pocvideosdk.TSPocVideo;
import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.activity.VoiceCallActivity;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/13 11:20
 */
public class WorkFragment extends Fragment {


    public int locationID = 0;
    public String version;

    private Button btn_change_name;
    private Button btn_id_work;
    private EditText et_change_name;
    private EditText et_id_word;
    private EditText et_versionCode_work;
    private ImageView toolbar_navigation_work;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init();
        turnToVoiceCallActivity();
        changeNickName();
        setLocationId();
        getVersionCode(getContext());
    }

    private void Init() {
        btn_change_name = getActivity().findViewById(R.id.btn_change_name);
        btn_id_work = getActivity().findViewById(R.id.btn_id_work);
        et_change_name = getActivity().findViewById(R.id.et_change_name);
        et_id_word = getActivity().findViewById(R.id.et_id_word);
        et_versionCode_work=getActivity().findViewById(R.id.et_versionCode_work);
        toolbar_navigation_work = getActivity().findViewById(R.id.toolbar_navigation_work);

    }

    private void changeNickName() {
        btn_change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TSPocVideo.getInstance().Device_ChangeInfo(et_change_name.getText().toString());
            }
        });
    }

    private void turnToVoiceCallActivity() {
        toolbar_navigation_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VoiceCallActivity.class));
            }
        });
    }

    /*设置设备通信ID*/
    void setLocationId() {
        btn_id_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationID = Integer.parseInt(et_id_word.getText().toString());
            }
        });
    }

    /*获取软件版本号*/
    public  String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version = packageInfo.versionName;
        et_versionCode_work.setText(version+"");
        return version;
    }

}
