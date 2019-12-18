package com.Dzh.todayinformation.main.shanghai.moudle;

import com.Dzh.todayinformation.base.JHRequest;
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.dzh.http.request.IRequest;
import com.dzh.http.annotation.RequestMethod;

public interface ShangHaiDetailRequest
{
    IRequest xiaoHuaRequest  = JHRequest.sendHttp("/joke/content/list.php",RequestMethod.Get, ShangHaiDetailBean.class);  // 这个是在接口中定义的以个常量
}
