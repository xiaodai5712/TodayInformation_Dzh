package com.dzh.mvp.mvp;

public interface IMvpView
{
    // 中介者设计模式中的抽象同事
    MvpController getMvpController();
}
