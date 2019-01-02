package com.apple.small.multitypedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;

// 实现多种类型的adapter
// 保存id的list list代表条目数
// id高32位=type(从1开始) id低32位=数据下标
public class MultiTypeRecyclerAdapter extends RecyclerView.Adapter<RVBaseViewHolder> {

    public static final int FLAG_ITEM_CHANGE = 0;
    public static final int FLAG_ITEM_INSERT = 1;
    public static final int FLAG_ITEM_REMOVE = 2;

    protected ArrayList<Long> mIds = new ArrayList<Long>(8);
    protected SparseArray<ViewHolderRVFactory> mVhfs = new SparseArray<>(8);

    public static long buildID(int type, int index) {
        return ((long) type) << 32 | index;
    }

    // 追加一个/多个id
    public static void buildIDs(ArrayList<Long> ids, int type, int index, int count) {
        count += index;
        for (; index < count; ++index) {
            ids.add(((long) type) << 32 | index);
        }
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderRVFactory vhf = mVhfs.get(viewType);
        Log.i("ssytest", "viewType:" + viewType);
        return vhf.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(final RVBaseViewHolder holder, int position) {
        long id = getItemId(position);
        int type = (int) (id >> 32); // type
        Log.i("ssytest", "id:" + id + "--position:" + position + "--type:" + type);
        ViewHolderRVFactory vhf = mVhfs.get(type);
        // bind具体放到ViewHolder子类中实现
        int index = (int)(id&0xffffffff); // index
        vhf.bindViewHolder(holder,index);
    }

    @Override
    public int getItemViewType(int position) {
        long id = getItemId(position);
        int type = (int) (id >> 32); // type
        ViewHolderRVFactory vhf = mVhfs.get(type);
        return vhf.getItemType();
    }

    @Override
    public long getItemId(int position) {
        return mIds.get(position);
    }

    @Override
    public int getItemCount() {
        return mIds.size();
    }

    @Override
    public void onViewRecycled(RVBaseViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public ArrayList<Long> ids() {
        return mIds;
    }

    //清除所有数据
    public void clear() {
        for (int i = 0; i < mVhfs.size(); i++) {
            ViewHolderRVFactory vf = mVhfs.valueAt(i);
            vf.clear(false);
        }
        mIds.clear();
    }

    public MultiTypeRecyclerAdapter addType(ViewHolderRVFactory vhf) {
        vhf.mAdapter = this;
        mVhfs.put(vhf.mType, vhf);
        return this;
    }

    public void updateItem(int type, int position, int count) {
        switch (type) {
            case FLAG_ITEM_CHANGE:
                notifyItemRangeChanged(position, count);
                break;
            case FLAG_ITEM_INSERT:
                notifyItemRangeInserted(position, count);
                break;
            case FLAG_ITEM_REMOVE:
                notifyItemRangeRemoved(position, count);
                break;
        }

    }

}
