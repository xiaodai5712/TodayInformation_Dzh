package com.dzh.mvp.mvp.presenter;



import com.dzh.mvp.mvp.ILifeCircle;
import com.dzh.mvp.mvp.IMvpView;
import com.dzh.mvp.mvp.MvpController;

import java.lang.ref.WeakReference;

public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle
{
    // 中介者设计模式中的抽象中介者 保存和获取V层的引用

    protected WeakReference<T> weakReference;
    protected LifeCircleMvpPresenter()
    {
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView)
    {
        super();
        attachView(iMvpView);
        MvpController mvpController = iMvpView.getMvpController();
        mvpController.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView)
    {
        if(weakReference == null)
        {
            weakReference = new WeakReference(iMvpView);
        }
        else 
        {
            T view = (T) weakReference.get();
            if(view != iMvpView)
            {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy()
    {
        weakReference = null;
    }
    
    protected T getView()
    {
        T view = weakReference != null ? weakReference.get():null;
        if(view == null)
        {
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
