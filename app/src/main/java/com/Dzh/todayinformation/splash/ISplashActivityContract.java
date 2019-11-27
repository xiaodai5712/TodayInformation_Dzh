package com.Dzh.todayinformation.splash;

import com.Dzh.todayinformation.mvp.ILifeCircle;
import com.Dzh.todayinformation.mvp.IMvpView;
import com.Dzh.todayinformation.mvp.MvpController;

public interface ISplashActivityContract
{
    interface IView extends IMvpView
    {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle
    {
        void initTimer();
    }
    IView emptyView = new IView()
    {
        @Override
        public void setTvTimer(String timer)
        {

        }

        @Override
        public MvpController getMvpController()
        {
            return null;
        }
    };
}
