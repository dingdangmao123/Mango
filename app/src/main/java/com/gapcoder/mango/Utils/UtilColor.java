package com.gapcoder.mango.Utils;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by suxiaohui on 2018/1/31.
 */

public class UtilColor {
 private static int[] inner=new int[]{android.graphics.Color.parseColor("#3FE2C5"), android.graphics.Color.parseColor("#88673AB7"), android.graphics.Color.parseColor("#88009688"),
         android.graphics.Color.parseColor("#88FFEB3B"), android.graphics.Color.parseColor("#88FF9800"), android.graphics.Color.parseColor("#88F44336")
 };
        private static int[] outer=new int[]{android.graphics.Color.parseColor("#03A9F4"), android.graphics.Color.parseColor("#673AB7"), android.graphics.Color.parseColor("#009688"),
                 android.graphics.Color.parseColor("#FF9800"), android.graphics.Color.parseColor("#F44336")};

    public static void set(ViewGroup v, int tmper){
        int value=0;
        if(tmper<-10){
            value=0;
        }else if(tmper<0){
            value=1;
        }else if(tmper>40)
            value=5;
        else
            value=tmper/10+2;

        v.setBackgroundColor(inner[value]);
        int t=v.getChildCount();
        for(int i=0;i<t;i++) {
            if(v.getChildAt(i) instanceof TextView)
           ((TextView) (v.getChildAt(i))).setTextColor(outer[value]);
        }

    }

    public static void set(ViewGroup v, String tmper) {

        set(v,Integer.parseInt(tmper));
    }

}
