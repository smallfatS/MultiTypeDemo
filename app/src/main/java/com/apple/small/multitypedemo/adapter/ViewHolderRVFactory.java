package com.apple.small.multitypedemo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewHolderRVFactory<T extends Object> {
    public int mType;//viewHolder类型
    public MultiTypeRecyclerAdapter mAdapter; // 归属adapter
    // 数据
    public ArrayList<T> mDatas;
    private int mLayoutId;  //item布局

    public ViewHolderRVFactory(int layoutId) {
        this.mLayoutId = layoutId;
    }

    public Object getItem(int i) {
        if (mDatas != null && i < mDatas.size()) {
            return mDatas.get(i);
        }
        return null;
    }

    public void clear(boolean force) {
        if (mDatas != null) mDatas.clear();
    }

    public long addData(T t) {
        long id = id(mDatas.size());
        mDatas.add(t);
        return id;
    }

    public long id(int idx) {
        Log.i("ssytest", "idx:" + idx + "mType:" + mType);
        return MultiTypeRecyclerAdapter.buildID(mType, idx);
    }

    public void addData(ArrayList<Long> ids, ArrayList<T> tl, Long sepId) {
        addData(ids, tl, sepId, false);
    }

    public void addData(ArrayList<Long> ids, ArrayList<T> tl, Long sepId, boolean isHideLastSep) {
        if (tl == null || tl.size() == 0) return;
        int idx = mDatas.size();
        if (sepId != null) {
            for (T t : tl) {
                ids.add(id(idx++));
                ids.add(sepId);
            }
            if (isHideLastSep) {
                ids.remove(ids.size() - 1);
            }
        } else {
            for (T t : tl) {
                ids.add(id(idx++));
            }
        }
        mDatas.addAll(tl);
    }

    public void addData(ArrayList<Long> ids, ArrayList<T> tl, Long sepId, boolean isHideLastSep, int start, int end) {
        if (tl == null || tl.size() == 0 || end <= start) return;
        int count = tl.size();
        if (count <= end) end = count - 1;
        count = mDatas.size();
        if (sepId != null) {
            for (; start < end; start++) {
                ids.add(id(count++));
                ids.add(sepId);
                mDatas.add(tl.get(start));
            }
            if (isHideLastSep) {
                ids.remove(ids.size() - 1);
            }
        } else {
            for (; start < end; start++) {
                ids.add(id(count++));
                mDatas.add(tl.get(start));
            }
        }
    }

    public RVBaseViewHolder createViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new RVBaseViewHolder(view);
    }

    // 子类实现
    public void bindViewHolder(final RVBaseViewHolder holder, int index) {
    }

    public int getItemType() {
        return mType;
    }

}
