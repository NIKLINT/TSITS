package com.tsits.tsits_webrtc.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.entity.Contant
import com.tsits.tsits_webrtc.entity.ContantTitle
import com.tsits.tsits_webrtc.entity.MessageContant
import kotlinx.android.synthetic.main.item_group.view.*

class MessageContantRvAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDataList: MutableList<Any> = ArrayList()

    private var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerView.ViewHolder {
           return MessageItemViewHolder(View.inflate(parent.context, R.layout.item_message, null))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        if (holder is  MessageItemViewHolder&& data is MessageContant) {
        holder?.image?.let {
            Glide
                .with(context)
                .load(data.messageImageUrl ?: "")
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(it)
        }
        holder?.group?.text = data?.group
        holder?.time?.text = data?.time
    }
    }


    fun setData(dataList: List<Any>) {
        Log.d("message", "---------------->setData")
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    class MessageItemViewHolder : RecyclerView.ViewHolder {
        var image: ImageView? = null
        var group: TextView? = null
        var time: TextView? = null

        constructor(itemView: View) : super(itemView) {
            image = itemView.findViewById(R.id.groupImage)
            group = itemView.findViewById(R.id.group)
            time = itemView.findViewById(R.id.time)
        }
    }


}