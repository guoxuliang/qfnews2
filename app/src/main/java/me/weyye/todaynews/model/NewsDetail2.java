package me.weyye.todaynews.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24 0024.
 * 新闻详情
 */

public class NewsDetail2 {

    @SerializedName("code")
    private String _$Code212; // FIXME check this code
    private List<InfoEntity> info;

    public String get_$Code212() {
        return _$Code212;
    }

    public void set_$Code212(String _$Code212) {
        this._$Code212 = _$Code212;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public static class InfoEntity {
        @SerializedName("author")
        private String _$Author176; // FIXME check this code
        private String body;
        private String docid;
        private String picnews;
        private String title;
        private String article;
        private String img;
        private String topicId;
        private String ptime;

        public String get_$Author176() {
            return _$Author176;
        }

        public void set_$Author176(String _$Author176) {
            this._$Author176 = _$Author176;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getPicnews() {
            return picnews;
        }

        public void setPicnews(String picnews) {
            this.picnews = picnews;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }
    }
}
