package com.gapcoder.mango.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gapcoder.mango.Model.lifestyle;
import com.gapcoder.mango.Model.unit;
import com.gapcoder.mango.R;
import com.gapcoder.mango.Utils.MyColor;
import com.gapcoder.mango.Utils.UtilColor;
import com.gapcoder.mango.Utils.UtilColor;

import java.util.HashMap;
import java.util.List;

/**
 * Created by suxiaohui on 2018/1/31.
 */

public class LifeStyleAdapter extends RecyclerView.Adapter<LifeStyleAdapter.SnapViewHolder> {

    private Context mContext;

    private List<lifestyle.HeWeather6Bean.LifestyleBean> data;

    public LifeStyleAdapter(List<lifestyle.HeWeather6Bean.LifestyleBean> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lifestyle, parent, false);
        return new SnapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SnapViewHolder holder, int position) {
        lifestyle.HeWeather6Bean.LifestyleBean b = data.get(position);
        holder.left.setText(type.map.get(b.getType()));
        holder.right.setText(b.getBrf());
        Log.i("tag", b.getTxt());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        ViewGroup up;
        TextView left;
        TextView right;

        public SnapViewHolder(View itemView) {
            super(itemView);
            up = itemView.findViewById(R.id.up);
            up.setBackgroundResource(MyColor.get());
            left = (TextView) itemView.findViewById(R.id.left);
            right = (TextView) itemView.findViewById(R.id.right);
        }
    }

    static class type {
        public static HashMap<String, String> map = new HashMap<>();

        static {
            map.put("comf", "舒适度");
            map.put("cw", "洗车指数");
            map.put("drsg", "穿衣指数");
            map.put("flu", "感冒指数");
            map.put("sport", "运动指数");
            map.put("trav", " 旅游指数");
            map.put("uv", "紫外线指数");
            map.put("air", "空气污染扩散条件指数");
        }
    }
}

