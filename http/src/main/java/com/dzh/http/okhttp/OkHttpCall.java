package com.dzh.http.okhttp;

import com.dzh.http.request.IRequest;
import com.dzh.http.request.call.ICall;
import com.dzh.http.response.IResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class OkHttpCall implements ICall
{

    private  IRequest request;
    private  Call call;

    public OkHttpCall(IRequest request, Call call)
    {
        this.call = call;
        this.request = request;
    }

    @Override
    public IResponse execute()
    {
        Response response = null;
        try
        {
            response = call.execute();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        OkHttpResponse okhttpResponse = new OkHttpResponse(response);
        return okhttpResponse;
    }

    @Override
    public IRequest getRequest()
    {
        return request;
    }
}
