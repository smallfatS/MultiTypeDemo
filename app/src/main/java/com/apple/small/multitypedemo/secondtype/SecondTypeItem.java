package com.apple.small.multitypedemo.secondtype;


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

public class SecondTypeItem extends ViewHolderRVFactory<SecondItemBean> implements MsgType, IViewHolderType {

    //viewholder页面控件
    private TextView m2ndTvType;
    private ImageView m2ndIvBg;

    public SecondTypeItem(int layoutId) {
        super(layoutId);
        mType = TYPE_SECOND;
        mDatas = new ArrayList<>();
    }

    @Override
    public void bindViewHolder(RVBaseViewHolder holder, int index) {
        m2ndTvType = holder.getView(R.id.m2ndTvType);
        m2ndIvBg = holder.getView(R.id.m2ndIvBg);
        holder.setOnClickListener(m2ndTvType, SECOND_TYPE, 0,0,m2ndTvType);
        fillData(index);
    }

    public void fillData(int position) {
        SecondItemBean bean = mDatas.get(position);
        m2ndTvType.setText(bean.secondItemType);
        m2ndIvBg.setImageResource(R.mipmap.pic2);
    }
}
