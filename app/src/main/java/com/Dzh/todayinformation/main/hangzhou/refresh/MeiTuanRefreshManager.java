package com.Dzh.todayinformation.main.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.Dzh.todayinformation.R;
import com.dzh.refresh.BaseRefreshManager;

/**
 * Date: 2019/12/31
 * author: Dzh
 */
public class MeiTuanRefreshManager extends BaseRefreshManager
{
    private ImageView mImageView;
    public MeiTuanRefreshManager(Context context)
    {
        super(context);
    }

    @Override
    public View getHeaderView()
    {
        View inflate = mLayoutInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);
        mImageView = inflate.findViewById(R.id.loading);
        return inflate;
    }

    // 这个回调只会触发一次
    @Override
    public void downRefresh()
    {

    }

    // 刷新释放的时候 图片会变成美团的吉祥物
    @Override
    public void releaseRefresh()
    {
        mImageView.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable mAnimationDrawable = (AnimationDrawable)mImageView.getDrawable();
        mAnimationDrawable.start();
    }

    // 下拉的过程
    @Override
    public void iddleRefresh()
    {
        mImageView.clearAnimation();
        mImageView.setImageResource(R.mipmap.pull_image);
        mImageView.setScaleX(0);
        mImageView.setScaleY(0);
    }

    // 正在刷新的状态，实际上也是一个帧动画
    @Override
    public void refreshing()
    {
        mImageView.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable mAnimationDrawable = (AnimationDrawable)mImageView.getDrawable();
        mAnimationDrawable.start();
    }

    @Override
    public void downRefreshPercent(float percent)
    {
        mImageView.setScaleX(percent);
        mImageView.setScaleY(percent);

    }

}
