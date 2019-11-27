package com.Dzh.todayinformation.splash;


import android.os.Handler;

public class CustomCountDownTimer implements Runnable
{
    private  ICountDownHandler countDownHandler;
    private  int time;
    private Handler handler;
    private boolean isRun;
    private int countDownTime;
    // 实时区回调当下的时间 倒计时到几秒 观察者设计模式
    // 支持动态传入总时间
    // 每过一秒总秒数 -1
    // 总时间倒计时为0时，要回调完成的状态

    public CustomCountDownTimer(int time,ICountDownHandler countDownHandler)
    {
        handler = new Handler();
        this.time = time;
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;

    }

    // 实现的具体逻辑
    @Override
    public void run()
    {
        if(isRun)
        {
            if(countDownHandler != null)
            {
                countDownHandler.onTicker(countDownTime);
            }
            if(countDownTime == 0)
            {
                cancel();
                if(countDownHandler != null)
                {
                    countDownHandler.onFinish();
                }
            }
            else
            {
                countDownTime = time --;
                handler.postDelayed(this,1000);
            }
        }
    }
    // 开启倒计时
    public void start()
    {
        isRun = true;
        handler.post(this);  // post的过程就是在执行 Runnable接口的Run方法
    }

    // 跳出循环 终止
    public void cancel()
    {
        isRun = false;
        handler.removeCallbacks(this);
    }

    // 观察者回调接口  IOC数据回调
    public interface ICountDownHandler
    {
        // 倒计时回调
        void onTicker(int time);

        // 完成时回调
        void onFinish();
    }

}
