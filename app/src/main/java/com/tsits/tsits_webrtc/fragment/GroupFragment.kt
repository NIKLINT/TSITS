package com.tsits.tsits_webrtc.fragment


import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsits.tsits_webrtc.adapter.ContantRvAdapter
import kotlinx.android.synthetic.main.fragment_group.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.activity.GroupDetailActivity
import com.tsits.tsits_webrtc.activity.LoginActivity
import com.tsits.tsits_webrtc.entity.Contant
import com.tsits.tsits_webrtc.entity.ContantTitle

class GroupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        turntoLogin()
    }

    private fun turntoLogin() {
        toolbar_navigation.setOnClickListener(){
            var intent=Intent(context!!,GroupDetailActivity::class.java)
            context?.startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        var dataList = getData()
        Log.d("message", "---------------->initRecyclerView datalist size:${dataList?.size}")
        val adapter = ContantRvAdapter(context!!)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.setData(dataList)
    }

    private fun getData(): List<Any> {
        val dataList = ArrayList<Any>()
        for (tindex in 0 until 3) {
            dataList.add(ContantTitle("title$tindex"))
            for (index in 0 until 5) {
                dataList.add(
                    Contant(
                        "https://img1.baidu.com/it/u=1502334019,908245671&fm=26&fmt=auto&gp=0.jpg",
                        "name:$index",
                        "phone:$index"
                    )
                )
            }
        }
        return dataList
    }
}