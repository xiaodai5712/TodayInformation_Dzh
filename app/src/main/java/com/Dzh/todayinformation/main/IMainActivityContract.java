package com.Dzh.todayinformation.main;

import androidx.fragment.app.Fragment;

import com.dzh.mvp.mvp.ILifeCircle;
import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.MvpController;


public interface IMainActivityContract
{
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

    IView emptyView = new IView()
    {


        @Override
        public MvpController getMvpController()
        {
            return null;
        }

        @Override
        public void showFragment(Fragment mFragment)
        {

        }

        @Override
        public void addFragment(Fragment mFragment)
        {

        }

        @Override
        public void hideFragment(Fragment mFragment)
        {

        }
    };
}
