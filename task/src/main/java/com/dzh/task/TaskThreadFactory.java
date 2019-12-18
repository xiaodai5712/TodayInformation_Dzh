package com.dzh.task;

import java.util.concurrent.ThreadFactory;

public class TaskThreadFactory implements ThreadFactory
{
    @Override
    public Thread newThread(Runnable r)
    {
        return new Thread(r,"task_thread_pool");
    }
}
