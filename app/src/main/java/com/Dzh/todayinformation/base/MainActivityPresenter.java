package com.Dzh.todayinformation.base;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.main.IMainActivityContract;
import com.Dzh.todayinformation.main.beijing.BeiJingFragment;
import com.Dzh.todayinformation.main.hangzhou.HangZhouFragment;
import com.Dzh.todayinformation.main.shanghai.ShangHaiFragment;
import com.Dzh.todayinformation.main.shenzhen.ShenZhenFragment;
import com.Dzh.todayinformation.main.tools.MainConstantTool;
import com.Dzh.todayinformation.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.IView>implements  IMainActivityContract.IPresenter
{

    private int mCurrentFragmentIndex; // 当前Fragment编号
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId; // 这个是 XML文件中 RadioButton的值 上海的button值为 mCurrentCheckedId = 2131230847
    String TAG = "测试MainActPresenter";
    private int mTopPosition; // 标明上海杭州哪个被选定
    private int mBottomPosition; //标明北京深圳哪个被选定

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
        mCurrentFragmentIndex = MainConstantTool.SHANGHAI;  // 初始时，将当前的fragment 编号 设为0，即上海的Fragment
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedIndex()
    {
        return mCurrentFragmentIndex;
    }

    @Override
    public int getCurrentCheckedId()
    {
        return mCurrentCheckedId;
    }

    // 切换fragment的方法
    public void replaceFragment(int mCurrentFragmentIndex )
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

    @Override
    public int getTopPosition()
    {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition()
    {
        return mBottomPosition;
    }

    // 记录当前Fragment 序号
    private void setCurChecked(int mCurrentFragmentIndex)
    {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex)
        {
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                Log.d(TAG, "mCurrentCheckedId = " + mCurrentCheckedId);
                mTopPosition = MainConstantTool.SHANGHAI;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mTopPosition = MainConstantTool.HANGZHOU;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_beijing;
                mBottomPosition = MainConstantTool.BEIJING;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_shenzhen;
                mBottomPosition = MainConstantTool.SHENZHEN;
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
                Log.d(TAG, "newCurrentFragment:  上海创建");
                break;
            }
            case 1:
            {
                fragment = new HangZhouFragment();
                Log.d(TAG, "newCurrentFragment:  杭州创建");
                break;
            }
            case 2:
            {
                fragment = new BeiJingFragment();
                Log.d(TAG, "newCurrentFragment:  北京创建");
                break;
            }
            case 3:
            {
                fragment = new ShenZhenFragment();
                Log.d(TAG, "newCurrentFragment:  深圳创建");
                break;
            }
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        Log.d(TAG, "newCurrentFragment: ");
        if(fragment == null)
        {
            Log.d(TAG, "newCurrentFragment:   fragment == null");
        }
        else 
        {
            Log.d(TAG, "newCurrentFragment:  fragment ！= null");
        }
        addAndShowFragment(fragment);
    }

    // 显示Fragment
    private void addAndShowFragment(Fragment mFragment)
    {
        Log.d(TAG, "addAndShowFragment: " + mFragment.isAdded());
        if(mFragment.isAdded())
        {
            Log.d(TAG, "addAndShowFragment:  加上了");
            getView().showFragment(mFragment);
        }
        else
        {
            Log.d(TAG, "addAndShowFragment:  没加上返回空的");
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
