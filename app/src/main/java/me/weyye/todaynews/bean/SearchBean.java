package me.weyye.todaynews.bean;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class SearchBean extends RequestBody{


    /**
     * owner : 1394186967
     * keyword : 习近平
     */

    private String owner;
    private String keyword;


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
    }

    public SearchBean(String owner, String keyword) {
        this.owner = owner;
        this.keyword = keyword;
    }
}
