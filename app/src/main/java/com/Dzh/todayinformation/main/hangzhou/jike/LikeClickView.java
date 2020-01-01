package com.Dzh.todayinformation.main.hangzhou.jike;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.main.tools.SystemUtil;

import java.util.Map;

/**
 * Date: 2019/12/24
 * author: Dzh
 */
public class LikeClickView extends View
{

    private boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap unLikeBitmap;
    private Bitmap shiningBitmap;
    private Paint bitmapPaint;
    private int left;
    private int top;
    private float handScale = 1.0f;
    private int centerY;
    private int centerX;

    public LikeClickView(Context context)
    {
        this(context, null, 0);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, null, 0);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JikeLikeView);
        isLike = typedArray.getBoolean(R.styleable.JikeLikeView_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init()
    {
        Resources resources = getResources();
        likeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like);
        unLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_unlike);
        shiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like_shining);
        bitmapPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int measureWidth = 0;
        int measureHeight = 0;
        int maxHeight = unLikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 20);
        int maxWidth = unLikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 30);
        // 拿到当前控件的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); // heightMeasureSpec和widthMeasureSpec 指的是用户所定义的控件的大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (mode != MeasureSpec.EXACTLY)
        {
            // 测量模式未指定如果有背景  背景多大，我们的控件就有多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight(); // 拿到背景的高度和宽度
            if (suggestedMinimumWidth == 0)
            { // 如果背景宽度为0，即没有背景
                measureWidth = maxWidth;
            } else
            {
                measureWidth = Math.min(suggestedMinimumWidth, maxWidth);
            }
            if (suggestedMinimumHeight == 0)
            { // 如果背景高度为0，即没有背景
                measureHeight = maxHeight;
            } else
            {
                measureHeight = Math.min(suggestedMinimumHeight, maxHeight);
            }
        } else
        {
            // 测量模式指定，根据用户定义的大小来判断
            measureWidth = Math.min(maxWidth, widthSize);
            measureHeight = Math.min(maxHeight, heightSize);
        }
        setMeasuredDimension(measureWidth, measureHeight);
        getPadding(measureWidth, measureHeight);
    }

    private void getPadding(int measureWidth, int measureHeight)
    {
        int bitmapWidth = likeBitmap.getWidth();
        int bitmapHeight = likeBitmap.getHeight();
        left = (measureWidth - bitmapWidth) / 2;
        top = (measureHeight - bitmapHeight) / 2;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        centerX = width/2;
        centerY = height/2;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Bitmap handBitmap = isLike ? likeBitmap : unLikeBitmap;
        //  使用 canvas.scale 以及其他的效果方法时 必须先调用 canvas.save 以及 canvas.restore 这两个方法必须同时出现
        canvas.save();
        canvas.scale(handScale,handScale,centerX,centerY); // 这是实现动画的关键一步
        canvas.drawBitmap(handBitmap, left, top, bitmapPaint);
        canvas.restore();
        if (isLike)
        {
            canvas.drawBitmap(shiningBitmap, left + 10, 0, bitmapPaint);
        }
    }

    // 当这个自定义View从界面消失的时候，这个方法会被回调，可以在这个方法中回收资源
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        likeBitmap.recycle();
        unLikeBitmap.recycle();
        shiningBitmap.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                onClick();
                break;
            }
            default:
            {
                break;
            }
        }
        return true;

    }

    private void onClick()
    {
        isLike = !isLike;
//        ObjectAnimator handScale = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
//        handScale.setDuration(250);
//        handScale.start();
//        invalidate(); // 这个方法走完就会走onDrawn方法

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.1f, 1.0f);
        valueAnimator.setDuration(250);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float animatedValue =(float) animation.getAnimatedValue();
                handScale = animatedValue;
                invalidate();
            }
        });
    }

    // 使用objectAnimator 系统会自动调用该属性的set方法
    public void setHandScale(float value)
    {
        this.handScale = value;
        invalidate();
    }
}
