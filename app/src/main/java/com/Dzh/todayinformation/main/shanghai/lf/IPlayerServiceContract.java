package com.Dzh.todayinformation.main.shanghai.lf;

import android.content.Context;

import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.dzh.annotation.MvpEmptyViewFactory;
import com.dzh.mvp.mvp.ILifeCircle;
import com.dzh.mvp.mvp.IMvpView;

public interface IPlayerServiceContract
{
    @MvpEmptyViewFactory
    interface IView extends IMvpView
    {

    }

    interface  IPresenter extends ILifeCircle
    {
        void bindService(Context context);

        void playOrPaused();
    }


}
