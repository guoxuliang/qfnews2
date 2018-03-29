package me.weyye.todaynews.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import me.weyye.todaynews.R;
import me.weyye.todaynews.base.ApiService;
import me.weyye.todaynews.base.BaseActivity;
import me.weyye.todaynews.bean.NewsDetailBean;
import me.weyye.todaynews.bean.NewsDetailCollectBean;
import me.weyye.todaynews.model.NewsDetail2;
import me.weyye.todaynews.ui.activity.umeng.Defaultcontent;
import me.weyye.todaynews.utils.DialogBuilder;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsDetailActivity2 extends BaseActivity {
    private WebView webView;
    Retrofit retrofit;
    private String str;
    private String wbnewsid;
    private String title, ptime,imgsrc;
    private TextView tv_title2, date_detail;
    private ImageView img;
    private ImageView iv_img1, iv_img2, imageView;
    private TextView tv_textview;
    private PopupWindow mPopupWindow = null;
    View contentView;
    private Button menu_item1, menu_item2;
    private ProgressDialog progressDialog;

    private String UmengShareUrl="";
    private DisplayImageOptions options;


    /**
     * 友盟分享
     * @param savedInstanceState
     */
    private ShareAction mShareAction;
    private UMShareListener mShareListener;
    Bitmap map = null;
    URL url = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.umeng_blue));
        }
        setContentView(R.layout.activity_news_detail2);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            wbnewsid = bundle.getString("wbnewsid");
            ptime = bundle.getString("ptime");
            title = bundle.getString("title");
            UmengShareUrl = bundle.getString("url");
            imgsrc = bundle.getString("imgsrc");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        url = new URL(imgsrc);
                        URLConnection conn = url.openConnection();
                        conn.connect();
                        InputStream in;
                        in = conn.getInputStream();
                        map = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }
        initView();

    }

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

    private void initView() {
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
                .cacheOnDisc(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        webView = (WebView) findViewById(R.id.web);
        tv_textview = (TextView) findViewById(R.id.textView2);
        tv_textview.setText("新闻详情");

        iv_img1 = (ImageView) findViewById(R.id.imageview_back);
        iv_img1.setVisibility(View.VISIBLE);
        iv_img2 = (ImageView) findViewById(R.id.imageView);
        iv_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailActivity2.this.finish();
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });


        webView.setScrollContainer(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        tv_title2 = (TextView) findViewById(R.id.tv_title2);
        date_detail = (TextView) findViewById(R.id.date_detail);
        img = (ImageView) findViewById(R.id.img);

        date_detail.setText(ptime);
        showLoadingDialog("正在加载");
        post();
        umengfx();
    }



    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        str = "<html>" + head + "<body>" + bodyHTML + "</body></html>";
        webView.loadData(str, "text/html; charset=UTF-8", null);
        return str;
    }

    private void post() {
        if (wbnewsid != null && !wbnewsid.equals("")) {
            NewsDetailBean info = new NewsDetailBean("1394186967", wbnewsid);
            /*** 利用Gson 将对象转json字符串*/
            Gson gson = new Gson();
            String obj = gson.toJson(info);
            retrofit = new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/UserInfoService/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
            final ApiService login = retrofit.create(ApiService.class);
            retrofit2.Call<ResponseBody> data = login.getWbnewsById(body);

            data.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                    String str = null;
                    String sbody = null;
                    try {
                        dismissLoadingDialog();
                        str = response.body().string();
                        response.body().close();
                        String str1 = str.substring(1, str.length());
                        String str2 = str1.substring(0, str1.length() - 1);
                        Gson gson = new Gson();
                        NewsDetail2 newsdetail = gson.fromJson(str2, NewsDetail2.class);
                        java.lang.reflect.Type detailType = new TypeToken<ArrayList<NewsDetail2.InfoEntity>>() {
                        }.getType();//TypeToken内的泛型就是Json数据中的类型
                        final ArrayList<NewsDetail2.InfoEntity> newsdetailinfo = gson.fromJson(gson.toJson(newsdetail.getInfo()), detailType);
                        for (NewsDetail2.InfoEntity b : newsdetailinfo) {
                            sbody = b.getBody();
//                            String image = b.getImg();
                            tv_title2.setText(b.getTitle());
                        }
                        if (sbody != null) {
                            getHtmlData(sbody);
                        } else {
                            showToast("暂无数据");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                    Log.d("", "onResponse: --err--" + t.toString());
                }
            });
        }
    }

    /**
     * 获取设备唯一标识
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {

        String id;
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            id = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }

    private void postCollect() {
        getDeviceId(this);
        String code = "1";
        String deviceid = "868857021421064";
        String newsid = wbnewsid;
        String owner = "1394186967";
        if (wbnewsid != null && !wbnewsid.equals("")) {
            NewsDetailCollectBean collectInfo = new NewsDetailCollectBean(code, deviceid, newsid, title, owner);
            /*** 利用Gson 将对象转json字符串*/
            Gson gson = new Gson();
            String obj = gson.toJson(collectInfo);
            retrofit = new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/HouseService/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
            final ApiService login = retrofit.create(ApiService.class);
            retrofit2.Call<ResponseBody> data = login.Collection_entrance(body);

            data.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("@@gxlcode", response.toString());
                    if (response != null) {
                        if (response.code() == 200) {
//                            pointdialog("收藏成功", "是否跳转到收藏夹");
                            showToast("收藏成功");
                        } else if (response.code() == 300) {
//                            pointdialog("已经收藏过了", "是否跳转到收藏夹");
                            showToast("已经收藏过了");
                        } else {
//                            pointdialog("收藏失败", "请重试");
                            showToast("收藏失败，请重试");
                        }

                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                    Log.d("", "onResponse: --err--" + t.toString());
                    showToast(t.toString());
                }
            });
        }

    }


    private void showPopupWindow(View view) {
        contentView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_content_layout, null);

        menu_item1 = (Button) contentView.findViewById(R.id.menu_item1);
        menu_item2 = (Button) contentView.findViewById(R.id.menu_item2);
        menu_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("分享");
                mShareAction.open();
            }
        });
        menu_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postCollect();
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAsDropDown(imageView, 0, 0);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 设置好参数之后再show
        mPopupWindow.showAsDropDown(view);
    }


    /**
     * popwindow
     */
    private void pointdialog(String title, String message) {
        new DialogBuilder(NewsDetailActivity2.this)
                .title(title)
                .message(message)
                .cancelText("取消")
                .sureText("确定")
                .setSureOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("跳轉");
                        intent2Activity(CollectActivity.class);
                    }
                })
                .setCancelOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                       showToast("取消");
                    }
                }).build().show();
    }


    /**
     * 分享
     */
    private void umengfx() {
        mShareListener = new CustomShareListener(this);
        mShareAction = new ShareAction(NewsDetailActivity2.this).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                            Toast.makeText(NewsDetailActivity2.this, "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            Toast.makeText(NewsDetailActivity2.this, "复制链接按钮", Toast.LENGTH_LONG).show();

                        }else if(share_media == SHARE_MEDIA.SMS) {
                            new ShareAction(NewsDetailActivity2.this).withText("来自分享面板标题")
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }else
                        {
//                            UMWeb web = new UMWeb(Defaultcontent.url);
                            UMWeb web = new UMWeb(UmengShareUrl);
                            web.setTitle(title);
                            web.setDescription(ptime);
                            web.setThumb(new UMImage(NewsDetailActivity2.this, map));
                            new ShareAction(NewsDetailActivity2.this).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });
    }

    private class CustomShareListener implements UMShareListener {
        private WeakReference<NewsDetailActivity2> mActivity;
        public CustomShareListener(NewsDetailActivity2 newsDetailActivity2) {
            mActivity = new WeakReference(newsDetailActivity2);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            if (share_media.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), share_media + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (share_media != SHARE_MEDIA.MORE && share_media != SHARE_MEDIA.SMS
                        && share_media != SHARE_MEDIA.EMAIL
                        && share_media != SHARE_MEDIA.FLICKR
                        && share_media != SHARE_MEDIA.FOURSQUARE
                        && share_media != SHARE_MEDIA.TUMBLR
                        && share_media != SHARE_MEDIA.POCKET
                        && share_media != SHARE_MEDIA.PINTEREST
                        && share_media != SHARE_MEDIA.INSTAGRAM
                        && share_media != SHARE_MEDIA.GOOGLEPLUS
                        && share_media != SHARE_MEDIA.YNOTE
                        && share_media != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {


    }




//    public static Bitmap getBitMBitmap(String urlpath) {
//        Bitmap map = null;
//        try {
//            URL url = new URL(urlpath);
//            URLConnection conn = url.openConnection();
//            conn.connect();
//            InputStream in;
//            in = conn.getInputStream();
//            map = BitmapFactory.decodeStream(in);
//            // TODO Auto-generated catch block
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }

}