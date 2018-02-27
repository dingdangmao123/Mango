package com.gapcoder.mango;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gapcoder.mango.Model.Config;
import com.gapcoder.mango.Utils.ConfigTool;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class marker extends Base {

    void init() {
        setContentView(R.layout.marker);
        ButterKnife.bind(this);

        sb.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                save(isChecked);
            }
        });
        Config ins = ConfigTool.parse(this);
        sb.setChecked(ins.isAutoLocation());
    }


    @BindView(R.id.sb)
    SwitchButton sb;

    @OnClick(R.id.back)
    void back() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    @OnClick(R.id.about)
    void about() {
        Toast.makeText(marker.this, "芒果天气是一个简洁的天气App,只提供最简洁的功能，没有多余", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.version)
    void version() {
        Toast.makeText(marker.this, "v1.2 by gapcoder", Toast.LENGTH_SHORT).show();
    }

    private void save(boolean isCheck) {
        SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
        editor.putBoolean("autoLocation", isCheck);
        editor.apply();
    }

}
