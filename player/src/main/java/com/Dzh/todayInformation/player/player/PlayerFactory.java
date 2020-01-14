package com.Dzh.todayInformation.player.player;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.Dzh.todayInformation.player.tools.DataSourceUtil;

/**
 * Date: 2020/1/10
 * author: Dzh
 */
public class PlayerFactory
{
    private static String  TAG = "playerFactory";
    // 一般工厂设计模式 ： 可以创建用户想要的播放器
    public IPlayer createPlayer(Context context)
    {
        int playerType = DataSourceUtil.getMetaDataFromApp(context);
        switch (playerType)
        {
            case IPlayerType.MEDIAPLAYERTYPE_1:
            {
                return new GoogleMediaPlayer();
            }
            case IPlayerType.MEDIAPLAYERTYPE_2:
            {
                return new ExoMediaPlayer(context);
            }
            default: break;
        }
        Log.e(TAG, "获取播放器失败");
        return null;
    }
}
