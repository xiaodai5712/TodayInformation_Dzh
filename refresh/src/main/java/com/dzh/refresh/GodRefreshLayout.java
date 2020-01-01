package com.dzh.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

/**
 * Date: 2019/12/26
 * author: Dzh
 */
public class GodRefreshLayout extends LinearLayout
{

    private BaseRefreshManager mRefreshManager;
    private Context mContext;
    private View mHeadView;
    private int mHeadViewHeight;
    private int minHeadViewHeight;// 头部布局最小的一个高度
    private int maxHeadViewHeight;// 头部布局最大的一个高度
    private String TAG = "GodRefreshLayout";
    private RefreshingListener mRefreshingListener; // 正在刷新的回调接口
    private RecyclerView mRecyclerView;

    public GodRefreshLayout(Context context)
    {
        super(context);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context)
    {
        mContext = context;
    }

    // 开启下拉刷新  下拉刷新效果默认
    public void setBaseRefreshManager()
    {
        mRefreshManager = new DefaultRefreshManager(mContext);
        initHeaderView();
    }


    // 刷新完成后的操作
    public  void refreshOver()
    {
        hideView(getHeadViewLayoutParams());
    }

    // 自定义回调接口
    public void setRefreshListener(RefreshingListener refreshListener)
    {
        mRefreshingListener = refreshListener;
    }

    public interface RefreshingListener
    {
        void onRefreshing();
    }
    // 开启下拉刷新 自定义下拉刷新的效果
    public void setBaseRefreshManager(BaseRefreshManager manager)
    {
        mRefreshManager = manager;
        initHeaderView();
    }
    private void initHeaderView()
    {
        setOrientation(VERTICAL);
        mHeadView = mRefreshManager.getHeaderView();
        mHeadView.measure(0,0);
        mHeadViewHeight = mHeadView.getMeasuredHeight();
        minHeadViewHeight = - mHeadViewHeight; // 下拉刷新栏的高度
        maxHeadViewHeight =(int) (mHeadViewHeight * 0.3f);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,mHeadViewHeight);
        params.topMargin = minHeadViewHeight;
        addView(mHeadView,0,params);  // 这个有点迷啊，把参数换成mHeadView就没问题了，用mRefreshManager.getHeaderView()获取到的就不行
    }

    private int downY;
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                downY = (int)event.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE:
            {
                Log.d(TAG, "onTouchEvent: ");
                int moveY = (int) event.getY();
                if(downY == 0)
                {
                    downY = interceptDownY;
                }
                int dy = moveY - downY;
                if (dy > 0)
                {
                    LayoutParams params = getHeadViewLayoutParams();
                    int topMargin = (int) Math.min(dy / 1.8f + minHeadViewHeight, maxHeadViewHeight);

                    if(topMargin <= 0)
                    {
                        float percent = ((-minHeadViewHeight) - (-topMargin)) * 1.0f / (-minHeadViewHeight);
                        mRefreshManager.downRefreshPercent(percent);
                    }
                    if (topMargin < 0 && mCurrentRefreshState != RefreshState.DOWNREFRESH)
                    {
                        mCurrentRefreshState = RefreshState.DOWNREFRESH;
                        // 提示下拉刷新的状态
                        handleRefreshState(mCurrentRefreshState);
                    } else if (topMargin >= 0 && mCurrentRefreshState != RefreshState.RELEASEREFRESH)
                    {
                        mCurrentRefreshState = RefreshState.RELEASEREFRESH;
                        handleRefreshState(mCurrentRefreshState);
                    }
                    // dy/1.8f  是为了实现阻尼效果
                    params.topMargin = topMargin;
                    mHeadView.setLayoutParams(params);
                }
                return true;
            }
            case MotionEvent.ACTION_UP:
            {
                if(handleEventUp(event))
                {
                    return true;
                }
                break;
            }
            default: break;
        }
        return super.onTouchEvent(event);
    }


    private int interceptDownY;
    private int interceptDownX;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                interceptDownY = (int) ev.getY();
                interceptDownX = (int) ev.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                // 1 确定滑动方向，只有上下滑动才会触发
                int dy = (int) (ev.getY() - interceptDownY);
                int dx = (int) (ev.getX() - interceptDownX);
                if(Math.abs(dy) > Math.abs(dx) && dy > 0 && handleChildViewIsTop())
                {
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            {

            }
            default: break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    // 判断子View 是否处于顶端
    private boolean handleChildViewIsTop()
    {
        if(mRecyclerView != null)
        {
            return RefreshScrollingUtil.isRecyclerViewToTop(mRecyclerView);
        }
        return false;
    }

    // 这个方法回调时，可以获取当前ViewGroup子View
    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        View childAt = getChildAt(0);
        if (childAt instanceof RecyclerView)
        {
            mRecyclerView = (RecyclerView) childAt;
        }
    }

    private boolean handleEventUp(MotionEvent event)
    {
        final LayoutParams layoutParams = getHeadViewLayoutParams();
        if(mCurrentRefreshState == RefreshState.DOWNREFRESH)
        {
            hideView(layoutParams);
        }
        else if(mCurrentRefreshState == RefreshState.RELEASEREFRESH)
        {
            // 保持刷新的一个状态
            layoutParams.topMargin = 0;
            mHeadView.setLayoutParams(layoutParams);
            mCurrentRefreshState = RefreshState.REFRESHING;
            handleRefreshState(mCurrentRefreshState);

            if(mRefreshingListener != null)
            {
                mRefreshingListener.onRefreshing();
            }
        }
        return  layoutParams.topMargin > minHeadViewHeight;
    }

    public void hideView(final LayoutParams layoutParams)
    {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.topMargin, minHeadViewHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                mHeadView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                mCurrentRefreshState = RefreshState.IDDLE;
                handleRefreshState(mCurrentRefreshState);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    private LayoutParams getHeadViewLayoutParams()
    {
        return (LayoutParams)mHeadView.getLayoutParams();
    }

    private void handleRefreshState(RefreshState mCurrentRefreshState)
    {
        switch(mCurrentRefreshState)
        {
            case IDDLE:
            {
                mRefreshManager.iddleRefresh();
                break;
            }
            case REFRESHING:
            {
                mRefreshManager.refreshing();
                break;
            }
            case DOWNREFRESH:
            {
                mRefreshManager.downRefresh();
                break;
            }
            case RELEASEREFRESH:
            {
                mRefreshManager.releaseRefresh();
                break;
            }
            default:break;
        }
    }

    private RefreshState mCurrentRefreshState = RefreshState.IDDLE;
    // 定义下拉刷新的状态 依次为  静止 下拉刷新 释放刷新 正在刷新 刷新完成
    private enum RefreshState
    {
        IDDLE,DOWNREFRESH,RELEASEREFRESH,REFRESHING
    }


}
