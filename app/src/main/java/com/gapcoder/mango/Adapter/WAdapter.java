package com.gapcoder.mango.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gapcoder.mango.Model.unit;
import com.gapcoder.mango.R;
import com.gapcoder.mango.Utils.MyColor;
import com.gapcoder.mango.Utils.UtilColor;
import com.gapcoder.mango.Utils.UtilColor;

import java.util.List;

/**
 * Created by suxiaohui on 2018/1/31.
 */

public class WAdapter extends RecyclerView.Adapter<WAdapter.SnapViewHolder> {

    private Context mContext;

    private List<unit.HeWeather6Bean.DailyForecastBean> data;

    public WAdapter(List<unit.HeWeather6Bean.DailyForecastBean> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather, parent, false);
        return new SnapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SnapViewHolder holder, int position) {
        unit.HeWeather6Bean.DailyForecastBean b = data.get(position);
        holder.left1.setText(b.getCond_txt_d());
        holder.left2.setText(b.getTmp_min() + "℃ ~ " + b.getTmp_max() + "℃");
        holder.right1.setText(b.getDate());
        holder.right2.setText(b.getWind_dir() + " " + b.getWind_sc());
        holder.right3.setText("能见度: " + b.getVis() + "公里");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        ViewGroup left;
        TextView left1;
        TextView left2;
        TextView right1;
        TextView right2;
        TextView right3;

        public SnapViewHolder(View itemView) {
            super(itemView);
            left = itemView.findViewById(R.id.left);
            left.setBackgroundResource(MyColor.get());
            left1 = (TextView) itemView.findViewById(R.id.left1);
            left2 = (TextView) itemView.findViewById(R.id.left2);
            right1 = (TextView) itemView.findViewById(R.id.right1);
            right2 = (TextView) itemView.findViewById(R.id.right2);
            right3 = (TextView) itemView.findViewById(R.id.right3);
        }
    }
}

