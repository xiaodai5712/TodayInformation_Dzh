package com.dzh.http.result;

/*
* 所有IResponse解析后的结果
*/
public interface IResult<T>
{
    boolean isSuccess();
    int getCode();
    T data();
}
