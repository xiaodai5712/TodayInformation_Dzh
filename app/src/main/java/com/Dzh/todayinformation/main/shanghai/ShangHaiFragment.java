package com.Dzh.todayinformation.main.shanghai;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;

@ViewInject(mainLayoutId = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment
{

    private String TAG = "测试ShangHaiFragment";

    @Override
    public void afterBindView()
    {
        Log.d(TAG, "afterBindView: ");
    }
}
