package com.Dzh.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

public class ShangHaiDetailBean
{
    public int error_code;
    public String reason;
    public XiaoHuaListBean result;



    public static class XiaoHuaListBean
    {
        public ArrayList<XiaoHuaBean> data;
    }

    public static class XiaoHuaBean
    {
        public String content;
        public String hashId;
        public String unixtime;
        public String updatetime;
    }
}
