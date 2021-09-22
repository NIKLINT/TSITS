package com.tsits.tsits_webrtc.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsits.tsits_webrtc.inter_face.OnItemClickListener;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/22 18:02
 */
public class Stru_FriendInfo_Adapter(Context context, OnItemClickListener mOnItemClickLitener)
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
