package com.szlk.picstore.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.szlk.picstore.R;
import com.szlk.picstore.utils.BaseViewHolder;
import com.szlk.picstore.utils.CommonBaseAdapter;

/**
 * Created by C.C on 2016/8/18.
 */
public class RvAdapter extends CommonBaseAdapter<String> {
    private Activity activity;
    public RvAdapter(RecyclerView rv, int itemLayoutId) {
        super(rv, itemLayoutId);
        activity = (Activity) rv.getContext();
    }

    @Override
    public void bindViewData(BaseViewHolder holder, final String item, int position) {
        final ImageView iv = holder.getView(R.id.iv_item_rv_layout);
        iv.post(new Runnable() {
            @Override
            public void run() {
               Glide.with(activity).load(item).override(iv.getWidth(),iv.getHeight()).into(iv);
            }
        }) ;
    }
}
