package com.Dzh.todayinformation.main;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.Dzh.todayinformation.R;
import com.Dzh.todayinformation.base.BaseActivity;
import com.Dzh.todayinformation.base.MainActivityPresenter;
import com.Dzh.todayinformation.base.ViewInject;
import com.Dzh.todayinformation.main.tools.MainConstantTool;
import com.Dzh.todayinformation.mvp.presenter.LifeCircleMvpPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.IView
{
    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.main_rg_top)
    RadioGroup mainRgTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.main_rg_bottom)
    RadioGroup mainRgBottom;

    private boolean isChangeTopOrBottom;
    private String TAG = "测试MainAct";


    @Override
    public void afterBindView()
    {
        changeAnim(mainRgBottom,mainRgTop);
        initHomeFragment();
        initClickListener();
    }

    private void initClickListener()
    {
        rbMainShanghai.setChecked(true);
        mainRgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId == mPresenter.getCurrentCheckedId())
                    return;
                switch (checkedId)
                {
                    case R.id.rb_main_shanghai:
                        Log.d(TAG, "onCheckedChanged: 走了这里");
                        mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                        break;
                    case R.id.rb_main_hangzhou:
                        mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                        break;
                }
            }
        });

        mainRgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId == mPresenter.getCurrentCheckedId())
                {
                    return;
                }
                switch ((checkedId))
                {
                    case R.id.rb_main_beijing:
                        mPresenter.replaceFragment(MainConstantTool.BEIJING);
                        break;
                    case R.id.rb_main_shenzhen:
                        mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
                        break;
                }
            }
        });
    }

    // 初始化Fragment
    private void initHomeFragment()
    {
        mPresenter.initHomeFragment();
    }

    @OnClick(R.id.fac_main_home)
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if(isChangeTopOrBottom)
                {
                    changeAnim(mainRgTop,mainRgBottom);
                    handleTopPosition();
                }
                else
                {
                    changeAnim(mainRgBottom,mainRgTop);
                    handleBottomPosition();
                }
                break;
        }
    }

    // 北京 深圳
    private void handleBottomPosition()
    {
        Log.d(TAG, "handleBottomPosition: 当前的fragment 编号是 ：" + mPresenter.getCurrentCheckedIndex() );
        if(mPresenter.getTopPosition() != MainConstantTool.HANGZHOU)
        {
            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
            rbMainShanghai.setChecked(true);
        }
        else
        {
            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
            rbMainHangzhou.setChecked(true);
        }
    }

    // 上海杭州
    private void handleTopPosition()
    {
        Log.d(TAG, "handleBottomPosition: 当前的fragment 编号是 ：" + mPresenter.getCurrentCheckedIndex() );
        if(mPresenter.getBottomPosition() != MainConstantTool.SHENZHEN)
        {
            mPresenter.replaceFragment(MainConstantTool.BEIJING);
            rbMainBeijing.setChecked(true);
        }
        else
        {
            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            rbMainShenzhen.setChecked(true);
        }
    }

    private void changeAnim(RadioGroup gone,RadioGroup show)
    {
        // 消失的动画  translate 代表位移 alpha代表淡出淡入
        gone.clearAnimation(); // 清除自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        // 展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment mFragment)
    {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
        Log.d(TAG, "addFragment: show 完了");
        // 为什么初始化的时候，上海的fragment没有执行show方法，还是能被展示出来 这个方法是在切换的时候起作用的，初始化的时候
        // 自动加载Fragment
    }

    @Override
    public void addFragment(Fragment mFragment)
    {
        Log.d(TAG, "addFragment: ");
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,mFragment).commit();

    }

    @Override
    public void hideFragment(Fragment mFragment)
    {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
