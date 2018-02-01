package com.gapcoder.mango;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class marker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker);
        if (Build.VERSION.SDK_INT >= 21) {
            View d = getWindow().getDecorView();
            d.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        init();
    }

    private void init() {
        Button btn = (Button) findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RelativeLayout about = (RelativeLayout) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(marker.this, "芒果天气是一个简洁的天气App,只提供最简洁的功能，没有多余", Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout version = (RelativeLayout) findViewById(R.id.version);
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(marker.this, "v1.0 by gapcoder", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
