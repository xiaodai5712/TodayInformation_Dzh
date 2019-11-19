package com.Dzh.todayinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;


public class SplashActivity extends AppCompatActivity
{
    private FullScreenVideoView mVideoView;
    private TextView mTvTimer;
    private CustomCountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);  // 该方法将我自定义的xml文件加载到了系统的一个布局里，所以呈现出来的画面是我自定义的布局，加上系统自带的状态栏和导航栏

        mTvTimer = (TextView) findViewById(R.id.tv_splash_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
        mVideoView = (FullScreenVideoView)findViewById(R.id.vv_play);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.start();
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

        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler()
        {
            @Override
            public void onTicker(int time)
            {
                mTvTimer.setText(time + "秒");
            }

            @Override
            public void onFinish()
            {
                mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        timer.cancel();
    }
}
