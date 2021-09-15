package com.tsits.tsits_webrtc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.activity.MessageTalkingRoomActivity;
import com.tsits.tsits_webrtc.adapter.MessageContantRvAdapter;
import com.tsits.tsits_webrtc.entity.MessageContant;
import com.tsits.tsits_webrtc.inter_face.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/15 15:58
 */
public class MessageFragment extends Fragment implements OnItemClickListener {
    private static final String TAG = "MessageFragment";
    ArrayList dataList = new ArrayList<Object>();
    private RecyclerView recyclerView_message;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_message = getActivity().findViewById(R.id.recyclerView_message);
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

    private void initRecyclerView() {
        List dataList = getData();
        MessageContantRvAdapter adapter = new MessageContantRvAdapter(getContext(),this);
        recyclerView_message.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView_message.addItemDecoration(divider);
        recyclerView_message.setAdapter(adapter);
        adapter.setData(dataList);
    }

    /*
     * 取得列表
     * */
    private List<Object> getData() {
        for (int index = 0; index < 9; index++) {
            dataList.add(
                    new MessageContant(
                            "https://img1.baidu.com/it/u=1502334019,908245671&fm=26&fmt=auto&gp=0.jpg",
                            "group:" + index,
                            "time:" + index
                    )
            );
            Log.d("message",
                    "---------------->initRecyclerView datalist size111111:" + dataList.size());
        }
        return dataList;
    }




    @Override
    public void OnItemClick(@NonNull View view, int position) {
        Toast.makeText(getActivity(), "YOU Click is " + view + position, Toast.LENGTH_SHORT).show();
        turnToMessageTalkingRoomActivity(position);
    }

    /*
     * 携带bundle跳转到MessageTalkingRoom
     * */
    private void turnToMessageTalkingRoomActivity(int position) {
        Intent intent = new Intent(getContext(), MessageTalkingRoomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("getItem", getItem(position).toString());
//        bundle.putString("getItem", dataList.get(position).toString())
        intent.putExtras(bundle);
        startActivityForResult(intent, 1002);
        Log.d(TAG, bundle.toString());
    }




}
