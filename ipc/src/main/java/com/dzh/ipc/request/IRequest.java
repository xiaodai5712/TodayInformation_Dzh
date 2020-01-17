package com.dzh.ipc.request;

import com.dzh.ipc.CallBack;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Date: 2020/1/15
 * author: Dzh
 */
public interface IRequest extends Comparable<IRequest>
{
    void setParams(String params);
    String getParams();

    String getRequestKey();

    void addCallBack(CallBack callBack);

    CallBack getCallBack();
    long getAddTime();
}
