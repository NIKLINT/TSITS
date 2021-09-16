package com.tsits.tsits_webrtc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.activity.GroupDetailActivity;
import com.tsits.tsits_webrtc.adapter.ContantRvAdapter;
import com.tsits.tsits_webrtc.entity.Contant;
import com.tsits.tsits_webrtc.entity.ContantTitle;
import com.tsits.tsits_webrtc.inter_face.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/14 10:29
 */
public class GroupFragment extends Fragment implements OnItemClickListener {
    private static final String TAG="GroupFragment";
    ArrayList dataList = new ArrayList<Object>();
    private RecyclerView recyclerView_group;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_group = getActivity().findViewById(R.id.recyclerView_group);
        initRecyclerView();


    }

    public int getCount() {
        return dataList.size();
    }

    public Object getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    /*
     * 绑定列表
     * */
    private void initRecyclerView() {
        List dataList = getData();
        Log.d("message", "---------------->initRecyclerView datalist size:${dataList?.size}");
        ContantRvAdapter adapter = new ContantRvAdapter(getContext(), this);
        recyclerView_group.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL
                , false));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView_group.addItemDecoration(divider);
        recyclerView_group.setAdapter(adapter);
        adapter.setData(dataList);
    }

    /*
     * 取得列表
     * */
    private List<Object> getData() {
        for (int tindex = 0; tindex < 3; tindex++) {
            dataList.add(new ContantTitle("title" + tindex));
            for (int index = 0; index < 5; index++) {
                dataList.add(
                        new Contant(
                                "https://img1.baidu.com/it/u=1502334019,908245671&fm=26&fmt=auto&gp=0.jpg",
                                "name1:"+index,
                                "phone2:"+index
                        )
                );
            }
        }
        return dataList;
    }

    @Override
    public void OnItemClick(@NonNull View view, int position) {
        Toast.makeText(getActivity(), "YOU Click is " + view + position, Toast.LENGTH_SHORT).show();
        turntoGroupDetail(position);
    }

    /*
     * 携带bundle跳转到GroupDetail
     * */
    private void turntoGroupDetail(int position) {
        Intent intent = new Intent(getActivity(), GroupDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("getItem", getItem(position).toString());
//        bundle.putString("getItem", dataList.get(position).toString())
        intent.putExtras(bundle);
        startActivity(intent);
//        startActivityForResult(intent, 101);

    }

}
