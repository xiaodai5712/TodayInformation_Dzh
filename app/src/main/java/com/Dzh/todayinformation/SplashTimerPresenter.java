package com.Dzh.todayinformation;

class SplashTimerPresenter
{

    private final SplashActivity mActivity;
    private CustomCountDownTimer timer;

    public SplashTimerPresenter(SplashActivity activity)
    {
        this.mActivity = activity;
    }

    public void initTimer()
    {
        timer = new CustomCountDownTimer(2, new CustomCountDownTimer.ICountDownHandler()
        {
            @Override
            public void onTicker(int time)
            {
                mActivity.setTvTimer(time + "秒");
            }

            @Override
            public void onFinish()
            {
                mActivity.setTvTimer("跳过");
            }
        });
        timer.start();
    }

    public void cancel()
    {
        timer.cancel();
    }
}
