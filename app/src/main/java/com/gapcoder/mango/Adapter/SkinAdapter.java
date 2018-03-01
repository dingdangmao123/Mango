package com.gapcoder.mango.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.gapcoder.mango.R;
import com.gapcoder.mango.Utils.ConfigTool;
import com.gapcoder.mango.Utils.SkinData;
import com.zhy.changeskin.SkinManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by suxiaohui on 2018/2/28.
 */


public class SkinAdapter extends RecyclerView.Adapter<SkinAdapter.SnapViewHolder> {

    private Context mContext;
    private ViewGroup old;
    private ImageView check;

    public SkinAdapter(Context context) {

        this.mContext = context;
        check = new ImageView(context);
        check.setBackground(context.getResources().getDrawable(R.mipmap.ic_check_circle, null));
        check.setScaleType(ImageView.ScaleType.CENTER);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));
        check.setLayoutParams(lp);
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skinitem, parent, false);
        final SnapViewHolder h = new SnapViewHolder(view);
        h.color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("tag", view.getTag().toString());
                try {
                    SkinManager.getInstance().changeSkin(view.getTag().toString());

                    if (old != null)
                        old.removeView(check);

                    old = h.p;
                    old.addView(check);

                    SharedPreferences.Editor editor = mContext.getSharedPreferences("config", MODE_PRIVATE).edit();
                    editor.putString("themeColor", view.getTag().toString());
                    editor.apply();
                    Toast.makeText(mContext, "换肤成功", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return h;
    }

    @Override
    public void onBindViewHolder(SnapViewHolder holder, int position) {

        holder.color.setBackgroundColor(Color.parseColor(SkinData.data[position]));
        holder.color.setTag(SkinData.name[position]);

        if (ConfigTool.getThemeColor(mContext).equals(holder.color.getTag().toString())) {
            old = holder.p;
            old.addView(check);
        }

    }

    @Override
    public int getItemCount() {
        return SkinData.data.length;
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        View color;
        ViewGroup p;

        public SnapViewHolder(View itemView) {
            super(itemView);
            color = itemView.findViewById(R.id.skincolor);
            p = itemView.findViewById(R.id.parent);
        }
    }
}

