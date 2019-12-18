package com.dzh.http.request.call;

import com.dzh.http.request.IRequest;
import com.dzh.http.response.IResponse;


public interface ICall
{
    IResponse execute();

    IRequest getRequest();
}
