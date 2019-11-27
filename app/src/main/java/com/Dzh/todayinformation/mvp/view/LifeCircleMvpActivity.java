package com.Dzh.todayinformation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Dzh.todayinformation.mvp.IMvpView;
import com.Dzh.todayinformation.mvp.MvpController;

// Mvp View层框架底层的一个基类
public class LifeCircleMvpActivity extends AppCompatActivity implements IMvpView
{

    private MvpController mvpController;

    @Override
    public MvpController getMvpController()
    {
        if(this.mvpController == null)
        {
            this.mvpController = new MvpController();
        }
        return this.mvpController;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        if(intent == null)
        {
            intent = new Intent();
        }

        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onCreate(savedInstanceState,intent,null);
            mvpController.onActivityCreated(savedInstanceState,intent,null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onNewIntent(intent);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onStart();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onResume();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onPause();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onStop();
        }
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController mvpController = this.getMvpController();
        if(mvpController != null)
        {
            mvpController.onActivityResult(requestCode,resultCode,data);
        }
    }


}
