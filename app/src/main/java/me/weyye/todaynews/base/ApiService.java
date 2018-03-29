package me.weyye.todaynews.base;

import java.util.List;

import me.weyye.todaynews.model.News;
import me.weyye.todaynews.model.NewsDetail;
import me.weyye.todaynews.model.VideoModel;
import me.weyye.todaynews.model.ZdId;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 */
public interface ApiService {
    //baseUrl
    String HOST = "http://www.toutiao.com/";//主机
    String API_SERVER_URL = HOST + "api/";//api服务器URL
    String URL_ARTICLE_FEED = "/api/article/recent/";//文章提要URL
    String URL_COMMENT_LIST = "comment/list/";//URL的评论列表
    String HOST_VIDEO = "http://www.toutiao.com/";//主机视频
    String URL_VIDEO ="https://www.ixigua.com/?utm_source=toutiao&utm_medium=video_channel";//视频URL


      String BASE_URL="http://124.115.170.39:8082/system/rest/UserInfoService/";
    /**
     * 获取新闻详情
     */
    @GET
    Observable<ResultResponse<NewsDetail>> getNewsDetail(@Url String url);


    /**
     * 获取视频页的html代码
     */
    @GET
    Observable<String> getVideoHtml(@Url String url);

    /**
     * 获取视频数据json
     */
    @GET
    Observable<ResultResponse<VideoModel>> getVideoData(@Url String url);

    @GET
    Observable<ResponseBody> getImages(@Url String url);






    /**********************************************************************************************************/
    @FormUrlEncoded
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("system/rest/UserInfoService/")
    Observable<ResultResponse<List<ZdId>>> getTreeListByOwner(@Field("owner") String owner);


//    @FormUrlEncoded
//    @Headers("Content-Type: application/json;charset=UTF-8")
//    @POST("getlistByTreeid")
//    Observable<ResultResponse<List<News>>> getlistByTreeid(@Field("owner") String owner,
//                                                   @Field("wbtreeid") String wbtreeid,
//                                                   @Field("start") String start,
//                                                   @Field("count") String count);



    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("getlistByTreeid")
    Call<ResponseBody> getlistByTreeid(@Body RequestBody body);


    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("getWbnewsById")
    Call<ResponseBody> getWbnewsById(@Body RequestBody body);


    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("searchNews")
    Call<ResponseBody> searchNews(@Body RequestBody body);


    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("getlistVadio")
    Call<ResponseBody> getlistVadio(@Body RequestBody body);

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("Collection_entrance")
    Call<ResponseBody> Collection_entrance(@Body RequestBody body);

}
