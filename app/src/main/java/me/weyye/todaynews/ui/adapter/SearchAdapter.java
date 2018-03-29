package me.weyye.todaynews.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import me.weyye.todaynews.R;
import me.weyye.todaynews.model.Search;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class SearchAdapter extends BaseAdapter {
    private LayoutInflater inflater = null;
    DisplayImageOptions options;
    private List<Search.InfoEntity.ListEntity> list;
    private Context context;

    private String tv_title, tv_date, iv_icon;

    public SearchAdapter(Context context, List<Search.InfoEntity.ListEntity> list) {
        this.list = list;
        this.context = context;
    inflater = LayoutInflater.from(context);
    options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodlers mHodler;
        if (view==null){
            view = inflater.inflate(R.layout.news_item2, null);
            mHodler = new ViewHodlers();
            mHodler.tv_title = (TextView) view.findViewById(R.id.tv_title);
            mHodler.tv_date = (TextView) view.findViewById(R.id.tv_date);
            mHodler.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            view.setTag(mHodler);
        }else {
            mHodler = (ViewHodlers) view.getTag();
        }

        tv_title = list.get(i).getTitle();
        tv_date = list.get(i).getPtime();
        iv_icon = list.get(i).getNewurl();
        mHodler.tv_title.setText(tv_title);
        mHodler.tv_date.setText(tv_date);
        ImageLoader.getInstance().displayImage(list.get(i).getNewurl(), mHodler.iv_icon, options);
        return view;
    }
}

class ViewHodlers {
    TextView tv_title;
    TextView tv_date;
    ImageView iv_icon;
}

