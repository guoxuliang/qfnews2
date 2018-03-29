package me.weyye.todaynews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private TextView aboutme,collect;

    @Override
    protected void loadViewLayout() {
    }
    @Override
    protected void bindViews() {
    }
    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }
    @Override
    protected void setListener() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        aboutme= (TextView) findViewById(R.id.aboutme);
        collect= (TextView) findViewById(R.id.collect);
        aboutme.setOnClickListener(this);
        collect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aboutme:
                intent2Activity(AboutActivity.class);
                break;
            case R.id.collect:
                intent2Activity(CollectActivity.class);
                break;
        }
    }
}
