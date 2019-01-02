package com.apple.small.multitypedemo.thirdtype;


import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.apple.small.multitypedemo.MsgType;
import com.apple.small.multitypedemo.R;
import com.apple.small.multitypedemo.adapter.IViewHolderType;
import com.apple.small.multitypedemo.adapter.RVBaseViewHolder;
import com.apple.small.multitypedemo.adapter.ViewHolderRVFactory;
import com.apple.small.multitypedemo.firsttype.FirstItemBean;

import java.util.ArrayList;

public class ThirdTypeItem extends ViewHolderRVFactory<ThirdItemBean> implements MsgType, IViewHolderType {

    //viewholder页面控件
    private TextView m3rdTvId;

    public ThirdTypeItem(int layoutId) {
        super(layoutId);
        mType = TYPE_THIRD;
        mDatas = new ArrayList<>();
    }

    @Override
    public void bindViewHolder(RVBaseViewHolder holder, int index) {
        m3rdTvId = holder.getView(R.id.m3rdTvId);
        holder.setOnClickListener(m3rdTvId, THIRD_TYPE, 0,0,m3rdTvId);
        fillData(index);
    }

    public void fillData(int position) {
        ThirdItemBean bean = mDatas.get(position);
        m3rdTvId.setText(bean.thirdItemId);
    }
}
