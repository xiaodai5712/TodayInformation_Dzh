package com.Dzh.todayinformation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity
{
    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;

    private SplashTimerPresenter timerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);  // 该方法将我自定义的xml文件加载到了系统的一个布局里，所以呈现出来的画面是我自定义的布局，加上系统自带的状态栏和导航栏
        initTimerPresenter();
        initListener();
        initVideo();

        // 把初始化timer及相关内容抽离出 抽出到 presenter层
//        initTimer();
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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        timerPresenter.cancel();
    }

    public void setTvTimer(String s)
    {
        mTvTimer.setText(s);
    }
}
