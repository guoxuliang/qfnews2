package me.weyye.todaynews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.ApiService;
import me.weyye.todaynews.base.BaseMvpFragment;
import me.weyye.todaynews.bean.RouteBean;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.presenter.NewsListPresenter;
import me.weyye.todaynews.ui.activity.NewsDetailActivity2;
import me.weyye.todaynews.ui.adapter.NewsAdapter;
import me.weyye.todaynews.ui.adapter.TwoNewsAdapter;
import me.weyye.todaynews.ui.view.LoadingFlashView;
import me.weyye.todaynews.utils.ConstanceValue;
import me.weyye.todaynews.utils.LoadMoreListView;
import me.weyye.todaynews.view.INewsListView;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class NewsListFragment extends BaseMvpFragment<NewsListPresenter> implements INewsListView {
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;//新闻咧表显示的区域
    @BindView(R.id.loadingView)
    LoadingFlashView loadingView;
    private String mTitleCode = "";
    protected List<News> mDatas = new ArrayList<>();
    protected BaseQuickAdapter mAdapter;
    Retrofit retrofit;
    private TwoNewsAdapter tmAdapter;
    private LoadMoreListView listview;

    private int count=10;
    private String startstr;

    private News.InfoEntity.ListEntity news=new News.InfoEntity.ListEntity();

    private TextView feed_top_search_hint;

    private List<News.InfoEntity.ListEntity> list=new ArrayList<News.InfoEntity.ListEntity>();

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.layout_recyclerview, null);
        ButterKnife.bind(this, v);
        listview=v.findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String docid = list.get(i).getDocid();
                String ptime = list.get(i).getPtime();
                String title = list.get(i).getTitle();
                String imgsrc = list.get(i).getImgsrc();
                String url = list.get(i).getUrl();
                Bundle bundle=new Bundle();
                bundle.putString("wbnewsid",docid);
                bundle.putString("ptime",ptime);
                bundle.putString("title",title);
                bundle.putString("imgsrc",imgsrc);
                bundle.putString("url",url);
                Intent intent=new Intent();
                intent.setClass(getActivity(),NewsDetailActivity2.class);
                openActivity(NewsDetailActivity2.class,bundle);
            }
        });

        listview.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                loadMore();
            }
        });
        return v;
    }


    private void loadMore() {
        new Thread(){
            @Override
            public void run() {
                super.run();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                 count=count+5;
                startstr =String.valueOf(count);
                post("1394186967",mTitleCode,"0",startstr);
//                tmAdapter.notifyDataSetChanged();
//                        listview.setLoadCompleted();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tmAdapter.notifyDataSetChanged();
//                        listview.setLoadCompleted();
//                    }
//                });
            }
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();
//        post();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        post();
    }

    public static NewsListFragment newInstance(String code) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
    }

    @Override
    protected void processLogic() {

        initCommonRecyclerView(createAdapter(), null);
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
        showLoadingDialog("正在加载");
         startstr =String.valueOf(count);
        post("1394186967",mTitleCode,"0",startstr);
    }

    protected BaseQuickAdapter createAdapter() {
        if (list!=null){
             mAdapter = new NewsAdapter(list);
        }
        return mAdapter;
    }


//    @Override
//    protected void lazyLoad() {
//        super.lazyLoad();
//        if (TextUtils.isEmpty(mTitleCode))
//            mTitleCode = getArguments().getString(ConstanceValue.DATA);
//        Log.i("","==mTitleCode"+mTitleCode);
//    }


    private void post(String owner, String wbtreeid, String start, final String count) {
//        RouteBean info=new RouteBean("1394186967",mTitleCode,"0","30"); /*** 利用Gson 将对象转json字符串*/
        RouteBean info=new RouteBean(owner,wbtreeid,start,count);
        Gson gson=new Gson();
        String obj=gson.toJson(info);
        retrofit=new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/UserInfoService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        final ApiService login = retrofit.create(ApiService.class);
        retrofit2.Call<ResponseBody> data = login.getlistByTreeid(body);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissLoadingDialog();
                String str=null;
                try {

                    str = response.body().string();
                    response.body().close();
                    String str1=str.substring(1,str.length());
                    String str2=str1.substring(0,str1.length()-1);
                    Gson gson = new Gson();
                    News status = gson.fromJson(str2, News.class);
                    java.lang.reflect.Type listType=new TypeToken<ArrayList<News.InfoEntity>>(){}.getType();//TypeToken内的泛型就是Json数据中的类型
                    final ArrayList<News.InfoEntity> newsinfo=gson.fromJson(gson.toJson(status.getInfo()), listType);

                    for(News.InfoEntity b:newsinfo){
                        list=b.getList();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
    if (list!=null&&!list.equals("")){
        tmAdapter=new TwoNewsAdapter(getActivity(),list);
        listview.setAdapter(tmAdapter);
        tmAdapter.notifyDataSetChanged();
        listview.setLoadCompleted();

    }else {
        showToast("暂无数据");
    }

            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
            } });
    }

    /**
     * 新闻列表的点击事件
     */
    @Override
    protected void setListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
            }
        });
    }


    @Override
    public void onGetNewsListSuccess(List<News> response) {
    }

    @Override
    public void onError() {
        srl.setRefreshing(false);
    }

    @Override
    public void onGetNewsListSuccess(Response<ResponseBody> response) {
    }
}