package com.gapcoder.mango.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by suxiaohui on 2018/2/26.
 */

public class Net {
    public static boolean check(Context context){
        ConnectivityManager conn=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network=conn.getActiveNetworkInfo();
        if(network!=null&&network.isAvailable()){
            return true;
        }else{
            return false;
        }
    }
}
