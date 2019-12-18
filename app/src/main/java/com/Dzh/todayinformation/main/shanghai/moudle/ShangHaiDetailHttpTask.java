package com.Dzh.todayinformation.main.shanghai.moudle;

import com.dzh.http.LfHttpSever;
import com.dzh.http.result.IResult;

import java.util.HashMap;
import java.util.Map;

public class ShangHaiDetailHttpTask<T> extends LfHttpSever
{
    public IResult<T> getXiaoHuaList(String sort, String page, String pagesize)
    {
        // 2019/12/6

        Map<String,Object> params = new HashMap<>();
        params.put("sort",sort);
        params.put("page",page);
        params.put("pagesize", pagesize);
        params.put("time",""+ System.currentTimeMillis()/1000);
        params.put("key","8b9cf7e52a1557f08403e6ed6da328dc");
        return super.execute(ShangHaiDetailRequest.xiaoHuaRequest,params);
    }

}
