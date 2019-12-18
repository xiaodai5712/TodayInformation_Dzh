package com.dzh.task;

interface ITaskCallback<Result>
{
    void onComplete(Result result);
    void onException(Throwable throwable);

}
