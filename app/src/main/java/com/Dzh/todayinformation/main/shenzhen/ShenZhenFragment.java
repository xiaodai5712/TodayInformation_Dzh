package com.Dzh.todayinformation.main.shenzhen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_shenzhen)
public class ShenZhenFragment extends BaseFragment
{
    private String TAG = "ShenZhenFragment";
    @BindView(R.id.tv_shenzhen_fragment)
    TextView tvShenzhenFragment;

    @Override
    public void afterBindView()
    {
        tvShenzhenFragment.setText("wodfjkdfh");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        if(isVisibleToUser)
        {
            // 加载网络数据
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDestroyOptionsMenu()
    {
        super.onDestroyOptionsMenu();
        Log.d(TAG, "onDestroyOptionsMenu: ");
    }
}
