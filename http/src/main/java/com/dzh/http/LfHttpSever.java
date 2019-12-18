package com.dzh.http;

import com.dzh.http.request.IRequest;
import com.dzh.http.result.IResult;

import java.util.Map;

public class LfHttpSever
{
    protected<T> IResult<T> execute(IRequest request, Map<String,Object> params)
    {
        return HttpHelper.execute(request,params);
    }

}
