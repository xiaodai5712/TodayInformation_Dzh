package com.dzh.mvp.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MvpController implements ILifeCircle
{

    // 存放的是P层的实例 P层可以有多个实例
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle lifeCircle)
    {
        this.lifeCircles.add(lifeCircle);
    }
    @Override
    public void onCreate(Bundle saveInstanceState, Intent intent, Bundle getArguments)
    {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext())
        {
            ILifeCircle next = iterator.next();
            if(intent == null)
            {
                intent = new Intent();
            }
            if(getArguments == null)
            {
                getArguments = new Bundle();
            }
            next.onCreate(saveInstanceState,intent,getArguments);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent extras, Bundle getArguments) {
        Iterator var3 = this.lifeCircles.iterator();
        while (var3.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var3.next();
            if (extras == null) {
                extras = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            presenter.onActivityCreated(savedInstanceState, extras,getArguments);
        }
    }

    @Override
    public void onDestroy() {
        Iterator var1 = this.lifeCircles.iterator();

        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onDestroy();
        }
        this.lifeCircles.clear();
    }

    @Override
    public void onViewDestroy() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onViewDestroy();
        }
    }
    @Override
    public void onResume() {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter1 = (ILifeCircle) var2.next();
            presenter1.onResume();
        }

    }
    @Override
    public void onPause() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onPause();
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var2.next();
            presenter.onSaveInstanceState(outState);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Iterator var4 = this.lifeCircles.iterator();
        while (var4.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var4.next();
            presenter.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void attachView(IMvpView iMvpView) {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.attachView(iMvpView);
        }
    }

    @Override
    public void destroyView() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.destroyView();
        }
    }
    @Override
    public void onStart() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onStart();
        }

    }
    @Override
    public void onStop() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onStop();
        }
    }
    @Override
    public void onNewIntent(Intent intent) {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var2.next();
            presenter.onNewIntent(intent);
        }

    }
}
