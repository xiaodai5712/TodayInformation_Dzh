package com.dzh.http.result;

/**
 * Date: 2019/12/13
 * Time: 16:03
 * author: Dzh
 */
public interface IResultCallBack<T>
{
    void onSuccess(IResult<T> t);
    void onFailed(IResult t);
}
