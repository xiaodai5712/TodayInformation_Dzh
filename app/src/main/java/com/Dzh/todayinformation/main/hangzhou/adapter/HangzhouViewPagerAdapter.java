package com.Dzh.todayinformation.main.hangzhou.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.Dzh.todayinformation.main.hangzhou.view.JiKeFragment;
import com.Dzh.todayinformation.main.hangzhou.view.RefreshFragment;
import com.Dzh.todayinformation.main.hangzhou.view.ZhiHuFragment;

import java.util.ArrayList;

/**
 * Date: 2019/12/24
 * author: Dzh
 */
public class HangzhouViewPagerAdapter extends FragmentStatePagerAdapter
{

    ArrayList<String> titleList = new ArrayList<>();
    public HangzhouViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
        titleList.add("知乎");
        titleList.add("即刻");
        titleList.add("下拉刷新");
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new ZhiHuFragment();
            case 1:
                return new JiKeFragment();
            case 2:
                return new RefreshFragment();
            default: return null;
        }
    }

    @Override
    public int getCount()
    {
        return titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return titleList.get(position);
    }
}
