package com.Dzh.todayinformation.base;

import com.dzh.http.request.host.IHost;

public interface HostManager
{
    IHost jhhost = new IHost()
    {
        @Override
        public String getHost()
        {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath()
        {
            return "/joke/content/list.php";
        }
    };


}
