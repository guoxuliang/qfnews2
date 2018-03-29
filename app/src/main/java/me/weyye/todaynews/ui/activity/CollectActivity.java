package me.weyye.todaynews.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.weyye.todaynews.R;
import me.weyye.todaynews.base.ApiService;
import me.weyye.todaynews.base.BaseActivity;
import me.weyye.todaynews.bean.NewsDetailCollectBean;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.model.NewsDetailUrl;
import me.weyye.todaynews.ui.adapter.CollectAdapter;
import me.weyye.todaynews.utils.DialogBuilder;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class CollectActivity extends BaseActivity {
    private TextView tv_textview;
    private ListView list_collect;
    private CollectAdapter collectAdapter;
    private List<NewsDetailUrl.InfoEntity> list = new ArrayList<NewsDetailUrl.InfoEntity>();
    Retrofit retrofit;
    private ImageView iv_img1;

    private String newsid, title;
    private   String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            newsid = bundle.getString("newsid");
//            title = bundle.getString("title");
//        }
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
        tv_textview = (TextView) findViewById(R.id.textView2);
        tv_textview.setText("我的收藏");
        iv_img1 = (ImageView) findViewById(R.id.imageview_back);
        iv_img1.setVisibility(View.VISIBLE);
        iv_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectActivity.this.finish();
            }
        });
        list_collect = (ListView) findViewById(R.id.list_collect);
        list_collect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //點擊跳轉詳情
//                String  url_collect = list.get(i).getLinkurl();
                String url_collect = "http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48443";
                Bundle bundle = new Bundle();
                bundle.putString("url_collect", url_collect);
                openActivity(WebActivity.class, bundle);
            }
        });
        list_collect.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //長按點擊刪除
                title=list.get(i).getTitle();
                newsid=list.get(i).getDocid();
                pointdialog("温馨提示", "是否删除本条新闻");
                return true;
            }
        });
        post();
    }

    private void post() {
        getDeviceId(this);
        String code = "4";
//        String deviceid = "868857021421064";
        String owner = "1394186967";
            NewsDetailCollectBean collectInfolsit = new NewsDetailCollectBean(code, id, newsid, title, owner);
            /*** 利用Gson 将对象转json字符串*/
            Gson gson = new Gson();
            String obj = gson.toJson(collectInfolsit);
            retrofit = new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/HouseService/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
            final ApiService login = retrofit.create(ApiService.class);
            retrofit2.Call<ResponseBody> data = login.Collection_entrance(body);

            data.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                    String str = null;
                    String sbody = null;
                    try {
                        str = response.body().string();
                        response.body().close();
                        String str1 = str.substring(1, str.length());
                        String str2 = str1.substring(0, str1.length() - 1);
                        Gson gson = new Gson();
                        NewsDetailUrl status = gson.fromJson(str2, NewsDetailUrl.class);
                        java.lang.reflect.Type listType = new TypeToken<ArrayList<NewsDetailUrl.InfoEntity>>() {}.getType();//TypeToken内的泛型就是Json数据中的类型
                        Log.i("**listType", "**listType" + listType);
                        final ArrayList<NewsDetailUrl.InfoEntity> newsinfo = gson.fromJson(gson.toJson(status.getInfo()), listType);
                        Log.i("**newsinfo", "**newsinfo" + newsinfo);
                        for (NewsDetailUrl.InfoEntity b : newsinfo) {
                            b.getAuthor();
                            b.getDocid();
                            b.getTitle();
                            b.getWbtop();
                            b.getImgsrc();
                            b.getTopicId();
                            b.getShowType();
                            b.getPtime();
                            b.getUrl();
                            b.getTopicName();
                            list.add(b);
                        }
                        if (list != null && !list.equals("")) {
                            collectAdapter = new CollectAdapter(CollectActivity.this, list);
                            list_collect.setAdapter(collectAdapter);
                            collectAdapter.notifyDataSetChanged();
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

    private void postdel() {
        getDeviceId(this);
        String code = "2";
//        String deviceid = "868857021421064";
        String owner = "1394186967";
        if (newsid != null && !newsid.equals("")) {
            NewsDetailCollectBean collectInfolsit = new NewsDetailCollectBean(code, id, newsid, title, owner);
            /*** 利用Gson 将对象转json字符串*/
            Gson gson = new Gson();
            String obj = gson.toJson(collectInfolsit);
            retrofit = new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/HouseService/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
            final ApiService login = retrofit.create(ApiService.class);
            retrofit2.Call<ResponseBody> data = login.Collection_entrance(body);

            data.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("gxlcode", response.toString());
                    if (response != null) {
                        if (response.code() == 200) {
//                            pointdialog("删除成功", "是否跳转到收藏夹");
                            showToast("删除成功");
                            list.clear();
                            Log.i("###gogogo","###gogogo"+list);

                        } else if (response.code() == 300) {
                            pointdialog("已经收藏过了", "是否跳转到收藏夹");
                            showToast("已经收藏过了");
                        } else {
//                            pointdialog("收藏失败", "请重试");
                            showToast("删除失败，请重试");
                        }

                    }

                    if (list != null && !list.equals("")) {
                        collectAdapter = new CollectAdapter(CollectActivity.this, list);
                        list_collect.setAdapter(collectAdapter);
                        collectAdapter.notifyDataSetChanged();
                    } else {
                        showToast("暂无数据");
                    }
                    post();
//
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
    public String getDeviceId(Context context) {


        //android.telephony.TelephonyManager
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            //android.provider.Settings;
            id = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }

    private void pointdialog(String title, String message) {
        new DialogBuilder(CollectActivity.this)
                .title(title)
                .message(message)
                .cancelText("取消")
                .sureText("确定")
                .setSureOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        postdel();
                    }
                })
                .setCancelOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("取消");
                    }
                }).build().show();
    }
}


