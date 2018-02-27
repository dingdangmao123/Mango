package com.gapcoder.mango;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gapcoder.mango.Adapter.LifeStyleAdapter;
import com.gapcoder.mango.Adapter.WAdapter;
import com.gapcoder.mango.Model.Config;
import com.gapcoder.mango.Model.lifestyle;
import com.gapcoder.mango.Model.unit;
import com.gapcoder.mango.Utils.ConfigTool;
import com.gapcoder.mango.Utils.Curl;
import com.gapcoder.mango.Utils.Net;
import com.gapcoder.mango.Utils.Pool;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main extends Base {

    Handler mh = new Handler();
    NetState state;
    Config config;

    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.city)
    TextView name;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.rv)
    RecyclerView recyclerView;


    final int SEARCH_CODE = 1;
    final int MARKER_CODE = 2;

    String city = "";
    unit ins;

    WAdapter adapter;
    LinkedList<unit.HeWeather6Bean.DailyForecastBean> data = new LinkedList<>();

    @BindView(R.id.lifestyle)
    RecyclerView lifestyle;
    lifestyle lifeins;
    LifeStyleAdapter lifestyleAdapter;
    LinkedList<com.gapcoder.mango.Model.lifestyle.HeWeather6Bean.LifestyleBean> lifedata = new LinkedList<>();


    @OnClick(R.id.search)
    void button_search() {
        Intent i = new Intent(Main.this, search.class);
        startActivityForResult(i, SEARCH_CODE);
    }

    @OnClick(R.id.marker)
    void button_marker() {
        Intent i = new Intent(Main.this, marker.class);
        startActivityForResult(i, MARKER_CODE);
    }


    void init() {

        setContentView(R.layout.main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new WAdapter(data, this);
        recyclerView.setAdapter(adapter);

        lifestyle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lifestyleAdapter = new LifeStyleAdapter(lifedata, this);
        lifestyle.setAdapter(lifestyleAdapter);

        refresh.setColorSchemeResources(R.color.green);
        refresh.setRefreshing(true);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
            }
        });

        config = ConfigTool.parse(this);
        city = getCity();

        IntentFilter in = new IntentFilter();
        in.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        state = new NetState();
        registerReceiver(state, in);

        update();
    }

    void netWarning() {
        Toast.makeText(this, "无法连接到网络，请检查", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(state);
    }

    private void update() {

        if (!Net.check(this)) {
            netWarning();
            if (refresh.isRefreshing())
                refresh.setRefreshing(false);
            return;
        }

        if (!config.isAutoLocation() && city.length() == 0) {
            Toast.makeText(this, "未开启自动定位", Toast.LENGTH_LONG).show();
            return;
        }

        if (config.isAutoLocation())
            Pool.run(new Runnable() {
                @Override
                public void run() {
                    get("auto_ip");
                }
            });
        else
            Pool.run(new Runnable() {
                @Override
                public void run() {
                    get();
                }
            });
    }

    private void get() {
        get(city);
    }

    private void get(String cc) {

        String url = "https://free-api.heweather.com/s6/weather/forecast?location=" + cc + "&key=8ceb2dfadaa2449cb04768fa857f09d1";
        String url2 = "https://free-api.heweather.com/s6/weather/lifestyle?location=" + cc + "&key=8ceb2dfadaa2449cb04768fa857f09d1";
        try {
            String s = Curl.getText(url);
            String s2 = Curl.getText(url2);
            Gson gs = new Gson();
            ins = gs.fromJson(s, unit.class);
            final lifestyle ins2 = gs.fromJson(s2, lifestyle.class);
            data.clear();
            lifedata.clear();
            List<unit.HeWeather6Bean.DailyForecastBean> tmp = ins.getHeWeather6().get(0).getDaily_forecast();
            List<com.gapcoder.mango.Model.lifestyle.HeWeather6Bean.LifestyleBean> tmp2 = ins2.getHeWeather6().get(0).getLifestyle();
            int i = 0;
            final unit.HeWeather6Bean.DailyForecastBean b = tmp.get(0);
            for (i = 0; i < tmp.size(); i++)
                data.add(tmp.get(i));

            for (i = 0; i < tmp2.size(); i++)
                lifedata.add(tmp2.get(i));

            mh.post(new Runnable() {
                @Override
                public void run() {

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
                    if (refresh.isRefreshing())
                        refresh.setRefreshing(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("tag", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SEARCH_CODE:
                if (resultCode == RESULT_OK) {
                    if (data.getStringExtra("city").length() > 0)
                        city = data.getStringExtra("city");
                    Pool.run(new Runnable() {
                        @Override
                        public void run() {
                            get();
                        }
                    });
                }
                break;
            case MARKER_CODE:
                if (resultCode == RESULT_OK) {
                    config = ConfigTool.parse(this);
                    update();
                }
                break;
        }
    }

    private String getCity() {
        SharedPreferences p = getSharedPreferences("city", MODE_PRIVATE);
        String c = p.getString("name", "");
        return c;
    }

    class NetState extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (!Net.check(context)) {
                netWarning();
                if (refresh.isRefreshing())
                    refresh.setRefreshing(false);
            }

        }
    }

}
