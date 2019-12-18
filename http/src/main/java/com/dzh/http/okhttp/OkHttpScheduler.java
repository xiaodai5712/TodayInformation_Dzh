package com.dzh.http.okhttp;

import com.dzh.http.HttpScheduler;
import com.dzh.http.annotation.RequestMethod;
import com.dzh.http.request.IRequest;
import com.dzh.http.request.call.ICall;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpScheduler extends HttpScheduler
{

    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request)
    {
        Map<String, Object> params = request.getParams();
        int requestMethod = request.getRequestMethod();
        Request.Builder requestBuilder = new Request.Builder();
        switch (requestMethod)
        {
            case RequestMethod.Get:
            {
                // 拼接get请求的url host+path
                StringBuilder urlStringBuilder = new StringBuilder(request.getHost().getHost());
                urlStringBuilder.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStringBuilder.toString()).newBuilder();

                if (params != null && params.size() > 0)
                {
                    Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext())
                    {
                        Map.Entry<String, Object> next = iterator.next();
                        // TODO: 2019/12/6   这里涉及Object如何转成String
                        urlBuilder.addQueryParameter(next.getKey(), (String) next.getValue());
                    }
                }
                requestBuilder.get().url(urlBuilder.build()).build();
                break;
            }
            case RequestMethod.Post:
            {
                // TODO: 2019/12/9  留待自己去完善
                break;
            }
        }

        Request okHttpRequest = requestBuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request,call);  // 这个采用了静态代理的设计模式
        return okHttpCall;
    }

    private OkHttpClient getClient()
    {
        if(client == null)
        {
            client = new OkHttpClient();
        }
        return  client;
    }
}
