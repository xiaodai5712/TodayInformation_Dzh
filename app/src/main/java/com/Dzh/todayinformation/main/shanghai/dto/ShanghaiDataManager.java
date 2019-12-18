package com.Dzh.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

public class ShanghaiDataManager
{
    // 获取竖直方向数据
    private static ArrayList<ShanghaiBean> getVerData(int len)
    {
        ArrayList<ShanghaiBean> data = new ArrayList<>();
        for (int i = 0; i < len; i++)
        {
            ShanghaiBean bean = new ShanghaiBean();
            bean.setShowImg(false).setmDec("上海欢迎您");
            data.add(bean);
        }
        return data;
    }

    // 获取横向数据
    private static ArrayList<ShanghaiBean> getHorData(int len)
    {
        ArrayList<ShanghaiBean> data = new ArrayList<>();
        for (int i = 0; i < len; i++)
        {
            ShanghaiBean bean = new ShanghaiBean();
            bean.setShowImg(true).setmDec("上海是魔都");
            data.add(bean);
        }
        return data;
    }

    public static ArrayList<ShanghaiBean> getData()
    {
        ArrayList<ShanghaiBean> data = new ArrayList<>();
        data.addAll(getVerData(5));
        data.add(new ShanghaiBean().setData(getHorData(10)).setmItemType(ShanghaiBean.IShangshaiItemType.HORIZONTAL));
        data.addAll(getVerData(5));
        data.add(new ShanghaiBean().setData(getHorData(10)).setmItemType(ShanghaiBean.IShangshaiItemType.HORIZONTAL));
        return data;

    }

}
