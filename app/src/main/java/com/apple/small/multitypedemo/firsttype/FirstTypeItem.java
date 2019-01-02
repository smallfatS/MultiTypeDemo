package com.apple.small.multitypedemo.firsttype;


import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apple.small.multitypedemo.MsgType;
import com.apple.small.multitypedemo.R;
import com.apple.small.multitypedemo.adapter.IViewHolderType;
import com.apple.small.multitypedemo.adapter.RVBaseViewHolder;
import com.apple.small.multitypedemo.adapter.ViewHolderRVFactory;

import java.util.ArrayList;

public class FirstTypeItem extends ViewHolderRVFactory<FirstItemBean> implements MsgType, IViewHolderType {
    //viewholder页面控件
    private TextView mTvTxt;
    private ImageView mIvBg;

    public FirstTypeItem(int layoutId) {
        super(layoutId);
        mType = TYPE_FIRST;
        mDatas = new ArrayList<>();
    }

    @Override
    public void bindViewHolder(RVBaseViewHolder holder, int index) {
        mTvTxt = holder.getView(R.id.mTvTxt);
        mIvBg = holder.getView(R.id.mIvBg);
        holder.setOnClickListener(mTvTxt,FIRST_TYPE,0,0,mTvTxt);
        fillData(index);
    }

    public void fillData(int position) {
        FirstItemBean bean = mDatas.get(position);
        mTvTxt.setText(bean.itemTxt);
        mIvBg.setImageResource(R.drawable.ic_launcher_background);
    }

}
