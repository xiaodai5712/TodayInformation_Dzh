package com.Dzh.todayinformation.main.shanghai.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.Dzh.todayInformation.player.PlayerService;
import com.Dzh.todayInformation.player.source.RawPlayerSource;
import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BasePresenter;
import com.Dzh.todayinformation.base.JHTask;
import com.Dzh.todayinformation.base.helper.ContextHelper;
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.Dzh.todayinformation.main.shanghai.lf.IPlayerServiceContract;
import com.Dzh.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.Dzh.todayinformation.main.shanghai.moudle.ShangHaiDetailHttpTask;
import com.dzh.http.result.IResult;


public class PlayerServicePresenter extends BasePresenter<IPlayerServiceContract.IView> implements IPlayerServiceContract.IPresenter
{

    private String TAG = "ShanghaiDetailPresenter";
    private PlayerService playerService;

    public PlayerServicePresenter(IPlayerServiceContract.IView view)
    {
        super(view);
    }

    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // ioc 数据回调 和Service连接成功后调用
            PlayerService.PlayServiceBinder binder = (PlayerService.PlayServiceBinder)service;
            playerService = binder.getService();
            playOrPaused();
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            // 和Service连接断开后调用
            if (playerService != null)
            {
                playerService.unbindService(mConnection);
                playerService = null;
            }
        }
    };
    @Override
    public void bindService(Context context)
    {
        if(playerService != null)
        {
            playOrPaused();
        }
        else
        {
            Intent intent = new Intent(context, PlayerService.class);
            context.bindService(intent,mConnection,Service.BIND_AUTO_CREATE);
        }

    }

    @Override
    public void playOrPaused()
    {
        if (playerService != null)
        {
            //开启播放音乐
            playerService.playOrPause
                    (new RawPlayerSource().setPath(ContextHelper.getInstance().getApplicationContext().getPackageName(), R.raw.minyao)
                            ,ContextHelper.getInstance().getApplicationContext());
        }
    }


}
