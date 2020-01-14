package com.Dzh.todayInformation.player.player;

import com.Dzh.todayInformation.player.state.PlayerState;

/**
 * Date: 2020/1/10
 * author: Dzh
 */
public interface IPlayerListener
{
    void OnPlayerStateChanged(PlayerState state);
}
