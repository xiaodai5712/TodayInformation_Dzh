package com.Dzh.todayinformation.main.hangzhou.adapter;

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
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.Dzh.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.Dzh.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

public class ZhihuAdapter extends RecyclerView.Adapter
{

    private final ArrayList<ShangHaiDetailBean.XiaoHuaBean> mData;


    public ZhihuAdapter(ArrayList<ShangHaiDetailBean.XiaoHuaBean> data)
    {

        mData = data;
    }

    //  创建View 然后进行缓存 对内存友好
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, parent, false);
        // 因为这个布局使用的是LinerLayout 所以
        // 添参数的时候，要把parent添加进去
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
        return viewHolder;
    }


    // 绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ShangHaiDetailBean.XiaoHuaBean xiaoHuaBean = mData.get(position);
        ((ShanghaiViewHolder) holder).mTv.setText(xiaoHuaBean.content);
        ((ShanghaiViewHolder) holder).mIv.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    // viewHolder是一定要继承来实现的，因为它是一个抽象类
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTv;
        public ImageView mIv;

        public ShanghaiViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mIv = itemView.findViewById(R.id.item_shanghai_iv);
        }
    }

}
