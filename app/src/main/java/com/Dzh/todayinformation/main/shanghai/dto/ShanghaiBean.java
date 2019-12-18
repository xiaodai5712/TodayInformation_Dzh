package com.Dzh.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

public class ShanghaiBean
{
    private int mItemType = IShangshaiItemType.VERTICAL;
    private String mDec;
    private boolean isShowImg;

    public int getmItemType()
    {
        return mItemType;
    }

    public ShanghaiBean setmItemType(int mItemType)
    {
        this.mItemType = mItemType;
        return this;
    }

    public String getmDec()
    {
        return mDec;
    }

    public ShanghaiBean setmDec(String mDec)
    {
        this.mDec = mDec;
        return this;
    }

    public boolean isShowImg()
    {
        return isShowImg;
    }

    public ShanghaiBean setShowImg(boolean showImg)
    {
        isShowImg = showImg;
        return this;
    }

    public ArrayList<ShanghaiBean> getData()
    {
        return data;
    }

    public ShanghaiBean setData(ArrayList<ShanghaiBean> data)
    {
        this.data = data;
        return this;
    }

    private ArrayList<ShanghaiBean> data;

    public interface IShangshaiItemType
    {
        int VERTICAL = 0;
        int HORIZONTAL = 1;
    }


}
