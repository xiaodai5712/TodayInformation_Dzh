package com.Dzh.todayinformation.main.beijing;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_beijing)
public class BeiJingFragment extends BaseFragment
{


    @BindView(R.id.tv_beijing_fragment)
    TextView tvBeijingFragment;
    @BindView(R.id.btn_beijing_fragment)
    Button btnBeijingFragment;
    @BindView(R.id.btn_permission_beijing_fragment)
    Button btnPermissionBeijingFragment;
    private ProcessDataReceiver processDataReceiver;

    private String TAG = "BeiJingFragment";

    @Override
    public void afterBindView()
    {
        mContext.startService(new Intent(mContext, MainProcessService.class));
        btnBeijingFragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //去上海
                ProcessDataTest.getIntance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(), btnBeijingFragment);
            }
        });
//        processDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(processDataReceiver,new IntentFilter(""));

        btnPermissionBeijingFragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 权限检测
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    Log.e(TAG, "onClick: 当前系统的版本号为：" + Build.VERSION.SDK_INT );
                    int permission = getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if(permission == PackageManager.PERMISSION_GRANTED)
                    {
                        Log.d(TAG, "onClick: 权限已经有了");
                    }
                    else
                    {
                        Log.d(TAG, "onClick: 没有权限");
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
                        requestPermissions(permissions,1);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
//        getActivity().unregisterReceiver(processDataReceiver);
        mContext.stopService(new Intent(mContext, MainProcessService.class));
    }

    private class ProcessDataReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: 权限的请求结果" + grantResults[0] + grantResults[1]);
    }
}
