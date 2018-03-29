package me.weyye.todaynews.bean;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class RouteBean extends RequestBody{

    /**
     * owner : 1394186967
     * wbtreeid : 1001
     * start : 1
     * count : 10
     */

    private String owner;
    private String wbtreeid;
    private String start;
    private String count;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWbtreeid() {
        return wbtreeid;
    }

    public void setWbtreeid(String wbtreeid) {
        this.wbtreeid = wbtreeid;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public RouteBean(String owner, String wbtreeid,String start,String count) {
        this.owner = owner;
        this.wbtreeid = wbtreeid;
        this.start = start;
        this.count = count;
    }

    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
