package me.weyye.todaynews.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.weyye.todaynews.R;
import me.weyye.todaynews.base.ApiService;
import me.weyye.todaynews.base.BaseActivity;
import me.weyye.todaynews.bean.SearchBean;
import me.weyye.todaynews.model.Search;
import me.weyye.todaynews.ui.adapter.SearchAdapter;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/2/1 0001.
 */

public class SearchViewActivity extends BaseActivity {
    private SearchView mSearchView;
    private ListView msearchlistView;
    private SearchAdapter tmAdapter;
    private TextView text_zhiding;

    Retrofit retrofit;

    private ImageView iv_img1,iv_img2;
    private TextView tv_textview;
    private List<Search.InfoEntity.ListEntity> searchlist=new ArrayList<Search.InfoEntity.ListEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);
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
        text_zhiding= (TextView) findViewById(R.id.text_zhiding);
        mSearchView = (SearchView) findViewById(R.id.searchView);
        msearchlistView = (ListView) findViewById(R.id.listView);
        tv_textview= (TextView) findViewById(R.id.textView2);
        tv_textview.setText("搜索");
        iv_img1= (ImageView) findViewById(R.id.imageview_back);
        iv_img1.setVisibility(View.VISIBLE);
        iv_img2= (ImageView) findViewById(R.id.imageView);
        iv_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchViewActivity.this.finish();
            }
        });

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("", "onQueryTextSubmit = " +query);
                if(query!=null&&!query.equals("")){
                    post(query);
                }else {
                    Toast.makeText(SearchViewActivity.this,"无法完成搜索,请正确输入关键字",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        msearchlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String docid = searchlist.get(i).getDocid();
                Bundle bundle=new Bundle();
                bundle.putString("wbnewsid",docid);
                openActivity(NewsDetailActivity2.class,bundle);
            }
        });
    }


    private void post(String keyword) {
        SearchBean searchBean=new SearchBean("1394186967",keyword); /*** 利用Gson 将对象转json字符串*/
        Gson gson=new Gson();
        String obj=gson.toJson(searchBean);
        retrofit=new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/LunSercherService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        final ApiService login = retrofit.create(ApiService.class);
        retrofit2.Call<ResponseBody> data = login.searchNews(body);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                String str;
                try {
                        Log.i("==str2","==str2");
                        str = response.body().string();
                    String str1=str.substring(1,str.length());
                    String str2=str1.substring(0,str1.length()-1);

                    Gson gson = new Gson();
                    Search status = gson.fromJson(str2, Search.class);
                    Log.i("==status","==status"+status);
                    java.lang.reflect.Type listType=new TypeToken<ArrayList<Search.InfoEntity>>(){}.getType();//TypeToken内的泛型就是Json数据中的类型
                    final ArrayList<Search.InfoEntity> searchinfo=gson.fromJson(gson.toJson(status.getInfo()), listType);
                    for(Search.InfoEntity b:searchinfo){
                        searchlist=b.getList();
                        Log.e("","==list"+searchlist);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (searchlist.size()!=0){
                    text_zhiding.setVisibility(View.GONE);
                    tmAdapter= new SearchAdapter(getApplication(),searchlist);
                    msearchlistView.setAdapter(tmAdapter);
                }else {
                    showToast("暂无数据");
                }

            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                Log.d("", "onResponse: --err--"+t.toString());
            } });
    }
}