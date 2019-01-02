package com.apple.small.multitypedemo.adapter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.apple.small.multitypedemo.MsgSender;


public class RVBaseViewHolder extends RecyclerView.ViewHolder {
    private View mItemView;
    private SparseArray<View> views;



    public RVBaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        mItemView = itemView;
    }

    public int getViewPosition() {
        return this.getPosition();
    }

    public <V extends View> V getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
    }

    public RVBaseViewHolder setOnClickListener(View view, final int what, final int arg1, final int arg2, final Object obj) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgSender.getInstance().sendMsg(what, arg1, arg2, obj);
            }
        });
        return this;
    }

}
