package com.Dzh.todayinformation.main.shanghai;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.shanghai.adapter.ShanghaiAdapter;
import com.Dzh.todayinformation.main.shanghai.dto.ShanghaiDataManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment
{

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingToolBarLayout)
    CollapsingToolbarLayout shanghaiCollapsingToolBarLayout;
    @BindView(R.id.shanghai_app_barLayout)
    AppBarLayout shanghaiAppBarLayout;
    @BindView(R.id.shanghai_recyclerView)
    RecyclerView mRecyclerView;
    private String TAG = "测试ShangHaiFragment";

    @Override
    public void afterBindView()
    {
        Log.d(TAG, "afterBindView: ");

        initRecyclerView();
        initListener();
    }

    private void initRecyclerView()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(),false));
    }

    private void initListener()
    {
        shanghaiAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i)
            {
                Log.d(TAG, "onOffsetChanged: 偏移值为：" + i + " ,高度为：" + appBarLayout.getMeasuredHeight());
                if (-i < appBarLayout.getMeasuredHeight() / 2)
                {
                    tvShanghaiWelcome.setVisibility(View.INVISIBLE);
                } else
                {
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
