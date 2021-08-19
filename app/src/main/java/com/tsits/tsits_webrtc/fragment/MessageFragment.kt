package com.tsits.tsits_webrtc.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TwoLineListItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.activity.MessageTalkingRoomFragment
import com.tsits.tsits_webrtc.adapter.MessageContantRvAdapter
import com.tsits.tsits_webrtc.entity.MessageContant
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_message, null)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    @SuppressLint("ResourceType")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        toolbar_navigation.setOnClickListener() {


        }
    }



    private fun initRecyclerView() {
        var dataList = getData()
        Log.d("message", "---------------->initRecyclerView datalist size:${dataList?.size}")
        val adapter = MessageContantRvAdapter(context!!)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter
        adapter.setData(dataList)
    }

    private fun getData(): List<Any> {
        val dataList = ArrayList<Any>()
        for (index in 0..9) {
            dataList.add(
                MessageContant(
                    "https://img1.baidu.com/it/u=1502334019,908245671&fm=26&fmt=auto&gp=0.jpg",
                    "group:$index",
                    "time:$index"
                )
            )
            Log.d("message",
                "---------------->initRecyclerView datalist size111111:${dataList?.size}")
        }
        return dataList
    }

}