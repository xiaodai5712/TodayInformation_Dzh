package com.Dzh.todayinformation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class FullScreenVideoView extends VideoView
{

    // 主要用于直接new出来的对象
    public FullScreenVideoView(Context context)
    {
        super(context);
    }

    // 主要用于XML文件当中，支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    // 也用于XML文件当中，同时支持自定义属性和样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        //  测量模式  测量大小
        int width = getDefaultSize(0,widthMeasureSpec);
        int height = getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
