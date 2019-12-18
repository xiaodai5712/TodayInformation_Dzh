package com.Dzh.todayinformation.base;

import com.dzh.http.parser.DefaultResultParser;
import com.dzh.http.request.IRequest;
import com.dzh.http.annotation.RequestMethod;
import com.dzh.http.request.LfRequest;

import java.lang.reflect.Type;

public class JHRequest extends LfRequest
{
    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type)
    {
        JHRequest request = new JHRequest();
        request.host =HostManager.jhhost;
        request.path = path;
        request.requestMethod = requestMethod;
        request.type = type;
        request.resultParser = DefaultResultParser.getInstance();
        return request;

    }
}
