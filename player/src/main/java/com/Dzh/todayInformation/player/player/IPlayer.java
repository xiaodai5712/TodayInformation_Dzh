package com.Dzh.todayInformation.player.player;

import android.content.Context;

import com.Dzh.todayInformation.player.source.IPlayerSource;

/**
 * Date: 2020/1/10
 * author: Dzh
 */
public interface IPlayer
{

    void release();

    void prepare(Context context, IPlayerSource playerSource);

    void setPlayingListener(IPlayerListener listener);

    void paused();

    void reStart();
}
