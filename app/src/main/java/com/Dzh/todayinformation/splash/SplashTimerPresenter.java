package com.Dzh.todayinformation.splash;

import android.util.Log;

import com.Dzh.todayinformation.base.BasePresenter;
import com.dzh.mvp.mvp.base.BaseMvpPresenter;


public class SplashTimerPresenter extends BasePresenter<ISplashActivityContract.IView> implements ISplashActivityContract.IPresenter
{


    private CustomCountDownTimer timer;
    private final static String TAG = "SplashTimerPresenter";

    public SplashTimerPresenter(ISplashActivityContract.IView view)
    {
        super(view);
    }


    public void initTimer()
    {
        timer = new CustomCountDownTimer(2, new CustomCountDownTimer.ICountDownHandler()
        {
            @Override
            public void onTicker(int time)
            {
                getView().setTvTimer(time + "秒");

            }

            @Override
            public void onFinish()
            {
                getView().setTvTimer("跳过");
            }
        });
        timer.start();
    }

    public void cancel()
    {
        timer.cancel();
    }



    @Override
    public void onPause()
    {

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        cancel();
        Log.e(TAG, "onDestroy: ggg  ");
    }

//    // 防止空指针
//    @Override
//    protected ISplashActivityContract.IView getEmptyView()
//    {
//        return ISplashActivityContract.emptyView;
//    }
}
