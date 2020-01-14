package com.Dzh.todayInformation.player.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Date: 2020/1/10
 * author: Dzh
 */
public class DataSourceUtil
{
    public static int getMetaDataFromApp(Context context)
    {
        int value = 0;
        try
        {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getInt("playertype");
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        // 读取配置
        return value;
    }
}
