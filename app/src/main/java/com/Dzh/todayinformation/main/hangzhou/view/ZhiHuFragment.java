package com.Dzh.todayinformation.main.hangzhou.view;

import android.view.animation.AnimationUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.hangzhou.adapter.ZhihuAdapter;
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.Dzh.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.Dzh.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;

/**
 * Date: 2019/12/18
 * author: Dzh
 */

@ViewInject(mainLayoutId = R.layout.fragment_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShanghaiDetailContract.IView
{

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shanghai_app_barLayout)
    AppBarLayout shanghaiAppBarLayout;
    @BindView(R.id.zhihu_recyclerView)
    RecyclerView zhihuRecyclerView;

    @Override
    public void afterBindView()
    {
        zhihuRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        zhihuRecyclerView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.zhihu_recyclerview_show));
        mPresenter.getNetData(20);


    }

    @Override
    public void showData(ShangHaiDetailBean data)
    {
        if(zhihuRecyclerView.getAdapter() == null)
        {
            ZhihuAdapter adapter = new ZhihuAdapter(data.result.data);
            zhihuRecyclerView.setAdapter(adapter);
        }
    }
}
