package com.dzh.http;

import com.dzh.http.parser.IParser;
import com.dzh.http.request.IRequest;
import com.dzh.http.request.call.ICall;
import com.dzh.http.response.IResponse;
import com.dzh.http.result.IResult;

public abstract class HttpScheduler
{
    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call)
    {
        // IResponse 和 IResult进行转换
        IResponse iResponse = call.execute();
        IRequest request = call.getRequest();
        IParser parser = request.getParser();
        return parser.parseResponse(request,iResponse);
    }
}
