package com.gapcoder.mango;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class search extends AppCompatActivity {


    private HashSet<String> mytag=new HashSet<String>();
    private EditText ed;
    private TagContainerLayout mTag;
    private String select="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        init();

    }

    private void init(){

        if (Build.VERSION.SDK_INT >= 21) {
            View d = getWindow().getDecorView();
            d.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        Button btn=(Button)findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.putExtra("city",select);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        Button ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    select();
            }
        });

        mTag = (TagContainerLayout) findViewById(R.id.tag);

        List<String> tag=Arrays.asList("北京","上海","广州","深圳","南京","杭州","苏州","厦门");
        mTag.setTags(tag);
       // mTag.setTheme(0);
        mTag.setTagBackgroundColor(Color.TRANSPARENT);
        mTag.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                select=text;
                TextView t=(TextView)findViewById(R.id.select);
                t.setText(select);
            }

            @Override
            public void onTagLongClick(final int position, String text) {
            }

            @Override
            public void onTagCrossClick(int position) {
            }
        });
    }
    private void select(){
        EditText t=(EditText)findViewById(R.id.city);
        select=t.getText().toString();
        if(select.length()==0){
            Toast.makeText(getApplicationContext(),"你还未选择城市",Toast.LENGTH_LONG).show();
            return ;
        }
        TextView text=(TextView)findViewById(R.id.select);
        text.setText(select);
    }
}
