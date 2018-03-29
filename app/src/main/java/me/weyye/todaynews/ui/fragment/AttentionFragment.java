package me.weyye.todaynews.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseMvpFragment;
import me.weyye.todaynews.presenter.AttentionPresenter;
import me.weyye.todaynews.ui.activity.WebActivity;
import me.weyye.todaynews.view.IAttentionView;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class AttentionFragment extends BaseMvpFragment<AttentionPresenter> implements IAttentionView {

    private LinearLayout btn1,btn2,btn3;
    private TextView textView2;
    @Override
    protected AttentionPresenter createPresenter() {
        return new AttentionPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tab2, null);

    }

    @Override
    protected void bindViews(View view) {
        btn1=get(R.id.btn1);
        btn2=get(R.id.btn2);
        btn3=get(R.id.btn3);
        textView2=get(R.id.textView2);
        textView2.setText("举报");
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 给webview中传递参数并且跳转
                //举报
                String url="http://shaanxi.12388.gov.cn/";
                Intent intent = new Intent(getActivity(),WebActivity.class);
//                String url = getActivity().getIntent().getStringExtra("url");
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 给webview中传递参数并且跳转
                //举报查询
                String url="http://shaanxi.12388.gov.cn/index_search.html";
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "举报查询");
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), AboutActivity.class));
                //TODO 给webview中传递参数并且跳转
                //网上举报须知
                String url="http://shaanxi.12388.gov.cn/html/jbxz.html";
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "网上举报须知");
                startActivity(intent);
            }
        });
    }
}
