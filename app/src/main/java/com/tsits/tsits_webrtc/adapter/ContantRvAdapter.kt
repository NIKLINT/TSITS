package com.tsits.tsits_webrtc.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.entity.Contant
import com.tsits.tsits_webrtc.inter_face.OnItemClickListener
import com.tsits.tsits_webrtc.entity.ContantTitle

class ContantRvAdapter(context: Context,val mOnItemClickLitener: OnItemClickListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDataList: MutableList<Any> = ArrayList()

    private var context: Context = context

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position] is ContantTitle) 0 else 1
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            TitleViewHolder(View.inflate(parent.context, R.layout.item_group_title, null))
        } else {
            ItemViewHolder(View.inflate(parent.context, R.layout.item_group, null))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        if (holder is TitleViewHolder && data is ContantTitle) {
            holder?.title?.text = data?.title
            mOnItemClickLitener?.let {
                holder?.itemView?.setOnClickListener {
                    mOnItemClickLitener.OnItemClick(holder?.itemView,position);
                }
            }
        } else if (holder is ItemViewHolder && data is Contant) {
            holder?.image?.let {
                Glide
                    .with(context)
                    .load(data.headerImageUrl ?: "")
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(it)
            }
            holder?.name?.text = data?.name
            holder?.phone?.text = data?.phone.toString()

            mOnItemClickLitener?.let {
                holder?.itemView?.setOnClickListener {
                    mOnItemClickLitener.OnItemClick(holder?.itemView,position);
                }
            }
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

    class ItemViewHolder : RecyclerView.ViewHolder {
        var image: ImageView? = null
        var name: TextView? = null
        var phone: TextView? = null

        constructor(itemView: View) : super(itemView) {
            image = itemView.findViewById(R.id.image)
            name = itemView.findViewById(R.id.tv_nickname_group)
            phone = itemView.findViewById(R.id.tv_deviceID_group)
        }
    }

    class TitleViewHolder : RecyclerView.ViewHolder {
        var title: TextView? = null

        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.title)
        }
    }
}