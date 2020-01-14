package com.Dzh.todayinformation.base.tools;

import android.view.View;

/**
 * Date: 2020/1/9
 * author: Dzh
 */
public class OnDoubleClickListener implements View.OnClickListener
{

    private  View.OnClickListener mOnClickListener;
    private double old;
    public OnDoubleClickListener(View.OnClickListener onClickListener)
    {
        this.mOnClickListener = onClickListener;
    }

    @Override
    public void onClick(View v)
    {
        long now = System.currentTimeMillis();
        if(now - old > 1000)
        {
            if (mOnClickListener != null)
            {
                mOnClickListener.onClick(v);
            }
        }
        old = now;
    }
}
