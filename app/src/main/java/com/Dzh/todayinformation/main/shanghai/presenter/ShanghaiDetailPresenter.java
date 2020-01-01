package com.Dzh.todayinformation.main.shanghai.presenter;

import android.util.Log;

import com.Dzh.todayinformation.base.BasePresenter;
import com.Dzh.todayinformation.base.JHTask;
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.Dzh.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.Dzh.todayinformation.main.shanghai.moudle.ShangHaiDetailHttpTask;
import com.dzh.http.result.IResult;
import com.google.gson.Gson;


public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.IView> implements IShanghaiDetailContract.IPresenter
{

    private String TAG = "ShanghaiDetailPresenter";

    public ShanghaiDetailPresenter(IShanghaiDetailContract.IView view)
    {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.IView getEmptyView()
    {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData(int pagesize)
    {
//         1 数据的结果解析 2 短时间内多次的重复任务请求怎么处理

//        submitTask(new LfTask()
//        {
//            // 一定要回到主线程
//            @Override
//            public void onComplete(Object o)
//            {
//                // 获取网络结果
//
//                Log.e( "getNetData: ","succeed : " + o );
//
//            }
//
//            @Override
//            public void onException(Throwable throwable)
//            {
//                Log.e( "getNetData: ","failure: " + throwable.toString() );
//            }
//
//
//            // 运行于子线程
//            @Override
//            public Object onBackground()
//            {
//                IResult desc =  new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
//                return  desc;
//            }
//        });
        /*
        * 1 合理利用继承关系
        * 2 合理利用抽象编程
        * 3 合理利用泛型
        * 4 合理利用一些设计模式
        */

        submitTask(new JHTask<ShangHaiDetailBean>()
        {
            @Override
            public IResult<ShangHaiDetailBean> onBackground()
            {
                return new ShangHaiDetailHttpTask<ShangHaiDetailBean>().getXiaoHuaList("desc", "1", pagesize+"");
            }

            @Override
            public void onSuccess(IResult<ShangHaiDetailBean> t)
            {
                ShangHaiDetailBean data = t.data();
                getView().showData(data);
//                Gson gson = new Gson();
//                String s = gson.toJson(data);
//                Log.e(TAG, "onSuccess: "+ s);
            }
        });
    }
}
