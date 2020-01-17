package com.Dzh.todayinformation.main.beijing;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * Date: 2020/1/14
 * author: Dzh
 */
public class MainProcessService extends Service
{

    public static final int SHANGHAI_DETAIL = 0;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case SHANGHAI_DETAIL:
                {
                    Messenger replyTo = msg.replyTo;
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("data",ProcessDataTest.getIntance().getProcessDec());
                    message.setData(bundle);
                    try
                    {
                        replyTo.send(message);
                    } catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    };

    // Messenger支持Service快进程通信
    private Messenger messenger = new Messenger(handler);
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return messenger.getBinder();
    }
}
