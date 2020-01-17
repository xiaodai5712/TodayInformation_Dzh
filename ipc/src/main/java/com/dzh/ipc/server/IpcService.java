package com.dzh.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.dzh.ipc.IClientInterface;
import com.dzh.ipc.ISeveInterface;
import com.dzh.ipc.response.ResponseUtil;

/**
 * Date: 2020/1/15
 * author: Dzh
 */
public class IpcService extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return new ISeveInterface.Stub()
        {
            private IClientInterface mClientInterface;

            @Override
            public void executeAsync(String requestKey, String requestParams) throws RemoteException
            {
                ResponseUtil.getAsyncResponse(requestKey,requestParams,mClientInterface);
//                switch (requestKey)
//                {
//                    case "shanghai_detail":
//                    {
//                        if(mClientInterface != null)
//                        {
//                            mClientInterface.callBack(requestKey,"来自远方的祝福");
//                        }
//                        break;
//                    }
//
//                    default:break;
//                }
            }

            @Override
            public String executeSync(String requestKey, String requestParams) throws RemoteException
            {
                String result = "";
                switch (requestKey)
                {
                    case "shanghai_detail":
                    {
                        result = "来自远方的祝福";
                        break;
                    }

                    default:break;
                }
                return result;
            }

            @Override
            public void registerCallBack(IClientInterface iClientInterface) throws RemoteException
            {
                this.mClientInterface = iClientInterface;
            }

        };
    }
}
