package com.tsits.tsits_webrtc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.entity.RoomMsg;

import java.util.List;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/16 13:49
 */
public class RoomMsgAdapter extends RecyclerView.Adapter<RoomMsgAdapter.RoomMsgViewHolder> {
    private List<RoomMsg> mMsgList;
    private Context context;

    public RoomMsgAdapter(List<RoomMsg> msgList) {
        this.mMsgList = msgList;
        this.context=context;
    }


    @NonNull
    @Override
    public RoomMsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_message, parent, false);
        return new RoomMsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomMsgViewHolder holder, int position) {
        RoomMsg msg = mMsgList.get(position);
        if (msg.getType() == RoomMsg.TYPE_RECEIVED) {
//            Glide.with(context).load(msg.rightHand).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.rightHand);
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == RoomMsg.TYPE_SENT) {
//            Glide.with(context).load(msg.rightHand).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.rightHand);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }
    }


    @Override
    public int getItemCount() {
        return mMsgList.size();
    }


    static class RoomMsgViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        ImageView leftHand;
        ImageView rightHand;

        public RoomMsgViewHolder(@NonNull View view) {
            super(view);
            leftLayout = itemView.findViewById(R.id.layout_left);
            rightLayout = itemView.findViewById(R.id.layout_right);
            leftMsg = itemView.findViewById(R.id.msg_left);
            rightMsg = itemView.findViewById(R.id.msg_right);
            leftHand = itemView.findViewById(R.id.iv_hand_left);
            rightHand = itemView.findViewById(R.id.iv_hand_right);

        }
    }


}
