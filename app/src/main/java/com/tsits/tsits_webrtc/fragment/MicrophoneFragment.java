package com.tsits.tsits_webrtc.fragment;

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
import com.tsits.tsits_webrtc.sdk.TSPocVideo;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/7 17:35
 */
public class MicrophoneFragment extends Fragment implements View.OnTouchListener {

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
        imageView5.setEnabled(true);
        imageView6.setEnabled(true);
        imageView5.setOnTouchListener(this);
        imageView6.setOnTouchListener(this);

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

                    TSPocVideo.getInstance().Call_PttonOrPttoff(true);
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


}

