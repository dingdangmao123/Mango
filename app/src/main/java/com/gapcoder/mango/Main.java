package com.gapcoder.mango;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.mango.Adapter.LifeStyleAdapter;
import com.gapcoder.mango.Adapter.WAdapter;
import com.gapcoder.mango.Model.lifestyle;
import com.gapcoder.mango.Model.unit;
import com.gapcoder.mango.Utils.Blur;
import com.gapcoder.mango.Utils.Curl;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class Main extends AppCompatActivity {

    Handler mh = new Handler() {
    };
    Holder holder;
    ImageView bg;
    TextView name;
    SwipeRefreshLayout refresh;

    final int SEARCH_CODE = 1;
    final int PLACE_CODE = 2;

    String city ="";

    unit ins;
    RecyclerView recyclerView;
    WAdapter adapter;
    LinkedList<unit.HeWeather6Bean.DailyForecastBean> data = new LinkedList<>();

    lifestyle lifeins;
    LifeStyleAdapter lifestyleAdapter;
    RecyclerView lifestyle;
    LinkedList<com.gapcoder.mango.Model.lifestyle.HeWeather6Bean.LifestyleBean> lifedata = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        // setImage();
        city=getCity();

        update();


    }

    private void init() {

        setContentView(R.layout.main);
        if (Build.VERSION.SDK_INT >= 21) {
            View d = getWindow().getDecorView();
            d.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

         holder = new Holder();
         holder.init(this);

        bg = (ImageView) findViewById(R.id.bg);
        name = (TextView) findViewById(R.id.city);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new WAdapter(data, this);
        recyclerView.setAdapter(adapter);

        lifestyle = (RecyclerView) findViewById(R.id.lifestyle);
        lifestyle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lifestyleAdapter = new LifeStyleAdapter(lifedata, this);
        lifestyle.setAdapter(lifestyleAdapter);

        final Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, search.class);
                startActivityForResult(i, SEARCH_CODE);
            }
        });

        final Button marker= (Button) findViewById(R.id.marker);
        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, marker.class);
                startActivityForResult(i, SEARCH_CODE);
            }
        });

        final Button place= (Button) findViewById(R.id.place);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, place.class);
                startActivityForResult(i, PLACE_CODE);
            }
        });

        refresh=(SwipeRefreshLayout)findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.green);
        refresh.setRefreshing(true);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(){
                update();
            }
        });
    }

    private void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                get();
            }
        }).start();
    }

    private void setImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap ins = Blur.blurBitmap(getApplicationContext(), BitmapFactory.decodeResource(getResources(), R.drawable.a));
                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        bg.setImageBitmap(ins);
                    }
                });
            }
        }).start();
    }

    private void get() {

        String url = "https://free-api.heweather.com/s6/weather/forecast?location=" + city + "&key=8ceb2dfadaa2449cb04768fa857f09d1";
        String url2 = "https://free-api.heweather.com/s6/weather/lifestyle?location=" + city + "&key=8ceb2dfadaa2449cb04768fa857f09d1";
        try {
            String s = Curl.getText(url);//response.body().string();
            String s2 = Curl.getText(url2);//.body().string();
            Gson gs = new Gson();
            ins = gs.fromJson(s, unit.class);
            final lifestyle ins2 = gs.fromJson(s2, lifestyle.class);
            data.clear();
            lifedata.clear();
            List<unit.HeWeather6Bean.DailyForecastBean> tmp = ins.getHeWeather6().get(0).getDaily_forecast();
            List<com.gapcoder.mango.Model.lifestyle.HeWeather6Bean.LifestyleBean> tmp2 = ins2.getHeWeather6().get(0).getLifestyle();
            int i = 0;
            final unit.HeWeather6Bean.DailyForecastBean b = tmp.get(0);
            for (i = 1; i < tmp.size(); i++)
                data.add(tmp.get(i));

            for (i = 0; i < tmp2.size(); i++)
                lifedata.add(tmp2.get(i));

            mh.post(new Runnable() {
                @Override
                public void run() {
                    holder.t1.setText(b.getCond_txt_d());
                    holder.t2.setText(b.getTmp_min() + "℃ ~ " + b.getTmp_max() + "℃");
                    holder.t3.setText(ins.getHeWeather6().get(0).getUpdate().getLoc());
                    holder.t4.setText(b.getWind_dir() + " " + b.getWind_sc());
                    holder.t5.setText("能见度: " + b.getVis() + "公里");
                    adapter.notifyDataSetChanged();
                    lifestyleAdapter.notifyDataSetChanged();
                    if (ins != null) {
                        unit.HeWeather6Bean.BasicBean basic = ins.getHeWeather6().get(0).getBasic();
                        if (basic.getLocation().equals(basic.getAdmin_area()))
                            name.setText(basic.getLocation());
                        else if (basic.getLocation().equals(basic.getParent_city()))
                            name.setText(basic.getLocation() + " " + basic.getAdmin_area());
                        else
                            name.setText(basic.getLocation() + " " + basic.getParent_city());
                    }
                    if(refresh.isRefreshing())
                        refresh.setRefreshing(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("tag", e.toString());
        }
    }

    private static class Holder {

        ViewGroup group;
        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;
        TextView t5;
        TextView t6;

        public void init(Activity ac) {
            group = ac.findViewById(R.id.group);
            t1 = (TextView) ac.findViewById(R.id.t1);
            t2 = (TextView) ac.findViewById(R.id.t2);
            t3 = (TextView) ac.findViewById(R.id.t3);
            t4 = (TextView) ac.findViewById(R.id.t4);
            t5 = (TextView) ac.findViewById(R.id.t5);
            // t6 = (TextView) ac.findViewById(R.id.t6);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SEARCH_CODE:
                if (resultCode == RESULT_OK) {
                    if(data.getStringExtra("city").length()>0)
                        city=data.getStringExtra("city");
                    update();
                }
                break;
            case PLACE_CODE:
                if (resultCode == RESULT_OK) {
                    if(data.getStringExtra("city").length()>0)
                        city=data.getStringExtra("city");
                    update();
                }
        }
    }

    private String getCity() {
        SharedPreferences p = getSharedPreferences("city", MODE_PRIVATE);
        String c = p.getString("name", "北京");
        return c;
    }
}
