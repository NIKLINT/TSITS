package com.tsits.tsits_webrtc.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.`interface`.OnItemClickListener
import com.tsits.tsits_webrtc.activity.GroupDetailActivity
import com.tsits.tsits_webrtc.adapter.ContantRvAdapter
import com.tsits.tsits_webrtc.entity.Contant
import com.tsits.tsits_webrtc.entity.ContantTitle
//import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback
//import com.tsits.tsits_webrtc.sdk.TSPocVideo
import kotlinx.android.synthetic.main.fragment_group.*
import java.text.FieldPosition


class GroupFragment : Fragment(), OnItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_group, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    /*
    * 绑定列表
    * */
    private fun initRecyclerView() {
        var dataList = getData()
        Log.d("message", "---------------->initRecyclerView datalist size:${dataList?.size}")
        val adapter = ContantRvAdapter(context!!, this)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter
        adapter.setData(dataList)
    }

    /*
    * 取得列表
    * */
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


    /*
        * 跳转到GroupDetail
        * */
    private fun turntoGroupDetail(position: Int) {

        val intent = Intent(context!!, GroupDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putString(getText().toString())
        startActivityForResult(intent, 1001)
    }

    override fun OnItemClick(view: View, position: Int) {
        Toast.makeText(activity, "YOU Click is $view $position", Toast.LENGTH_SHORT).show()
        turntoGroupDetail(position)
    }

}