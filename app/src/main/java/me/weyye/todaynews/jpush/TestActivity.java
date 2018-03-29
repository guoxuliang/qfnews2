package me.weyye.todaynews.jpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;
import me.weyye.todaynews.R;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        TextView tv = new TextView(this);
//        tv.setText("用户自定义打开的Activity");
        TextView tvtest=(TextView)findViewById(R.id.test);
        TextView  tv = (TextView) findViewById(R.id.textView2);
        tv.setText("我的通知");
        Intent intent = getIntent();
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if(bundle!=null){
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            tvtest.setText("Title : " + title + "  " + "Content : " + content);
        }
//        addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

}
