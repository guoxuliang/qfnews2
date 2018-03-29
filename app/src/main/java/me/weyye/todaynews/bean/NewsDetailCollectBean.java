package me.weyye.todaynews.bean;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class NewsDetailCollectBean extends RequestBody{


    /**
     * code : 4
     * deviceid : 868857021421064
     * newsid : 48443
     * title : 习近平在参观砥砺奋进的五年大型成就展时强调振奋精神砥砺奋进再接再厉为实现中华民族伟大复兴的中国梦继续奋斗
     * owner : 1394186967
     */

    private String code;
    private String deviceid;
    private String newsid;
    private String title;
    private String owner;

    public NewsDetailCollectBean(String code, String deviceid, String newsid, String title, String owner) {
        this.code=code;
        this.deviceid=deviceid;
        this.newsid=newsid;
        this.title=title;
        this.owner=owner;
    }


    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
