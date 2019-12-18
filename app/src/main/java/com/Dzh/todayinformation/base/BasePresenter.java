package com.Dzh.todayinformation.base;

import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.base.BaseMvpPresenter;
import com.dzh.task.LfTask;
import com.dzh.task.TaskHelper;

/*
*集成 mvp 及 网络请求 快捷方式
*/
public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T>
{
    public BasePresenter(T view)
    {
        super(view);
    }
    public void submitTask(LfTask task)
    {
        TaskHelper.submitTask(task,task);
    }
}
