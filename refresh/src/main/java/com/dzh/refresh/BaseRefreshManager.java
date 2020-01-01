package com.dzh.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Date: 2019/12/26
 * author: Dzh
 */
public abstract class BaseRefreshManager
{

    public LayoutInflater mLayoutInflater;

    public BaseRefreshManager(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public abstract View getHeaderView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void iddleRefresh();

    public abstract void refreshing();
    public abstract void downRefreshPercent(float percent);
}
