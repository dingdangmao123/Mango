package com.gapcoder.mango;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class place extends AppCompatActivity {

    private String select = "";
    TextView current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);
        init();
    }

    private void init() {

        if (Build.VERSION.SDK_INT >= 21) {
            View d = getWindow().getDecorView();
            d.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        current = (TextView) findViewById(R.id.current);
        current.setText(getCity());

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("city", select);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });

    }

    private void select() {

        EditText t = (EditText) findViewById(R.id.city);
        select = t.getText().toString();
        if (select.length() == 0) {
            Toast.makeText(getApplicationContext(), "你还未输入城市", Toast.LENGTH_LONG).show();
            return;
        }

        current.setText(select);
        save();
    }

    private void save() {
        SharedPreferences.Editor editor = getSharedPreferences("city", MODE_PRIVATE).edit();
        editor.putString("name", select);
        editor.apply();
    }

    private String getCity() {
        SharedPreferences p = getSharedPreferences("city", MODE_PRIVATE);
        String c = p.getString("name", "北京");
        return c;
    }
}
