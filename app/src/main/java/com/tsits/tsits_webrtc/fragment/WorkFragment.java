package com.tsits.tsits_webrtc.fragment;

import android.content.Intent;
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

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.activity.VoiceCallActivity;
import com.tsits.tsits_webrtc.manager.HardwareInformationUtil;
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback;
import com.tsits.tsits_webrtc.sdk.TSPocVideo;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/13 11:20
 */
public class WorkFragment extends Fragment {

    private HardwareInformationUtil hardwareInformationUtil;

    private Button change_name_button;
    private EditText et_change_name;
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

    }

    private void Init() {
        change_name_button=getActivity().findViewById(R.id.change_name_button);
        et_change_name=getActivity().findViewById(R.id.et_change_name);
        toolbar_navigation_work=getActivity().findViewById(R.id.toolbar_navigation_work);

    }

    private void changeNickName() {
        change_name_button.setOnClickListener(new View.OnClickListener() {
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
}
