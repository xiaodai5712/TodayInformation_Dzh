package com.Dzh.todayinformation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.Dzh.todayinformation.base.BaseActivity;
import com.Dzh.todayinformation.main.MainActivity;
import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.ViewInject;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.IView
{
    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;

    private ISplashActivityContract.IPresenter timerPresenter;
    String TAG = "测试SplashAct";

    @Override
    public void afterBindView()
    {
        initTimerPresenter();
        initListener();
        initVideo();
    }

    private void initTimerPresenter()
    {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }



    private void initVideo()
    {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.start();
            }
        });
    }

    private void initListener()
    {
        mTvTimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });


        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                mp.start();
            }
        });
    }

    public void setTvTimer(String s)  // 这个方法来自 ISplashActivityContract.IView 参数 s 是怎么传过来的
    {
        Log.d(TAG, "setTvTimer:  s = " + s);
        mTvTimer.setText(s);
        if(!s.equals("跳过"))
        {
            mTvTimer.setEnabled(false);
        }
        else
        {
            mTvTimer.setEnabled(true);
        }
    }
}
