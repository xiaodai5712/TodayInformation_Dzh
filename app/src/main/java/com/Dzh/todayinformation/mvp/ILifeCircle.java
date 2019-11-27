package com.Dzh.todayinformation.mvp;

import android.content.Intent;
import android.os.Bundle;

public interface ILifeCircle  // 一个最基本的接口，包含了声明周期里所有的方法
{
    void onCreate(Bundle saveInstanceState, Intent intent,Bundle getArguments);

    void onActivityCreated(Bundle saveInstanceState, Intent intent,Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();
    void onStop();

    void destroyView();
    void onDestroy();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode,int resultCode,Intent data);

    void onSaveInstanceState(Bundle bundle);

    void attachView(IMvpView iMvpView);

}
