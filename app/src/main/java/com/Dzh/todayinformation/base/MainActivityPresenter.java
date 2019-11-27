package com.Dzh.todayinformation.base;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.main.IMainActivityContract;
import com.Dzh.todayinformation.main.shanghai.BeiJingFragment;
import com.Dzh.todayinformation.main.shanghai.HangZhouFragment;
import com.Dzh.todayinformation.main.shanghai.ShangHaiFragment;
import com.Dzh.todayinformation.main.shanghai.ShenZhenFragment;
import com.Dzh.todayinformation.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.IView>implements  IMainActivityContract.IPresenter
{

    private int mCurrentFragmentIndex; // 当前Fragment脚标
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    String TAG = "测试MainActPresenter";

    public MainActivityPresenter(IMainActivityContract.IView view)
    {
        super(view);
    }

    @Override
    protected IMainActivityContract.IView getEmptyView()
    {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment()
    {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }

    // 切换fragment的方法
    private void replaceFragment(int mCurrentFragmentIndex )
    {
        for(int i = 0; i < mFragments.length; i++)
        {
            if(mCurrentFragmentIndex != i)
            {
                if(mFragments[i] != null)
                {
                    hideFragment(mFragments[i]);
                }
            }
        }

        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if(mFragment != null)
        {
            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);
        }
        else
        {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }
    }

    // 记录当前Fragment 脚标
    private void setCurChecked(int mCurrentFragmentIndex)
    {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex)
        {
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                Log.d(TAG, "setCurChecked: ");

                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_beijing;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_shenzhen;
                break;
        }
    }

    // 创建当前Fragment
    private void newCurrentFragment(int mCurrentFragmentIndex)
    {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex)
        {
            case 0:
            {
                fragment = new ShangHaiFragment();
                Log.d(TAG, "newCurrentFragment: ");
                break;
            }
            case 1:
            {
                fragment = new HangZhouFragment();
                break;
            }
            case 2:
            {
                fragment = new BeiJingFragment();
                break;
            }
            case 3:
            {
                fragment = new ShenZhenFragment();
                break;
            }
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    // 显示Fragment
    private void addAndShowFragment(Fragment mFragment)
    {
        if(mFragment.isAdded())
        {
            getView().showFragment(mFragment);
        }
        else
        {
            getView().addFragment(mFragment);
        }
    }

    // 隐藏Fragment
    private void hideFragment(Fragment mFragment)
    {
        if(mFragment != null && mFragment.isVisible())
        {
            getView().hideFragment(mFragment);
        }
    }
}
