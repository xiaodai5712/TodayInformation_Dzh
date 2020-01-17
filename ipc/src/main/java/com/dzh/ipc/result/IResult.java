package com.dzh.ipc.result;

/**
 * Date: 2020/1/15
 * author: Dzh
 */
public interface IResult
{
    boolean isSuccess();

    int getCode();

    String data();

}
