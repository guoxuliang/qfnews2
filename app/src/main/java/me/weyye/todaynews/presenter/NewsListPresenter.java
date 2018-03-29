package me.weyye.todaynews.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import me.weyye.todaynews.base.ApiService;
import me.weyye.todaynews.base.AppClient;
import me.weyye.todaynews.base.BasePresenter;
import me.weyye.todaynews.base.SubscriberCallBack;
import me.weyye.todaynews.bean.RouteBean;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.model.ZdId;
import me.weyye.todaynews.utils.ToastUtils;
import me.weyye.todaynews.view.INewsListView;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class NewsListPresenter extends BasePresenter<INewsListView> {
    public NewsListPresenter(INewsListView mvpView) {
        super(mvpView);
    }

//public void getOwnerId(String owner) {
//        addSubscription(AppClient.getApiService().getTreeListByOwner(owner), new SubscriberCallBack<List<ZdId>>() {
//            @Override
//            protected void onSuccess(List<ZdId> response) {
//                Logger.i("response.toString()", response.toString());
////                mvpView.onGetNewsListSuccess(response);//显示到界面上
//                ToastUtils.showToast("GXL==response"+response);
//            }
//            @Override
//            protected void onError() {
//                super.onError();
////                mvpView.onError();
//                ToastUtils.showToast("GXL==onError------getOwnerId");
//            }
//        });
//    }

//    public void getNewsList(List<News> info) {
//                    addSubscription(AppClient.getApiService().getlistByTreeid(info), new SubscriberCallBack<List<News>>() {
//            @Override
//            protected void onSuccess(List<News> response) {
//                            Logger.i("response.toString()", response.toString());
//                            mvpView.onGetNewsListSuccess(response);
//                            ToastUtils.showToast("GXL==response"+response);
//                        }
//            @Override
//            protected void onError() {
//                super.onError();
////                mvpView.onError();
//                ToastUtils.showToast("GXL==onError getNewsList");
//            }
//        });
//    }
    Retrofit retrofit;
    public void post(String mTitle) {
    RouteBean info=new RouteBean("1394186967","1001","1","10"); /*** 利用Gson 将对象转json字符串*/
    Gson gson=new Gson();
    String obj=gson.toJson(info);
    retrofit=new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/UserInfoService/").build();
    RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
    final ApiService login = retrofit.create(ApiService.class);
    retrofit2.Call<ResponseBody> data = login.getlistByTreeid(body);
    data.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                String str=response.body().string();
                Log.i("==homeNewsBean","==homeNewsBean"+str);
                mvpView.onGetNewsListSuccess(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
            Log.d("", "onResponse: --err--"+t.toString());
        } });
}
}
