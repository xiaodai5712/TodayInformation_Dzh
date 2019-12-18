package com.dzh.http.parser;

import com.dzh.http.request.IRequest;
import com.dzh.http.response.IResponse;
import com.dzh.http.result.IResult;
import com.dzh.http.result.Result;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class DefaultResultParser implements IParser
{

    private static DefaultResultParser mInstance;
    private final Gson mGson;

    private DefaultResultParser()
    {
        mGson = new Gson();
    }

    public static IParser getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new DefaultResultParser();
        }
        return mInstance;
    }

    @Override
    public IResult parseResponse(IRequest request, IResponse response)
    {
        Type type = request.getType();
        String bodyString = response.getBodyString();
        Object object = mGson.fromJson(bodyString,type);
        return Result.success(object);
    }
}
