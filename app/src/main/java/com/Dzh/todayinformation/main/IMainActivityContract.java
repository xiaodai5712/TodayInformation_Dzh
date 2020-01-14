package com.Dzh.todayinformation.main;

import androidx.fragment.app.Fragment;

import com.dzh.annotation.MvpEmptyViewFactory;
import com.dzh.mvp.mvp.ILifeCircle;
import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.MvpController;


public interface IMainActivityContract
{
    @MvpEmptyViewFactory
    interface IView extends IMvpView
    {

        void showFragment(Fragment mFragment);
        void addFragment(Fragment mFragment);
        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle
    {

        void initHomeFragment();

        int getCurrentCheckedIndex();

        int getCurrentCheckedId();

        void replaceFragment(int mCurrentFragmentIndex);
        int getTopPosition();
        int getBottomPosition();


    }

}
