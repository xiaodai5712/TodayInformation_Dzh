package com.Dzh.todayinformation.main.hangzhou.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * Date: 2019/12/24
 * author: Dzh
 */
public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView>
{
    public BottomShowBehavior(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }


    // 这个方法的回调时机： 即将发生嵌套滚动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type)
    {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    // 发生嵌套滚动的时候回调
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type)
    {
        // 向下滑动
        if((dyConsumed + dyUnconsumed) > 0)
        {
            // 隐藏child
            if(child.getVisibility()== View.VISIBLE)
            {
                BottomAnim.hide(child);
            }
        }

        else
        {
            // 向上滑动 展示child
            if(child.getVisibility()!= View.VISIBLE)
            {
                BottomAnim.show(child);
            }

        }
    }
}
