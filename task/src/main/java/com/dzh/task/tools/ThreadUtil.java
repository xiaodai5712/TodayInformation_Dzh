package com.dzh.task.tools;


import android.os.Handler;
import android.os.Looper;

public class ThreadUtil
{
    private static Handler MAIN = new Handler(Looper.getMainLooper());

    public static void postMainThread(final Runnable runnable)
    {
        MAIN.post(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    runnable.run();
                }catch ( Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    // 获取当前设备的cpu数量
    public static int CPU_NUM = Runtime.getRuntime().availableProcessors();

}
