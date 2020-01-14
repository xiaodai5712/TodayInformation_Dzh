package com.Dzh.todayinformation.base.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

/**
 * Date: 2020/1/6
 * author: Dzh
 */
public class AnimationUtil
{
    public static void startTranslationXAnim(View target, float positionStart, float positionEnd, Animator.AnimatorListener listener)
    {

        // X 方向的属性动画
        ObjectAnimator titleAnim = ObjectAnimator.ofFloat(target,"translationX",positionStart,positionEnd);
        titleAnim.setDuration(TimeUnit.SECONDS.toMillis(1));
        titleAnim.start();
        if (listener != null)
        {
            titleAnim.addListener(listener);
        }
    }
}
