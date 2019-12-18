package com.Dzh.todayinformation.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dzh.mvp.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends LifeCircleMvpFragment
{


    protected Context mContext;
    private String TAG = "测试BaseFragment";

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View mView = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if(annotation != null)
        {
            int mainLayoutId = annotation.mainLayoutId();
            if(mainLayoutId > 0)
            {
                mView = initFragment(inflater,mainLayoutId);
                Log.d(TAG, "onCreateView: ");
                bindView(mView);
                afterBindView();
            }
            else
            {
                throw new RuntimeException("mainLayoutId < 0");
            }
        }
        else
        {
            throw new RuntimeException("annotation = null");
        }
        return mView;
    }

    private View initFragment(LayoutInflater inflater,int mainLayoutId)
    {
        return inflater.inflate(mainLayoutId,null);
    }


    // 模板方法设计模式
    public abstract void afterBindView();

    // View 的依赖注入绑定
    private void bindView(View mView)
    {
        ButterKnife.bind(this,mView);
    }
}
