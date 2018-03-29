package me.weyye.todaynews.ui.fragment;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.weyye.todaynews.R;

import me.weyye.todaynews.base.BaseFragment;
import me.weyye.todaynews.jpush.TestActivity;
import me.weyye.todaynews.model.Notice;
import me.weyye.todaynews.theme.colorUi.util.SharedPreferencesMgr;
import me.weyye.todaynews.ui.activity.AboutActivity;
import me.weyye.todaynews.ui.activity.CollectActivity;
import me.weyye.todaynews.ui.activity.SettingActivity;
import me.weyye.todaynews.ui.activity.WebActivity;
//import me.weyye.todaynews.ui.view.HeaderZoomLayout;
import me.weyye.todaynews.utils.ConstanceValue;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class MeFragment extends BaseFragment {

    private LinearLayout btn1,btn2,btn3,btn4;
    private TextView textView2;

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    protected void bindViews(View view) {
//        zommLayout=get(R.id.zommLayout);
        btn1=get(R.id.btn1);
        btn2=get(R.id.btn2);
        btn3=get(R.id.btn3);
        btn4=get(R.id.btn4);
        textView2=get(R.id.textView2);
        textView2.setText("大厅");
    }

    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //信息公开
                String url="http://qinfeng.gov.cn/xxgk.htm";
                Intent intent = new Intent(getActivity(),WebActivity.class);
//                String url = getActivity().getIntent().getStringExtra("url");
                intent.putExtra("url", url);
                intent.putExtra("title", "信息公开");
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //党纪法规
//              String url="http://61.185.20.64:8080/JZFP_M_TEST/index.html";
                String url = "http://qinfeng.gov.cn/zxxx1/dzfgk1.htm";
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "党纪法规");
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关于我们
//                startActivity(new Intent(getActivity(), AboutActivity.class));
                Intent intent = new Intent(getActivity(),AboutActivity.class);
                startActivity(intent);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关于我们
                Intent intent = new Intent(getActivity(),CollectActivity.class);
                startActivity(intent);

            }
        });

    }
}
