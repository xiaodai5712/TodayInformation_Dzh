package com.Dzh.todayInformation.player.source;

import java.io.File;

/**
 * Date: 2020/1/10
 * author: Dzh
 */
public class RawPlayerSource implements IPlayerSource
{
    private String url;
    private int resId;

    @Override
    public void setUrl(String url)
    {
        this.url = url;
    }

    public IPlayerSource setPath(String packageName, int rawId)
    {

        setUrl( "android.resource://" + packageName + File.separator + rawId);
        setResId(rawId);
        return this;
    }

    private void setResId(int rawId)
    {
        this.resId = rawId;
    }


    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public int getResId()
    {
        return resId;
    }
}
