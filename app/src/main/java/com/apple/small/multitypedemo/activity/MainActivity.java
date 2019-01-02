package com.apple.small.multitypedemo.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apple.small.multitypedemo.MsgSender;
import com.apple.small.multitypedemo.MsgType;
import com.apple.small.multitypedemo.R;
import com.apple.small.multitypedemo.adapter.MultiTypeRecyclerAdapter;
import com.apple.small.multitypedemo.adapter.RVBaseViewHolder;
import com.apple.small.multitypedemo.firsttype.FirstItemBean;
import com.apple.small.multitypedemo.firsttype.FirstTypeItem;
import com.apple.small.multitypedemo.secondtype.SecondItemBean;
import com.apple.small.multitypedemo.secondtype.SecondTypeItem;
import com.apple.small.multitypedemo.thirdtype.ThirdItemBean;
import com.apple.small.multitypedemo.thirdtype.ThirdTypeItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MsgType, MsgSender.IRec{

    private RecyclerView mRv;
    private MultiTypeRecyclerAdapter mMultiAdapter;
    private FirstTypeItem mFirstTypeItem;
    private SecondTypeItem mSecondTypeItem;
    private ThirdTypeItem mThirdTypeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.mRv);
        mMultiAdapter = new MultiTypeRecyclerAdapter();

        mFirstTypeItem = new FirstTypeItem(R.layout.first_item);
        mSecondTypeItem = new SecondTypeItem(R.layout.second_item);
        mThirdTypeItem = new ThirdTypeItem(R.layout.third_item);

        mMultiAdapter.addType(mFirstTypeItem);
        mMultiAdapter.addType(mSecondTypeItem);
        mMultiAdapter.addType(mThirdTypeItem);

        //设置布局
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置间距
        mRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRv.setAdapter(mMultiAdapter);
        MsgSender.getInstance().setIRec(this);
    }

    private void makeFakeData(ArrayList<FirstItemBean> firstList, ArrayList<SecondItemBean> secondList, ArrayList<ThirdItemBean> thirdList) {
        FirstItemBean firstItemBean = new FirstItemBean();
        firstItemBean.itemId = "1-01";
        firstItemBean.itemTxt = "1-01txt";
        firstItemBean.itemType = "1-01type";
        firstList.add(firstItemBean);

        SecondItemBean secondItemBean = new SecondItemBean();
        secondItemBean.secondItemId = "2-01";
        secondItemBean.secondItemTxt = "2-01txt";
        secondItemBean.secondItemType = "2-01type";
        secondList.add(secondItemBean);

        ThirdItemBean thirdItemBean = new ThirdItemBean();
        thirdItemBean.thirdItemId = "3-01";
        thirdItemBean.thirdItemTxt = "3-01txt";
        thirdItemBean.thirdItemType = "3-01type";
        thirdList.add(thirdItemBean);
    }

    private void initData() {
        ArrayList<FirstItemBean> firstList = new ArrayList<>();
        ArrayList<SecondItemBean> secondList = new ArrayList<>();
        ArrayList<ThirdItemBean> thirdList = new ArrayList<>();
        makeFakeData(firstList, secondList, thirdList);

        mMultiAdapter.clear();
        ArrayList<Long> ids = mMultiAdapter.ids();
        if (firstList.size() > 0) {
            mFirstTypeItem.addData(ids, firstList, null);
        }
        if (secondList.size() > 0) {
            mSecondTypeItem.addData(ids, secondList, null);
        }
        if (thirdList.size() > 0) {
            mThirdTypeItem.addData(ids, thirdList, null);
        }

        mMultiAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClickMsg(Message msg) {
        if (msg.obj == null || !(msg.obj instanceof TextView)) {
            return;
        }

        TextView textView = (TextView) msg.obj;
        switch (msg.what) {
            case FIRST_TYPE :
                textView.setText("first Clicked");
                break;
            case SECOND_TYPE:
                textView.setText("second Clicked");
                break;
            case THIRD_TYPE:
                textView.setText("third Clicked");
                break;
        }
    }
}
