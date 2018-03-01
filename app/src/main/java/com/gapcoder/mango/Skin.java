package com.gapcoder.mango;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.gapcoder.mango.Adapter.SkinAdapter;
import com.gapcoder.mango.Utils.ConfigTool;
import com.zhy.changeskin.SkinManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Skin extends Base {


    @BindView(R.id.skin)
    RecyclerView skin;


    void init() {
        setContentView(R.layout.skin);
        ButterKnife.bind(this);

        skin.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SkinAdapter adapter = new SkinAdapter(this);
        skin.setAdapter(adapter);
        SkinManager.getInstance().changeSkin(ConfigTool.getThemeColor(this));
    }

    @OnClick(R.id.back)
    void back() {
        finish();
    }

}
