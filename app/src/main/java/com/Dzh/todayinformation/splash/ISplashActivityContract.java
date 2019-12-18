package com.Dzh.todayinformation.splash;


import com.dzh.mvp.mvp.ILifeCircle;
import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.MvpController;

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
