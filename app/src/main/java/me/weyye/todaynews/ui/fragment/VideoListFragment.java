package me.weyye.todaynews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.ApiService;
import me.weyye.todaynews.base.VPApplication;
import me.weyye.todaynews.bean.RouteBean;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.model.NewsVideo;
import me.weyye.todaynews.ui.activity.NewsDetailActivity2;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/2/8 0008.
 */
public class VideoListFragment extends Fragment {

    private ListView videoList;
    Retrofit retrofit;
    private VideoListAdapter videotmAdapter;
    private List<NewsVideo.InfoEntity.ListEntity> listvideo=new ArrayList<NewsVideo.InfoEntity.ListEntity>();
    String s1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517475944300&di=73a484540660c88755d71a7d24ea2bbb&imgtype=0&src=http%3A%2F%2Fnews.youth.cn%2Fsz%2F201708%2FW020170804381587228106.jpg";

    /**
     * 友盟分享
     * @param savedInstanceState
     */
    private ShareAction mShareAction;
    private UMShareListener mShareListener;
    Bitmap map = null;
    URL urlicon = null;
    String docid,ptime,title,imgsrc,UmengShareUrl;

    private VideoListAdapter.MyClickListener myClickListener;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_video, container, false);
        videoList=view.findViewById(R.id.videoList);
        ButterKnife.bind(this, view);
        post();
        umengfx();
        return view;
    }
    public int firstVisible=0,visibleCount=0, totalCount=0;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.e("videoTest", "SCROLL_STATE_FLING");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.e("videoTest", "SCROLL_STATE_IDLE");
