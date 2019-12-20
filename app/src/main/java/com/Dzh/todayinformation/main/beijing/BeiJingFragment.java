package com.Dzh.todayinformation.main.beijing;

import android.widget.TextView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_beijing)
public class BeiJingFragment extends BaseFragment
{


    @BindView(R.id.tv_beijing_fragment)
    TextView tvBeijingFragment;

    @Override
    public void afterBindView()
    {

    }
}
