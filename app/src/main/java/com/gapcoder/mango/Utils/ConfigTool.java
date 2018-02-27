package com.gapcoder.mango.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gapcoder.mango.Model.Config;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by suxiaohui on 2018/2/26.
 */

public class ConfigTool {
    public static boolean getautoLocation(Context context){
        SharedPreferences p = context.getSharedPreferences("city", MODE_PRIVATE);
        return p.getBoolean("autoLocation", false);

    }
    public static Config parse(Context context){
        Config ins=new Config();
        SharedPreferences p = context.getSharedPreferences("config", MODE_PRIVATE);
        ins.setAutoLocation(p.getBoolean("autoLocation",true));
        return ins;
    }

}
