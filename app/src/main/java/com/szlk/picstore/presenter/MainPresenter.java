package com.szlk.picstore.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.szlk.picstore.R;
import com.szlk.picstore.activity.ShowPicActivity;
import com.szlk.picstore.adapter.RvAdapter;
import com.szlk.picstore.contract.MainContract;
import com.szlk.picstore.model.MainModel;
import com.szlk.picstore.utils.CommonBaseAdapter;
import com.szlk.picstore.utils.RVItemDecoration;

import java.util.List;

/**
 * Created by C.C on 2016/8/18.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainModel model;
    private RvAdapter adapter;

    public static MainPresenter newInstance(MainContract.View view) {
        return new MainPresenter(view);
    }

    private MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.bindPresenter(this);
        this.view.initView();
        model = new MainModel();
    }

    @Override
    public void getRecyclerView(final RecyclerView recyclerView) {
        //设置RecyclerView配置
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new RVItemDecoration(16));
        //适配器
        adapter = new RvAdapter(recyclerView, R.layout.item_layout);
        recyclerView.setAdapter(adapter);
        //点击事件
        adapter.setItemListener(new CommonBaseAdapter.onRecyclerItemClickerListener() {
            @Override
            public void onRecyclerItemClick(View view, Object data, int position) {
                 if (null != data){
                     String picPath = (String) data;
                     startActivity(recyclerView.getContext(),picPath); //启动ShowPicActivity
                 }
            }
        });
    }

    /**
     *  启动ShowPicActivity
     */
    private void startActivity(Context context,String picPath) {
        Intent intent = new Intent(context, ShowPicActivity.class);
        Bundle bundle = new Bundle ();
        bundle.putString("picPath",picPath);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void load(final Activity activity) {
        if (view == null) return;
        model.loadPic(activity, new MainContract.MainBiz.onLoadPicListener() {
            @Override
            public void onLoadBefore() {
                view.showProgress();
            }//拿到图片路径集合前

            @Override
            public void onLoadAfter() {
                view.closeProgress();
            }//拿到图片路径集合后

            @Override
            public void onResult(List<String> picPathList) {//拿到图片路径集合
                if (adapter != null) adapter.setData(picPathList);
            }

            @Override
            public void onFailed(String info) {
                view.showInfo(info);
            }//获取图片路径失败
        });
    }

    @Override
    public void recycle() {
        if (model != null) model = null;
        if (view != null) view = null;
    }
}
