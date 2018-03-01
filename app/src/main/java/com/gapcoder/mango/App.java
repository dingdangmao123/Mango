package com.gapcoder.mango;

import android.app.Application;

import com.zhy.changeskin.SkinManager;

/**
 * Created by suxiaohui on 2018/2/28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
