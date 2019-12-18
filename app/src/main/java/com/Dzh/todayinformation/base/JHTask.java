package com.Dzh.todayinformation.base;

import com.dzh.http.result.IResult;
import com.dzh.http.result.IResultCallBack;
import com.dzh.http.result.Result;
import com.dzh.task.LfTask;

public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T>
{


    @Override
    public void onComplete(IResult<T> iResult)
    {
        if(iResult != null)
        {
            if(iResult.isSuccess())
            {
                onSuccess(iResult);
            }
            else
            {
                onFailed(Result.failed(Result.CODE_505)); // 1次失败
            }
        }
        else
        {
            onFailed(Result.failed(Result.CODE_504)); // 2次失败
        }
    }


    @Override
    public void onFailed(IResult t)
    {
        switch (t.getCode())
        {
            case Result.CODE_404:
                break;
            case Result.CODE_505:
                break;
            case Result.CODE_504:
                break;
        }
    }

    @Override
    public void onException(Throwable throwable)
    {
        onFailed(Result.failed(Result.CODE_404)); // 3次失败
    }
}
