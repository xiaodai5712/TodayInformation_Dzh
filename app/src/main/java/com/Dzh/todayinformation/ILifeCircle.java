package com.Dzh.todayinformation;

import android.content.Intent;
import android.os.Bundle;

public interface ILifeCircle
{
    void onCreate(Bundle saveInstanceState, Intent intent,Bundle getArguments);

    void onActivityCreated(Bundle saveInstanceState, Intent intent,Bundle getArguments);

    void onStart();

    void onResume();

    void onStop();

    void destroyView();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode,int resultCode,Intent data);

    void onSaveInstanceState(Bundle bundle);

    void attachView();

}
