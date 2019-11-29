package com.Dzh.todayinformation.main.tools;

import androidx.annotation.IntDef;

import static com.Dzh.todayinformation.main.tools.MainConstantTool.BEIJING;
import static com.Dzh.todayinformation.main.tools.MainConstantTool.HANGZHOU;
import static com.Dzh.todayinformation.main.tools.MainConstantTool.SHANGHAI;
import static com.Dzh.todayinformation.main.tools.MainConstantTool.SHENZHEN;

@IntDef({SHANGHAI,HANGZHOU,SHENZHEN,BEIJING})
public @interface MainConstantTool
{
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;
}
