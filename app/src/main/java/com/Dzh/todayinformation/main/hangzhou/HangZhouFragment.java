package com.Dzh.todayinformation.main.hangzhou;

import android.widget.TextView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_hangzhou)
public class HangZhouFragment extends BaseFragment
{

    @BindView(R.id.tv_hangzhou)
    TextView tvHangzhou;

    @Override
    public void afterBindView()
    {
        tvHangzhou.setText("我是杭州");
    }
}
