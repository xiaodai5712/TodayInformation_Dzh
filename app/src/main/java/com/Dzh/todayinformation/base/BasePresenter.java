package com.Dzh.todayinformation.base;

import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.base.BaseMvpPresenter;
import com.dzh.task.LfTask;
import com.dzh.task.TaskHelper;

import today.information.mvp.MvpEmptyViewFactory;

/*
*集成 mvp 及 网络请求 快捷方式
*/
public  class  BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T>
{
    public BasePresenter(T view)
    {
        super(view);
    }
    public void submitTask(LfTask task)
    {
        TaskHelper.submitTask(task,task);
    }

    @Override
    protected T getEmptyView()
    {
        T t = null;
        Class superClassGenricType = GenericsUtils.getSuperClassGenricType(this.getClass(), 0);
        try
        {
            t = (T)MvpEmptyViewFactory.create(superClassGenricType);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return t;
    }
}
