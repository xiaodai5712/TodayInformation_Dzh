package com.Dzh.todayInformation.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.Dzh.todayInformation.player.source.IPlayerSource;
import com.Dzh.todayInformation.player.state.PlayerState;

/**
 * Date: 2020/1/10
 * author: Dzh
 */
public class GoogleMediaPlayer implements IPlayer, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnInfoListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnBufferingUpdateListener
{

    private  MediaPlayer mMediaPlayer;
    private String TAG = "GoogleMediaPlayer";
    private IPlayerListener mPlayerListener;

    public GoogleMediaPlayer()
    {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
    }



    @Override
    public void prepare(Context context, IPlayerSource playerSource)
    {
        // 操作mediaPalyer
        try
        {
            mMediaPlayer.setDataSource(context, Uri.parse(playerSource.getUrl()));
            mMediaPlayer.prepare(); // 这个方法执行完之后，系统自动调用播放器的onPrepared方法
            mMediaPlayer.setOnErrorListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnInfoListener(this);
            mMediaPlayer.setOnVideoSizeChangedListener(this);
            mMediaPlayer.setOnBufferingUpdateListener(this);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setPlayingListener(IPlayerListener listener)
    {
        mPlayerListener = listener;
    }

    @Override
    public void paused()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.pause();
            setPlayerState(PlayerState.PAUSED);

        }
    }

    @Override
    public void reStart()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.start();
            setPlayerState(PlayerState.STARTED);
        }
    }



    private void setPlayerState(PlayerState state)
    {
        if(mPlayerListener != null)
        {
            mPlayerListener.OnPlayerStateChanged(state);
        }
    }


    // 播放完成后的监听
    @Override
    public void release()
    {

    }
    // 播放器准备好之后的回调
    @Override
    public void onPrepared(MediaPlayer mp)
    {
        // 初始化成功后调用，且播放器准备好后回调
        mp.start();
        setPlayerState(PlayerState.STARTED);
    }

    // 播放器如果遇到错误，会回调这个方法
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra)
    {
        Log.e(TAG, "onError: what = " + what + " ,extra = " + extra );
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        mp.stop();
        mp.release();
        setPlayerState(PlayerState.IDLE);
    }

    //  播放卡顿的一些信息
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra)
    {
        return false;
    }

    // 横竖屏切换， 视频大小改变回调
    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height)
    {

    }

    // 播放器的缓冲监听，用于一些展示，缓冲进度条
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent)
    {

    }


}
