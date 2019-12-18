package com.Dzh.todayinformation.main.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.Dzh.todayinformation.main.shanghai.moudle.ShangHaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

public class GetXiaoHuatTask extends AsyncTask<Object,Object,Object>
{

    // 运行在子线程中
    @Override
    protected Object doInBackground(Object[] objects)
    {
        return new ShangHaiDetailHttpTask().getXiaoHuaList((String)objects[0], (String)objects[1], (String)objects[2]);
    }


    // 运行在主线程，用于更新数据
    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        if(o == null)
        {
            Log.e( "onPostExecute: ", " o = null");
        }
        Response response = (Response) o;
        try
        {
            Log.e("GetXiaoHuatTask", "onPostExecute: " + response.body().string());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
