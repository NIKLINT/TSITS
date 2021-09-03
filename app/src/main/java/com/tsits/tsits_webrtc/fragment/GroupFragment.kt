package com.tsits.tsits_webrtc.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsits.pocvideosdk.Stru_FriendInfo
import com.tsits.pocvideosdk.Stru_GroupInfo
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.activity.GroupDetailActivity
import com.tsits.tsits_webrtc.adapter.ContantRvAdapter
import com.tsits.tsits_webrtc.entity.Contant
import com.tsits.tsits_webrtc.entity.ContantTitle
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback
import com.tsits.tsits_webrtc.sdk.TSPocVideo
import kotlinx.android.synthetic.main.fragment_group.*


class GroupFragment : Fragment() {
//    private lateinit var stru_friendInfoList:List<Stru_FriendInfo>
//    private lateinit var mITSPocVideoCallback: ITSPocVideoCallback
//    private lateinit var stru_groupInfos:List<Stru_GroupInfo>
//    init {
//        getFriendsList()
//        getGroupList()
//    }
//
//    private fun getFriendsList() {
//      return mITSPocVideoCallback.Friend_List(stru_friendInfoList)
//    }
//
//    private fun getGroupList(){
//       return mITSPocVideoCallback.Group_List(stru_groupInfos)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_group, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        turntoLogin()
    }

    private fun turntoLogin() {
        toolbar_navigation_group.setOnClickListener(){
            var intent=Intent(context!!,GroupDetailActivity::class.java)
            context?.startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        var dataList = getData()
        Log.d("message", "---------------->initRecyclerView datalist size:${dataList?.size}")
        val adapter = ContantRvAdapter(context!!)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
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
                        "name1:$index",
                        "phone2:$index"
                    )
                )
            }
        }
        return dataList
    }



}