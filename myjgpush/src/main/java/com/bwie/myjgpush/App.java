package com.bwie.myjgpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dell on 2017/1/11.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
