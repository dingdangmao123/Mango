package com.gapcoder.mango.Utils;

import com.gapcoder.mango.R;

import java.util.Random;

/**
 * Created by suxiaohui on 2018/2/27.
 */

public class MyColor {
    private static Random r;
    public static final  int[] data=new int[]{R.drawable.bg1,R.drawable.bg2, R.drawable.bg3,
            R.drawable.bg4,R.drawable.bg5,R.drawable.bg6,R.drawable.bg7,R.drawable.bg8,
            R.drawable.bg9,R.drawable.bg10
    };
    public static int get(){
        if(r==null)
            r=new Random();
        return MyColor.data[r.nextInt(MyColor.data.length)];
    }
}
