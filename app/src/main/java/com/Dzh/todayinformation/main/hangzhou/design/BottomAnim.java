package com.Dzh.todayinformation.main.hangzhou.design;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.Dzh.todayinformation.R;

/**
 * Date: 2019/12/24
 * author: Dzh
 */
public class BottomAnim
{
    public static void show(View show)
    {
        // 展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(show.getContext(),R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }    public static void hide(View gone)
    {
        // 消失的动画  translate 代表位移 alpha代表淡出淡入
        gone.clearAnimation(); // 清除自身动画
        Animation animationGone = AnimationUtils.loadAnimation(gone.getContext(), R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.INVISIBLE);
    }
}
