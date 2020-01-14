package com.Dzh.todayinformation.main.shanghai.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.Dzh.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

public class ShanghaiAdapter2 extends RecyclerView.Adapter
{


    public ShanghaiAdapter2()
    {

    }

    //  创建View 然后进行缓存 对内存友好
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_cardview, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(view);
        return viewHolder;

    }


    // 绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

    }

    //条目的数量
    @Override
    public int getItemCount()
    {
        return 15;
    }


    // viewHolder是一定要继承来实现的，因为它是一个抽象类
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTv;
        public ImageView mIv;

        public ShanghaiViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

}
