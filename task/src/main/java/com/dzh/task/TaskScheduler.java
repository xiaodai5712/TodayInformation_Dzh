package com.dzh.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.dzh.task.tools.ThreadUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TaskScheduler
{

    private final PriorityThreadPoolExecutor executor;

    interface ITaskSchedulerType
    {
        int SUBMIT_TASK = 0;
    }
    private static TaskScheduler instance;
    private  Handler handler;
    private int COREPOOLSIZE = ThreadUtil.CPU_NUM + 1;
    private int MAXIMUMPOOLSIZE = COREPOOLSIZE * 3;
    private int KEEPLIVETIME = 60;



    private TaskScheduler()
    {
        // 用于消息调度的线程
        HandlerThread handlerThread = new HandlerThread("task-thread"); // 从主线程向子线程中发消息
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper(), new Handler.Callback()
        {

            // HandlerThread handleMessage 运行在子线程
            @Override
            public boolean handleMessage(Message msg)
            {
                switch(msg.what)
                {
                    case ITaskSchedulerType.SUBMIT_TASK:
                    {
                        doSubmitTask((AsyncTaskInstance) msg.obj);
                    }
                }
                return false;
            }
        });

        // 创建线程池

        BlockingQueue<Runnable> poolQueue = new LinkedBlockingDeque<>();
        this.executor = new PriorityThreadPoolExecutor(COREPOOLSIZE,MAXIMUMPOOLSIZE,KEEPLIVETIME, TimeUnit.SECONDS,poolQueue,new TaskThreadFactory());
    }

    private void doSubmitTask(AsyncTaskInstance taskInstance)
    {
        executor.submit(taskInstance);
    }

    public static TaskScheduler getInstance()
    {
        if(instance == null)
        {
            instance = new TaskScheduler();
        }
        return instance;
    }

    public void submit(AsyncTaskInstance instance)
    {
        handler.sendMessage(handler.obtainMessage(ITaskSchedulerType.SUBMIT_TASK,instance));
    }
}
