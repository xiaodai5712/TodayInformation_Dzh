package com.Dzh.todayinformation.main.hangzhou.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.hangzhou.adapter.ZhihuAdapter;
import com.Dzh.todayinformation.main.hangzhou.refresh.MeiTuanRefreshManager;
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.Dzh.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.Dzh.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;
import com.dzh.refresh.GodRefreshLayout;

import butterknife.BindView;

/**
 * Date: 2019/12/26
 * author: Dzh
 */
@ViewInject(mainLayoutId = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShanghaiDetailContract.IView
{

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);
    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;
    @BindView(R.id.refresh_recyclerview)
    RecyclerView mRecyclerView;

    @Override
    public void afterBindView()
    {
        initRecyclerView();
        initRefreshLayout();

    }

    private void initRefreshLayout()
    {
        //  1 采用默认的方式
//        godRefresh.setBaseRefreshManager();

        // 2 支持自定义
        godRefresh.setBaseRefreshManager(new MeiTuanRefreshManager(mContext));
        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener()
        {
            @Override
            public void onRefreshing()
            {
//                mPresenter.getNetData(20);
                godRefresh.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        godRefresh.refreshOver();
                    }
                },2000);
            }
        });
    }

    private void initRecyclerView()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data)
    {
        if(mRecyclerView.getAdapter()== null)
        {
            ZhihuAdapter adapter = new ZhihuAdapter(data.result.data);
            mRecyclerView.setAdapter(adapter);
        }
        godRefresh.refreshOver();
    }
}
