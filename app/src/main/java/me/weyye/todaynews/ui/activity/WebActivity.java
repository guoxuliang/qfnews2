package me.weyye.todaynews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.weyye.todaynews.R;
import me.weyye.todaynews.utils.CustomProgressDialog;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class WebActivity extends AppCompatActivity {
    // 声明控件对象
    private WebView web;
    private String mArgument;
    private String url="";
    public static final String ARGUMENT = "argument";
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private TextView textView2;
    private Handler mHandler = new Handler();

    private CustomProgressDialog customProgressDialog;

    private ImageView iv_img1,iv_img2;
    private TextView tv_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);//进度指示器功能
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);//不确定的进度
        setContentView(R.layout.activity_web);
        initView();
        setProgressBarIndeterminateVisibility(true);
    }

    private void initView() {
        web= (WebView) findViewById(R.id.web);
        textView2= (TextView) findViewById(R.id.textView2);
        tv_textview= (TextView) findViewById(R.id.textView2);
        tv_textview.setText("新闻详情");
        iv_img1= (ImageView) findViewById(R.id.imageview_back);
        iv_img1.setVisibility(View.VISIBLE);
        iv_img2= (ImageView) findViewById(R.id.imageView);
        iv_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebActivity.this.finish();
            }
        });

        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
        customProgressDialog = CustomProgressDialog.createDialog(this, false);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url2 = intent.getStringExtra("url");
        String url_collect=intent.getStringExtra("url_collect");
        if(title!=null&&!title.equals("")){
            textView2.setText(title);
        }
        if(url2!=null&&!url2.equals("")){
            customProgressDialog.show();
            web.loadUrl(url2);
            customProgressDialog.dismiss();
        }
        if(url_collect!=null&&!url_collect.equals("")){
            customProgressDialog.show();
            web.loadUrl(url_collect);
            customProgressDialog.dismiss();
        }
//        web.setWebViewClient(new WebViewClient()
//        {
//            @Override
//            public void onPageFinished(WebView view, String url2)
//            {
//                customProgressDialog.show();
//                //开始
//                super.onPageFinished(view, url2);
//            }
//            @Override
//            public void onPageStarted(WebView view, String url2, Bitmap favicon)
//            {
//                //结束
//                super.onPageStarted(view, url2, favicon);
//                customProgressDialog.dismiss();
//            }
//        });
        //支持App内部javascript交互
        web.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        web.getSettings().setSupportZoom(true);

        //扩大比例的缩放
        web.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        web.getSettings().setBuiltInZoomControls(true);

        //声明WebSettings子类
        WebSettings webSettings = web.getSettings();
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);
//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setDomStorageEnabled(true);//加载出来一片空白


        //优先使用缓存:
        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        //不使用缓存:
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);


        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
//        web.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
            web.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
