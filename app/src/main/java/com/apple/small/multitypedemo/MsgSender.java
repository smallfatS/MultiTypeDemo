package com.apple.small.multitypedemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MsgSender implements Handler.Callback{

    public static MsgSender INSTANCE = new MsgSender();

    // 发送消息 页面处理
    private  Handler mHandler;
    public void initHandler() {
        if (mHandler==null) {
            mHandler = new Handler(Looper.getMainLooper(),this);
        }
    }

    public MsgSender(){}
    public static MsgSender getInstance() {
        return INSTANCE;
    }

    private static IRec mIRec;

    @Override
    public boolean handleMessage(Message msg) {
        mIRec.onClickMsg(msg);
        return false;
    }

    public void sendMsg(int what, int arg1, int arg2, Object obj){
        if (mHandler==null) {
            initHandler();
        }
        mHandler.removeMessages(what);
        mHandler.obtainMessage(what, arg1, arg2, obj).sendToTarget();
    }

    // 页面设置具体回调逻辑
    public void setIRec(IRec iRec) {
        this.mIRec = iRec;
    }

    public static interface IRec {
        public void onClickMsg(Message msg);
    }
}
