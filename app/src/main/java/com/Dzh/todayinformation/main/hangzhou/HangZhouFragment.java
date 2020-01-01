package com.Dzh.todayinformation.main.hangzhou;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.hangzhou.adapter.HangzhouViewPagerAdapter;
import com.Dzh.todayinformation.main.hangzhou.view.ZhiHuFragment;
import com.Dzh.todayinformation.main.shenzhen.ShenZhenFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_hangzhou)
public class HangZhouFragment extends BaseFragment
{
    private String TAG = "HangZhouFragment";

    @BindView(R.id.tl_tablayout)
    TabLayout tlTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    public void afterBindView()
    {
        tlTablayout.setupWithViewPager(vpViewpager);
        HangzhouViewPagerAdapter hangzhouViewPagerAdapter = new HangzhouViewPagerAdapter(getChildFragmentManager());
        vpViewpager.setAdapter(hangzhouViewPagerAdapter);


    }
}
