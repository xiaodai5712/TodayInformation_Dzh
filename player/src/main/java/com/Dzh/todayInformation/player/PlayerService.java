package com.Dzh.todayInformation.player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Switch;

import androidx.annotation.Nullable;

import com.Dzh.todayInformation.player.player.IPlayer;
import com.Dzh.todayInformation.player.player.IPlayerListener;
import com.Dzh.todayInformation.player.player.PlayerFactory;
import com.Dzh.todayInformation.player.source.IPlayerSource;
import com.Dzh.todayInformation.player.source.RawPlayerSource;
import com.Dzh.todayInformation.player.state.PlayerState;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * Date: 2020/1/9
 * author: Dzh
 */
public class PlayerService extends Service implements IPlayerListener
{


    private PlayerState mState = PlayerState.IDLE;
    private IPlayer mPlayer;
    private PlayerFactory mPlayerFactory;



    public class PlayServiceBinder extends Binder
    {
        // IBinder这个接口是用来实现跨进程通信的

        public PlayerService getService()
        {
            return PlayerService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return new PlayServiceBinder();
    }


    // 这个方法在 使用startService开启服务的时候作用比较大，startService一次，这个方法就会被调用一次，但是  无论bindService 多少次，这个方法都只会执行一次，=
    // 这个方法大多用来向Service传递数据
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }


    // 无论start还是bind 都只会调用一次，一般用来做全局的初始化操作
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public void playOrPause(IPlayerSource playerSource, Context mContext)
    {
        switch (mState)
        {
            case IDLE:
            {
                if(mPlayer != null)
                {
                    mPlayer.release();
                }
                if(mPlayerFactory == null)
                {
                    mPlayerFactory = new PlayerFactory();
                }
                if (mPlayer == null)
                {
                    mPlayer = mPlayerFactory.createPlayer(mContext);
                }
                if (mPlayer == null)
                {
                    return;
                }
                mPlayer.setPlayingListener(this);
                mPlayer.prepare(mContext,playerSource);
                // 拿到播放器去开始播放
                // 初始化播放器
                break;
            }
            case STARTED:
            {
                // 暂停
                if (mPlayer != null)
                {
                    mPlayer.paused();
                }
                break;
            }
            case PAUSED:
            {
                if (mPlayer != null)
                {
                    mPlayer.reStart();
                }
                // 去继续播放
            }
            case PREPARING:
            {
                break;
            }
            default: break;
        }
    }

    @Override
    public void OnPlayerStateChanged(PlayerState state)
    {
        this.mState = state;
    }
}
