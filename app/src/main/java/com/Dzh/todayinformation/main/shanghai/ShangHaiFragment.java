package com.Dzh.todayinformation.main.shanghai;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.tools.AnimationUtil;
import com.Dzh.todayinformation.base.BaseFragment;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.base.tools.OnDoubleClickListener;
import com.Dzh.todayinformation.main.shanghai.adapter.ShanghaiAdapter2;
import com.Dzh.todayinformation.main.shanghai.lf.IPlayerServiceContract;
import com.Dzh.todayinformation.main.shanghai.presenter.PlayerServicePresenter;
import com.Dzh.todayinformation.main.shanghai.view.MarqueeTextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment implements IPlayerServiceContract.IView
{

    IPlayerServiceContract.IPresenter mPresenter = new PlayerServicePresenter(this);
    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingToolBarLayout)
    CollapsingToolbarLayout shanghaiCollapsingToolBarLayout;
    @BindView(R.id.shanghai_app_barLayout)
    AppBarLayout shanghaiAppBarLayout;
    @BindView(R.id.shanghai_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_marquee_title)
    MarqueeTextView tvMarqueeTitle;
    private String TAG = "测试ShangHaiFragment";
    private boolean mIsPalying;

    @Override
    public void afterBindView()
    {
        Log.d(TAG, "afterBindView: ");

        initRecyclerView();
        initListener();
    }

    private void initRecyclerView()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(),false));
        mRecyclerView.setAdapter(new ShanghaiAdapter2());
    }

    private void initListener()
    {
        shanghaiAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i)
            {
                Log.d(TAG, "onOffsetChanged: 偏移值为：" + i + " ,高度为：" + appBarLayout.getMeasuredHeight());
                if (-i < appBarLayout.getMeasuredHeight() / 2)
                { // 这里的 i 是appBarLayout的位移量
                    tvShanghaiWelcome.setVisibility(View.INVISIBLE);
                    tvMarqueeTitle.setVisibility(View.INVISIBLE);
                } else
                {
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                    if(mIsPalying)
                    {
                        tvMarqueeTitle.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        tvShanghaiWelcome.setOnClickListener( new OnDoubleClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 属性动画
                if(mIsPalying)
                {
                    // 关闭音视频动画
                    AnimationUtil.startTranslationXAnim(tvShanghaiWelcome,tvShanghaiWelcome.getTranslationX(),tvShanghaiWelcome.getTranslationX()+300,null);
                    AnimationUtil.startTranslationXAnim(tvMarqueeTitle,tvMarqueeTitle.getTranslationX(),tvMarqueeTitle.getTranslationX()+200,null);
                    tvMarqueeTitle.setVisibility(View.GONE);
                    mPresenter.playOrPaused();
                }
                else
                {
                    // 开启音视频动画
                    AnimationUtil.startTranslationXAnim(tvShanghaiWelcome,tvShanghaiWelcome.getTranslationX(),tvShanghaiWelcome.getTranslationX()-300,null);
                    AnimationUtil.startTranslationXAnim(tvMarqueeTitle, tvMarqueeTitle.getTranslationX(), tvMarqueeTitle.getTranslationX() - 200, new AnimatorListenerAdapter()
                    {
                        @Override
                        public void onAnimationEnd(Animator animation)
                        {
                            tvMarqueeTitle.setVisibility(View.VISIBLE);
                            // 启动Service去播放音乐
                            mPresenter.bindService(mContext);
                        }
                    });
                }
                mIsPalying = !mIsPalying;
            }
        }));
    }
}
