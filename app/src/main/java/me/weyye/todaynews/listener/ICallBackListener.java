package me.weyye.todaynews.listener;

import android.view.View;

/**
 * Created by Administrator on 2018/2/2 0002.
 * 主页面搜索回调，将父布局中的控件传到子fragment中
 */

public interface ICallBackListener{
    void onItemClick(View view, int i);
}
