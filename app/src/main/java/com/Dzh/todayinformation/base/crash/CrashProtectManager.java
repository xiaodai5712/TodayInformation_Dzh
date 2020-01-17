package com.Dzh.todayinformation.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2020/1/3
 * author: Dzh
 */
public class CrashProtectManager
{
    private String TAG = "CrashProtectManager";
    private static CrashProtectManager mInstance;
    private static Context mContext;

    private CrashProtectManager()
    {

    }

    public static CrashProtectManager getInstance(Context context)
    {
        if(mInstance == null)
        {
            mContext = context.getApplicationContext();
            mInstance = new CrashProtectManager();
        }
        return mInstance;
    }

    public void init()
    {
        // crash防护
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                handleFileException(e);
                if(t == Looper.getMainLooper().getThread())
                {
                    handleMainThread(e);
                }
            }
        });
    }

    private void handleFileException(Throwable e)
    {
        // 通过throw
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.close();
        String crashInformation = printWriter.toString();

        // 定义文件名
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH-mm-ss");
        String time = dateFormat.format(new Date());
        String fileName = "crash" + time + ".txt";

        try
        {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                File cacheDir = mContext.getCacheDir();

                if (!cacheDir.exists())
                {
                    cacheDir.mkdirs();
                }
                File crashFile = new File(cacheDir.getAbsolutePath(), fileName);
                if (!crashFile.exists())
                {
                    crashFile.createNewFile();
                }
                FileOutputStream outputStream = new FileOutputStream(crashFile);
                outputStream.write(crashInformation.getBytes());
                outputStream.close();
            }
        }catch (Exception ee)
        {
            ee.printStackTrace();
        }

    }

    private void handleMainThread(Throwable e)
    {
        while (true)
        {
            try
            {
                Looper.loop();
            }catch (Throwable throwable)
            {
                handleFileException(throwable);
            }
        }
    }
}
