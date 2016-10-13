package com.szlk.picstore;

/**
 * Created by C.C on 2016/8/8.
 */
public interface BaseView<T> {
    void bindPresenter(T presenter);
    void initView();
}
