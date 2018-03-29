package me.weyye.todaynews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class NewsDetailUrl {
    /**
     * code : 200
     * info : [{"author":"","docid":"48443","title":"习近平在参观 砥砺奋进的五年大型成就展","wbtop":0,"imgsrc":"http://www.qinfeng.gov.cn/__local/7/A0/BB/5421F5A2102DB3573214CEDFF55_025D2350_5ABD4.jpg","topicId":1001,"showType":"左图右文","ptime":"2017-09-26 07:44:43","url":"http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48443","topicName":"868857021421064"},{"author":"","docid":"48442","title":" 王岐山在全国纪检监察系统表彰大会上强调 谦虚谨慎 再接再厉 向党和人民交上优异答卷","wbtop":0,"imgsrc":"http://www.qinfeng.gov.cn/__local/3/D7/5B/60FB511CA6FB51CB9EF61809955_B697812A_AD0A.jpg","topicId":1001,"showType":"左图右文","ptime":"2017-09-22 18:47:58","url":"http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48442","topicName":"868857021421064"},{"author":"","docid":"48441","title":"电视专题片《巡视利剑》第四集《巡视全覆盖》","wbtop":0,"imgsrc":"http://www.qinfeng.gov.cn/__local/3/8F/89/96D5D2BDDB8C0D9862954DBEB9E_5D3D1BD1_7D5E.jpg","topicId":1001,"showType":"左图右文","ptime":"2017-09-22 18:47:29","url":"http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48441","topicName":"868857021421064"}]
     */

    private String code;
    private List<InfoEntity> info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public static class InfoEntity {
        /**
         * author :
         * docid : 48443
         * title : 习近平在参观 砥砺奋进的五年大型成就展
         * wbtop : 0
         * imgsrc : http://www.qinfeng.gov.cn/__local/7/A0/BB/5421F5A2102DB3573214CEDFF55_025D2350_5ABD4.jpg
         * topicId : 1001
         * showType : 左图右文
         * ptime : 2017-09-26 07:44:43
         * url : http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48443
         * topicName : 868857021421064
         */

        private String author;
        private String docid;
        private String title;
        private int wbtop;
        private String imgsrc;
        private int topicId;
        private String showType;
        private String ptime;
        private String url;
        private String topicName;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWbtop() {
            return wbtop;
        }

        public void setWbtop(int wbtop) {
            this.wbtop = wbtop;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }


    }
//
//
//    /**
//     * code : 200
//     * info : [{"title":"习近平在参观砥砺奋进的五年大型成就展时强调振奋精神砥砺奋进再接再厉为实现中华民族伟大复兴的中国梦继续奋斗","owner":"1394186967","device":"868857021421064","linkurl":"http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48443","newsid":"48443"}]
//     */
//
//    private String code;
//    private List<InfoEntity> info;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//
//    public List<InfoEntity> getInfo() {
//        return info;
//    }
//    public void setInfo(List<InfoEntity> info) {
//        this.info = info;
//    }
//
//
//
//    public static class InfoEntity {
//        /**
//         * title : 习近平在参观砥砺奋进的五年大型成就展时强调振奋精神砥砺奋进再接再厉为实现中华民族伟大复兴的中国梦继续奋斗
//         * owner : 1394186967
//         * device : 868857021421064
//         * linkurl : http://www.qinfeng.gov.cn/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48443
//         * newsid : 48443
//         */
//
//        private String title;
//        private String owner;
//        private String imgsrc;
//
//        public String getPtime() {
//            return ptime;
//        }
//
//        public void setPtime(String ptime) {
//            this.ptime = ptime;
//        }
//
//        private String ptime;
//        private String device;
//        private String linkurl;
//        private String newsid;
//        private List<InfoEntity> info;
//
//        public String getImgsrc() {
//            return imgsrc;
//        }
//
//        public void setImgsrc(String imgsrc) {
//            this.imgsrc = imgsrc;
//        }
//
//        public String getTitle() {
//            return title;
//
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getOwner() {
//            return owner;
//        }
//
//        public void setOwner(String owner) {
//            this.owner = owner;
//        }
//
//        public String getDevice() {
//            return device;
//        }
//
//        public void setDevice(String device) {
//            this.device = device;
//        }
//
//        public String getLinkurl() {
//            return linkurl;
//        }
//
//        public void setLinkurl(String linkurl) {
//            this.linkurl = linkurl;
//        }
//
//        public String getNewsid() {
//            return newsid;
//        }
//
//        public void setNewsid(String newsid) {
//            this.newsid = newsid;
//        }
//
//        public List<InfoEntity> getInfo() {
//            return info;
//        }
//
////        public List<InfoEntity> getInfo() {
////            return info;
////        }
//    }




}
