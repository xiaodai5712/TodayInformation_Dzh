package com.Dzh.todayinformation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if(annotation != null)
        {
            int mainLayoutId = annotation.mainLayoutId();
            if(mainLayoutId > 0)
            {
                setContentView(mainLayoutId);
                ButterKnife.bind(this);
            }
            else
            {
                throw new RuntimeException("mainLayoutId < 0");
            }
        }
        else
        {
            throw new RuntimeException("annotation = null");
        }
    }
}
