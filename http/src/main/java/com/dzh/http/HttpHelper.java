package com.dzh.http;

import com.dzh.http.okhttp.OkHttpScheduler;
import com.dzh.http.request.IRequest;
import com.dzh.http.request.call.ICall;
import com.dzh.http.result.IResult;

import java.util.Map;

public class HttpHelper
{
    private volatile static HttpScheduler httpScheduler;
    public static HttpScheduler getHttpScheduler()
    {
        if(httpScheduler == null)
        {
            synchronized (HttpHelper.class)
            {
                if(httpScheduler == null)
                {
                    httpScheduler = new OkHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }
    // TODO: 2019/12/6
    protected static <T> IResult<T> execute(IRequest request, Map<String, Object> params)
    {
        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler().execute(call);
    }
}
