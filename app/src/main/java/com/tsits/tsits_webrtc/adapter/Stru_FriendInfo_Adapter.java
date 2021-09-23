package com.tsits.tsits_webrtc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.entity.Contant;
import com.tsits.tsits_webrtc.entity.ContantTitle;
import com.tsits.tsits_webrtc.entity.Stru_FriendInfo;
import com.tsits.tsits_webrtc.inter_face.OnItemClickListener;

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/22 18:02
 */
public class Stru_FriendInfo_Adapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> mDataList;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public Stru_FriendInfo_Adapter(RecyclerView recyclerView) {
        this.context = recyclerView.getContext();

    }

    public void setData(List<String> mDataList) {
        if (null != mDataList) {
            this.mDataList.clear();
            this.mDataList.addAll(mDataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position) instanceof Stru_FriendInfo ? 0 : 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new TitleViewHolder(View.inflate(parent.getContext(), R.layout.item_group_title, null));
        } else {
            return new ItemViewHolder(View.inflate(parent.getContext(), R.layout.item_group, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object data = mDataList.get(position);
        if (holder instanceof Stru_FriendInfo_Adapter.TitleViewHolder && data instanceof ContantTitle) {
            ((TitleViewHolder) holder).title.setText(((ContantTitle) data).getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(holder.itemView, position);
                }
            });
        } else if (holder instanceof ItemViewHolder && data instanceof Contant) {
            Glide.with(context).load(((Contant) data).getHeaderImageUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((ItemViewHolder) holder).image);
        }
        ((ItemViewHolder) holder).name.setText(((Contant)data).getName());
        ((ItemViewHolder) holder).phone.setText(((Contant)data).getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(holder.itemView,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView phone;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.tv_nickname_group);
            phone = itemView.findViewById(R.id.tv_deviceID_group);
        }
    }

    public static final class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView id;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_groupname_group);
            id = itemView.findViewById(R.id.tv_groupID_group);
        }
    }
}
