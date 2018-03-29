package me.weyye.todaynews.presenter;

import android.util.Log;

import me.weyye.todaynews.base.AppClient;
import me.weyye.todaynews.base.BasePresenter;
import me.weyye.todaynews.base.SubscriberCallBack;
import me.weyye.todaynews.model.CommentList;
import me.weyye.todaynews.model.NewsDetail;
import me.weyye.todaynews.view.IBaseDetailView;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class BaseDetailPresenter extends BasePresenter<IBaseDetailView> {
    public BaseDetailPresenter(IBaseDetailView mvpView) {
        super(mvpView);
    }

//    public void getComment(String group_id, String item_id, int pageNow) {
//        int offset = (pageNow - 1) * 10;
//        //获取新闻评论数
//        addSubscription(AppClient.getApiService().getComment(group_id, item_id, offset + "", "10"), new SubscriberCallBack<CommentList>() {
//
//            @Override
//            protected void onSuccess(CommentList response) {
//                mvpView.onGetCommentSuccess(response);
//            }
//
//        });
//        Log.i("===URL","===URL"+AppClient.getApiService().getComment(group_id, item_id, offset + "", "10"));
//    }

    public void getNewsDetail(String url) {
        //获取新闻详情
        addSubscription(AppClient.getApiService().getNewsDetail(url), new SubscriberCallBack<NewsDetail>() {
            @Override
            protected void onSuccess(NewsDetail response) {
                mvpView.onGetNewsDetailSuccess(response);
            }

        });
        Log.i("===URL","===URL"+AppClient.getApiService().getNewsDetail(url));
    }
}
