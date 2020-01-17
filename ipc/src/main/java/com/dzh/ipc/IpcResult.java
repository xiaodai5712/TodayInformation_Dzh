package com.dzh.ipc;

import com.dzh.ipc.result.IResult;

/**
 * Date: 2020/1/15
 * author: Dzh
 */
public class IpcResult implements IResult
{
    public String data;
    public int code;
    public boolean success;

    public static IResult getErrorResult()
    {
        IpcResult result = new IpcResult();
        result.success = false;
        return result;
    }

    public static IResult getSuccessResult(String data)
    {
        IpcResult result = new IpcResult();
        result.success = true;
        result.data = data;
        return result;
    }
    @Override
    public boolean isSuccess()
    {
        return success;
    }

    @Override
    public int getCode()
    {
        return code;
    }

    @Override
    public String data()
    {
        return data;
    }
}
