package com.gapcoder.mango.Model;

/**
 * Created by suxiaohui on 2018/2/26.
 */

public class Config {
    private boolean autoLocation;

    private String themeColor;

    public boolean isAutoLocation() {
        return autoLocation;
    }

    public void setAutoLocation(boolean autoLocation) {
        this.autoLocation = autoLocation;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }
}
