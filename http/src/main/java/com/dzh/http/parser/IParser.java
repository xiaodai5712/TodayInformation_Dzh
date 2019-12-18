package com.dzh.http.parser;

import com.dzh.http.request.IRequest;
import com.dzh.http.response.IResponse;
import com.dzh.http.result.IResult;

public interface IParser
{
    IResult parseResponse(IRequest request, IResponse response);
}
