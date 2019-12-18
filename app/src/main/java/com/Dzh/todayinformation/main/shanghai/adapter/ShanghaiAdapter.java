package com.Dzh.todayinformation.main.shanghai.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.Dzh.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

public class ShanghaiAdapter extends RecyclerView.Adapter
{

    private final ArrayList<ShanghaiBean> mData;
    private final Activity mContext;
    private  boolean mIsHor;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    public ShanghaiAdapter(Activity context,ArrayList<ShanghaiBean> data,boolean isHor)
    {
        recycledViewPool = new RecyclerView.RecycledViewPool();
        mData = data;
        mContext = context;
        mIsHor = isHor;
    }

    //  创建View 然后进行缓存 对内存友好
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        if(viewType == ShanghaiBean.IShangshaiItemType.VERTICAL)
        {

            if(mIsHor)
            {
                Log.d( "onCreateViewHolder: ","VERTICAL");
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, parent,false);
            // 因为这个布局使用的是LinerLayout 所以
            // 添参数的时候，要把parent添加进去
            ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(view);
            return viewHolder;
        }
        else if(viewType == ShanghaiBean.IShangshaiItemType.HORIZONTAL)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment_rv, null);
            ShanghaiViewHolderRv viewHolder = new ShanghaiViewHolderRv(view);
            return viewHolder;
        }
        return null;
    }




    // 绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ShanghaiBean shanghaiBean = mData.get(position);

        if(holder instanceof  ShanghaiViewHolder)
        {
            ((ShanghaiViewHolder)holder).mTv.setText(shanghaiBean.getmDec());
            ((ShanghaiViewHolder)holder).mIv.setVisibility(shanghaiBean.isShowImg()? View.VISIBLE:View.GONE);
            ((ShanghaiViewHolder)holder).itemView.setTag(position);
        }
        else if(holder instanceof  ShanghaiViewHolderRv)
        {
            ((ShanghaiViewHolderRv)holder).mRecyclerView.setAdapter(new ShanghaiAdapter(mContext,shanghaiBean.getData(),true));
        }

    }

    //条目的数量
    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return mData.get(position).getmItemType();
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
            this.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ShanghaiDetailActivity.start_5_0(mContext,mTv);
                }
            });
        }
    }

    public class ShanghaiViewHolderRv extends RecyclerView.ViewHolder
    {
        public RecyclerView mRecyclerView;

        public ShanghaiViewHolderRv(@NonNull View itemView)
        {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.item_shanghai_rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setRecycledViewPool(recycledViewPool);
        }
    }
}
