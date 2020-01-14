package com.Dzh.todayinformation.base;

import android.app.Application;

import com.Dzh.todayinformation.base.crash.CrashProtectManager;
import com.Dzh.todayinformation.base.helper.ContextHelper;

/**
 * Date: 2020/1/3
 * author: Dzh
 */
public class TodayInformationApplication extends Application
{
    @Override
    public void onCreate()
    {
        // 只要进程一启动,这个方法就会被系统调用，且这个类的声明周期是和进程一直的，进程什么时候结束，这个类的对象什么时候这GC掉
        super.onCreate();

        // 先把下面这句话暂时注掉，否则程序不崩溃，也不能实现功能，还找不到问题在哪就很蛋疼
        //CrashProtectManager.getInstance(this).init(); // Application也是Context的一个子类

        // 获取一个全局的context
        ContextHelper.getInstance().init(this);
    }
}