//                        autoPlayVideo(view);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.e("videoTest", "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // firstVisibleItem   当前第一个可见的item
                // visibleItemCount   当前可见的item个数
                if (firstVisible == firstVisibleItem) {
                    return;
                }
                firstVisible = firstVisibleItem;
                visibleCount = visibleItemCount;
                totalCount = totalItemCount;
            }
        });
    }


    public class VideoListAdapter extends BaseAdapter {

        //        int[] videoIndexs = {0,0,1,1,1,0,0,1,0,0,1,1,1,0,0};
        int[] videoIndexs = {1,1,1,1,1,1,1,1,1,1};
        Context context;
        LayoutInflater mInflater;
        List<NewsVideo.InfoEntity.ListEntity> listvideo;

        public VideoListAdapter(Context context,List<NewsVideo.InfoEntity.ListEntity> listvideo) {
            this.context = context;
            mInflater = LayoutInflater.from(context);
            this.listvideo=listvideo;
        }

        @Override
        public int getCount() {
//            return videoIndexs.length;
            return listvideo.size();
        }

        @Override
        public Object getItem(int position) {
            return listvideo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //This is the point
            if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
                ((VideoHolder) convertView.getTag()).jcVideoPlayer.release();
            }

            if (videoIndexs[position] == 1) {
                VideoHolder viewHolder;
                if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
                    viewHolder = (VideoHolder) convertView.getTag();
                } else {
                    viewHolder = new VideoHolder();
                    convertView = mInflater.inflate(R.layout.item_videoview, null);
                    viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
                    viewHolder.videotitle = (TextView) convertView.findViewById(R.id.videotitle);
                    viewHolder.videodate = (TextView) convertView.findViewById(R.id.videodate);
                    viewHolder.iv_shrea = (TextView) convertView.findViewById(R.id.iv_shrea);
                    convertView.setTag(viewHolder);
                }
                //图片地址

                boolean setUp = viewHolder.jcVideoPlayer.setUp(/**"http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"+*/listvideo.get(position).getUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,"");//http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4

                if (setUp) {
                    Glide.with(getActivity()).load(listvideo.get(position).getImgsrc()).into(viewHolder.jcVideoPlayer.thumbImageView);
                    viewHolder.videotitle.setText(listvideo.get(position).getTitle());
                    viewHolder.videodate.setText(listvideo.get(position).getPtime());


                }
                myClickListener = new MyClickListener(position);
                viewHolder.iv_shrea.setOnClickListener(myClickListener);
            } else {

                ImageViewHolder imageViewHolder;
                if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof ImageViewHolder) {
                    imageViewHolder = (ImageViewHolder) convertView.getTag();
                } else {
                    imageViewHolder = new ImageViewHolder();
                    LayoutInflater mInflater = LayoutInflater.from(context);
                    convertView = mInflater.inflate(R.layout.item_textview, null);
                    imageViewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);//http://mvpc.eastday.com/vzixun/20180123/20180123102535252977359_1_06400360.mp4
                    Glide.with(getActivity()).load(listvideo.get(position).getImgsrc()).into(imageViewHolder.imageView);
                    convertView.setTag(imageViewHolder);
                }

            }

            return convertView;
        }

        class VideoHolder {
            JCVideoPlayerStandard jcVideoPlayer;
            TextView videotitle;
            TextView videodate;
            TextView iv_shrea;
        }


        class ImageViewHolder {
            ImageView imageView;

        }
        public class MyClickListener implements View.OnClickListener {

            private int position;

            public MyClickListener(int position){
                this.position = position;
            }
            @Override
            public void onClick(View view) {
                docid = listvideo.get(position).getDocid();
                ptime = listvideo.get(position).getPtime();
                title = listvideo.get(position).getTitle();
                imgsrc = listvideo.get(position).getImgsrc();
                UmengShareUrl = listvideo.get(position).getUrl();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            urlicon = new URL(imgsrc);
                            URLConnection conn = urlicon.openConnection();
                            conn.connect();
                            InputStream in;
                            in = conn.getInputStream();
                            map = BitmapFactory.decodeStream(in);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                mShareAction.open();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    private void jiecao(){
        if(VPApplication.instance.VideoPlaying!=null)
        {
            if(VPApplication.instance.VideoPlaying.currentState== JCVideoPlayer.CURRENT_STATE_PLAYING)
            {
                VPApplication.instance.VideoPlaying.startButton.performClick();
            }else if (VPApplication.instance.VideoPlaying.currentState== JCVideoPlayer.CURRENT_STATE_PREPAREING)
            {
                JCVideoPlayer.releaseAllVideos();
            }
        }
    }




    private void post() {
        RouteBean info=new RouteBean("1394186967","1887","0","30"); /*** 利用Gson 将对象转json字符串*/
        Gson gson=new Gson();
        String obj=gson.toJson(info);
        retrofit=new Retrofit.Builder().baseUrl("http://124.115.170.39:8082/system/rest/UserInfoService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        final ApiService login = retrofit.create(ApiService.class);
        retrofit2.Call<ResponseBody> data = login.getlistVadio(body);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                String str=null;
                try {
                    str = response.body().string();
                    response.body().close();
                    String str1=str.substring(1,str.length());
                    String str2=str1.substring(0,str1.length()-1);
                    Gson gson = new Gson();
                    News status = gson.fromJson(str2, News.class);
                    java.lang.reflect.Type listType=new TypeToken<ArrayList<NewsVideo.InfoEntity>>(){}.getType();//TypeToken内的泛型就是Json数据中的类型
                    final ArrayList<NewsVideo.InfoEntity> newsvideoinfo=gson.fromJson(gson.toJson(status.getInfo()), listType);
                    for(NewsVideo.InfoEntity b:newsvideoinfo){
                        listvideo=b.getList();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (listvideo!=null&&!listvideo.equals("")){
                    videotmAdapter=new VideoListAdapter(getActivity(),listvideo);
                    videoList.setAdapter(videotmAdapter);
                }else {
                    Toast.makeText(getActivity(),"暂无数据",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
            } });
    }
    /**
     * 分享
     */
    private void umengfx() {
        mShareListener = new VideoListFragment.CustomShareListener(this);
        mShareAction = new ShareAction(getActivity()).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                            Toast.makeText(getActivity(), "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            Toast.makeText(getActivity(), "复制链接按钮", Toast.LENGTH_LONG).show();

                        }else if(share_media == SHARE_MEDIA.SMS) {
                            new ShareAction(getActivity()).withText("来自分享面板标题")
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }else
                        {
//                            UMWeb web = new UMWeb(Defaultcontent.url);
                            UMWeb web = new UMWeb(UmengShareUrl);
                            web.setTitle(title);
                            web.setDescription(ptime);
                            web.setThumb(new UMImage(getActivity(), map));
                            new ShareAction(getActivity()).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });
    }

    private class CustomShareListener implements UMShareListener {
        private WeakReference<VideoListFragment> mActivity;
        public CustomShareListener(VideoListFragment VideoListFragment) {
            mActivity = new WeakReference(VideoListFragment);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            if (share_media.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(getActivity(), share_media + " 收藏成功啦", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();
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
}