package com.Dzh.todayinformation.main.shanghai.lf;

import androidx.fragment.app.Fragment;

import com.Dzh.todayinformation.main.IMainActivityContract;
import com.dzh.mvp.mvp.ILifeCircle;
import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.MvpController;

public interface IShanghaiDetailContract
{
    interface IView extends IMvpView
    {

    }

    interface IPresenter extends ILifeCircle
    {

        void getNetData();
    }

    IShanghaiDetailContract.IView emptyView = new IShanghaiDetailContract.IView()
    {
        @Override
        public MvpController getMvpController()
        {
            return null;
        }

    };
}
