package com.dzh.ipc.response;

import com.dzh.ipc.IClientInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Date: 2020/1/16
 * author: Dzh
 */
public class ResponseUtil
{

    // 反射用的类必须在 com.ipc.response 包名下面
    public static void getAsyncResponse(String requestKey, String requestParams, IClientInterface mClientInterface)
    {
        StringBuilder stringBuilder = new StringBuilder("com.ipc.response");
        String first = requestKey.substring(0, 1).toUpperCase();
        String second = requestKey.substring(1, requestKey.length());
        stringBuilder.append(first).append(second).append("Response");
        // 通过反射的方式调用 宿主的类里面
        try
        {
            Class<?> clazz = Class.forName(stringBuilder.toString());
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class, IClientInterface.class);
            Object object = constructor.newInstance(requestKey, requestParams, mClientInterface);
            Method method = clazz.getMethod(requestKey);
            method.invoke(object);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
