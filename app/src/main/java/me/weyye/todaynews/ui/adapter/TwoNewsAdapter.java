package me.weyye.todaynews.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import me.weyye.todaynews.R;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.model.Search;
import me.weyye.todaynews.ui.activity.SearchViewActivity;
import okhttp3.ResponseBody;
import retrofit2.Callback;

import static cn.jpush.android.api.JPushInterface.a.v;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class TwoNewsAdapter extends BaseAdapter {
    private List<News.InfoEntity.ListEntity> list;
    private Context context;
    private LayoutInflater inflater = null;
    private String tv_title, tv_date, iv_icon;
    DisplayImageOptions options;

    public TwoNewsAdapter(Context context, List<News.InfoEntity.ListEntity> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.sjd)
                .showImageForEmptyUri(R.drawable.sjd).showImageOnFail(R.drawable.sjd).cacheInMemory(true)
                .cacheOnDisc(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHodler mHodler;
        if (view == null) {
            view = inflater.inflate(R.layout.news_item2, null);
            mHodler = new ViewHodler();
            mHodler.tv_title = (TextView) view.findViewById(R.id.tv_title);
            mHodler.tv_date = (TextView) view.findViewById(R.id.tv_date);
            mHodler.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            view.setTag(mHodler);
        } else {
            mHodler = (ViewHodler) view.getTag();
        }
        String type = list.get(i).getShowType();
        tv_title = list.get(i).getTitle();
        tv_date = list.get(i).getPtime();
        iv_icon = list.get(i).getImgsrc();
        if (type.equals("左图右文")) {
            mHodler.iv_icon.setVisibility(View.VISIBLE);
            mHodler.tv_title.setText(tv_title);
            mHodler.tv_date.setText(tv_date);
            ImageLoader.getInstance().displayImage(list.get(i).getImgsrc(), mHodler.iv_icon, options);
        }else if (type.equals("没有图片只有文字")){
            mHodler.iv_icon.setVisibility(View.GONE);
            mHodler.tv_title.setText(tv_title);
            mHodler.tv_date.setText(tv_date);
//            ImageLoader.getInstance().displayImage(list.get(i).getImgsrc(), mHodler.iv_icon, options);
        }

        return view;
    }

    class ViewHodler {
        TextView tv_title;
        TextView tv_date;
        ImageView iv_icon;
    }
}







