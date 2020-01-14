package com.Dzh.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;


import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseActivity;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.Dzh.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.Dzh.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@ViewInject(mainLayoutId = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.IView
{
    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);
    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";
    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView()
    {
        initAnim();
        initGetNetData();
//        initPostNetData();
        ivShanghaiDetail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s = null;
                s.toString();
            }
        });
    }

    private void initPostNetData()
    {
        OkHttpClient client = new OkHttpClient(); // okHttp的一些默认配置
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key","0303606ea898a3f7d893b4651665b1c7");
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(builder.build())
                .build();  // 建造者设计模式
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("initGetNetData", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Log.e("initGetNetData", "onResponse: " + response.body().string());
            }
        });
    }

    private void initGetNetData()
    {
        mPresenter.getNetData(20);
//        GetXiaoHuatTask task = new GetXiaoHuatTask();
//        task.execute("desc","1","1");

//        Object desc = new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
//        if(desc instanceof Response)
//        {
//            Response response = (Response)desc;
//            Log.e("initGetNetData: ", response.body().toString());
//        }
//        // 1 创建一个client 可以隔离
//        OkHttpClient client = new OkHttpClient(); // okHttp的一些默认配置
//
//        // 2 构建请求 url 参数
//        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
//        builder.addQueryParameter("sort","desc");
//        builder.addQueryParameter("page","1");
//        builder.addQueryParameter("pagesize","3");
//        builder.addQueryParameter("time",""+ System.currentTimeMillis()/1000);
//        builder.addQueryParameter("key","8b9cf7e52a1557f08403e6ed6da328dc");
//
//        // 3 构建request
//        Request request = new Request.Builder()
//                .url(builder.build())
//                .get()
//                .build();  // 建造者设计模式
//
//        // 4 构建call
//        Call call = client.newCall(request);
//
//        // 5 执行网络请求
//        call.enqueue(new Callback()
//        {
//            @Override
//            public void onFailure(Call call, IOException e)
//            {
//                Log.e("initGetNetData", "onFailure: " + e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException
//            {
//                Log.e("initGetNetData", "onResponse: " + response.body().string());
//            }
//        });
    }

    private void initAnim()
    {
        // 延时加载
        //postponeEnterTransition();
        // 开启转转场动画
        startPostponedEnterTransition();
    }

    // 用于安卓5.0及以上系统的界面转场动画
    public static void start_5_0(Activity activity,View view)
    {
        Pair pair = new Pair(view,mActivityOptionsCompat);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair);
        ActivityCompat.startActivity(activity,new Intent(activity,ShanghaiDetailActivity.class),optionsCompat.toBundle());

    }

    @Override
    public void showData(ShangHaiDetailBean data)
    {

    }
}
