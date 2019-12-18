package com.dzh.task;

import com.dzh.task.tools.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class AsyncTaskInstance<Result> extends FutureTask<Result>
{

    private final ITaskBackground iTaskBackground;
    private final ITaskCallback iTaskCallback;

    public AsyncTaskInstance(final ITaskBackground<Result> iTaskBackground, ITaskCallback<Result> iTaskCallback)
    {
        super(new Callable<Result>()
        {
            @Override
            public Result call() throws Exception
            {
                return iTaskBackground.onBackground();
            }
        });
        this.iTaskBackground = iTaskBackground;
        this.iTaskCallback = iTaskCallback;
    }

    @Override
    protected void done()
    {

        if(iTaskCallback != null)
        {
            onComplete();
        }
    }

    // 获取FutureTask执行不成功的异常
    @Override
    protected void setException(final Throwable t)
    {
        super.setException(t);
        if(iTaskCallback != null)
        {
            ThreadUtil.postMainThread(new Runnable()
            {
                @Override
                public void run()
                {
                    iTaskCallback.onException(t);
                }
            });
        }
    }

    private void onComplete()
    {
        try
        {
            final Object object = get();
            if(object != null )
            {
                ThreadUtil.postMainThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        iTaskCallback.onComplete(object);
                    }
                });
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static AsyncTaskInstance getInstance(ITaskBackground iTaskBackground, ITaskCallback iTaskCallback)
    {
        return new AsyncTaskInstance(iTaskBackground,iTaskCallback);
    }
}
