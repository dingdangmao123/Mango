package com.gapcoder.mango;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gapcoder.mango.Utils.ConfigTool;
import com.zhy.changeskin.SkinManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class search extends Base {


    private HashSet<String> mytag=new HashSet<String>();

    @BindView(R.id.tag)
    TagContainerLayout mTag;

    @BindView(R.id.location_city)
    TextView location_city;


    @BindView(R.id.city)
    EditText t;

    private String select="";


    @OnClick(R.id.back)
    void back(){
        Intent i = new Intent();
        i.putExtra("city", select);
        setResult(RESULT_OK, i);
        finish();
    }

    @OnClick(R.id.sure)
    void setLocation_city(){
        String lc=t.getText().toString();
        if(lc.length()==0)
            lc=location_city.getText().toString();
        else
            location_city.setText(lc);
        if(lc.length()==0)
        {
            Toast.makeText(getApplicationContext(), "你还未选择城市", Toast.LENGTH_LONG).show();
            return ;
        }
        select=lc;
        try {
            save();
            Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.i("tag",e.toString());
        }
    }
    void save(){
        SharedPreferences.Editor editor = getSharedPreferences("city", MODE_PRIVATE).edit();
        editor.putString("name", select);
        editor.apply();
    }
    void init(){
        setContentView(R.layout.search);
        ButterKnife.bind(this);
        List<String> tag=Arrays.asList("北京","上海","广州","深圳","南京","杭州","苏州","厦门");
        mTag.setTags(tag);
        mTag.setTagBackgroundColor(Color.TRANSPARENT);
        mTag.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                location_city.setText(text);
            }

            @Override
            public void onTagLongClick(final int position, String text) {
            }

            @Override
            public void onTagCrossClick(int position) {
            }
        });
        SkinManager.getInstance().changeSkin(ConfigTool.getThemeColor(this));
        getLocation();
    }
    private void getLocation(){
        select=getCity();
        location_city.setText(select);
    }
    private String getCity() {
        SharedPreferences p = getSharedPreferences("city", MODE_PRIVATE);
        String c = p.getString("name", "");
        return c;
    }
}
