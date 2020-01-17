package com.ipc.response;

import android.os.RemoteException;

import com.dzh.ipc.IClientInterface;

/**
 * Date: 2020/1/16
 * author: Dzh
 */
public class ShanghaiDetailResponse extends BaseResponse
{
    public ShanghaiDetailResponse(String requestKey, String params, IClientInterface mIClientInterface)
    {
        super(requestKey, params, mIClientInterface);
    }

    public void shanghaiDetail()
    {
        try
        {
            mIClientInterface.callBack(mRequestKey,"来自远方的祝福");
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
