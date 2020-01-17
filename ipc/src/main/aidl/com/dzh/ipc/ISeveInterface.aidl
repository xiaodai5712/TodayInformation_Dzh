// ISeveInterface.aidl
package com.dzh.ipc;
import com.dzh.ipc.IClientInterface;
// Declare any non-default types here with import statements

interface ISeveInterface
{
    void executeAsync(String requestKey,String requestParams);

    String executeSync(String requestKey,String requestParams);

    void registerCallBack(IClientInterface iClientInterface);
}
