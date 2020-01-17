package com.ipc.response;

import com.dzh.ipc.IClientInterface;

/**
 * Date: 2020/1/16
 * author: Dzh
 */
public class BaseResponse
{
    public final IClientInterface mIClientInterface;
    public final String mParams;
    public final String mRequestKey;

    public BaseResponse(String requestKey, String params, IClientInterface mIClientInterface)
    {
        this.mRequestKey = requestKey;
        this.mParams = params;
        this.mIClientInterface = mIClientInterface;
    }
}
