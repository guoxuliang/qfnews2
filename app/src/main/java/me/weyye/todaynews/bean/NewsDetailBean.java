package me.weyye.todaynews.bean;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class NewsDetailBean extends RequestBody{

    /**
     * owner : 1394186967
     * wbnewsid : 48443
     */

    private String owner;
    private String wbnewsid;

    public NewsDetailBean(String owner, String wbnewsid) {
        this.owner = owner;
        this.wbnewsid = wbnewsid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWbnewsid() {
        return wbnewsid;
    }

    public void setWbnewsid(String wbnewsid) {
        this.wbnewsid = wbnewsid;
    }

    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
