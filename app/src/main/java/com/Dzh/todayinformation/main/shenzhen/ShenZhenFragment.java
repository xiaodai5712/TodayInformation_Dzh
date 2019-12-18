package com.Dzh.todayinformation.main.shenzhen;

import android.widget.TextView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_shenzhen)
public class ShenZhenFragment extends BaseFragment
{
    @BindView(R.id.tv_shenzhen_fragment)
    TextView tvShenzhenFragment;

    @Override
    public void afterBindView()
    {
        tvShenzhenFragment.setText("我是深圳");
    }
}
