package me.weyye.todaynews.model;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class NewsVideo {

    /**
     * code : 200
     * info : [{"sumcount":7,"list":[{"author":"","docid":"66098","title":"云安纪事（报送单位：宝鸡市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/04/242i6xk9rz.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-09-04 10:56:26","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/09/04/1mv4s139ho.mp4","topicName":"微电影"},{"author":"","docid":"66093","title":"红木匣（报送单位：宝鸡市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/4w7o3cwffy.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 13:28:22","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/15lci0auzg.mp4","topicName":"微电影"},{"author":"","docid":"66096","title":"低保的诱惑（报送单位：宝鸡市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/10/10/26rkp6ar3b.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 13:03:25","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1r9tgkbv2h.mp4","topicName":"微电影"},{"author":"","docid":"66092","title":"闹心的发票（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/10/10/35z1s53r3b.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:44:48","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1g0mx83uod.mp4","topicName":"微电影"},{"author":"","docid":"66097","title":"规矩办事  清廉行政（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/2tshorcfj4.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:40:53","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/15j364iuii.mp4","topicName":"微电影"},{"author":"","docid":"66094","title":"微之信（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/2ts19aefhp.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:31:39","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1489z13us3.mp4","topicName":"微电影"},{"author":"","docid":"66095","title":"错误的荣誉（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/2zdys35fwa.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:10:55","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1t412b1ud7.mp4","topicName":"微电影"}]}]
     */

    private int code;
    private List<InfoEntity> info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * sumcount : 7
         * list : [{"author":"","docid":"66098","title":"云安纪事（报送单位：宝鸡市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/04/242i6xk9rz.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-09-04 10:56:26","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/09/04/1mv4s139ho.mp4","topicName":"微电影"},{"author":"","docid":"66093","title":"红木匣（报送单位：宝鸡市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/4w7o3cwffy.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 13:28:22","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/15lci0auzg.mp4","topicName":"微电影"},{"author":"","docid":"66096","title":"低保的诱惑（报送单位：宝鸡市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/10/10/26rkp6ar3b.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 13:03:25","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1r9tgkbv2h.mp4","topicName":"微电影"},{"author":"","docid":"66092","title":"闹心的发票（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/10/10/35z1s53r3b.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:44:48","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1g0mx83uod.mp4","topicName":"微电影"},{"author":"","docid":"66097","title":"规矩办事  清廉行政（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/2tshorcfj4.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:40:53","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/15j364iuii.mp4","topicName":"微电影"},{"author":"","docid":"66094","title":"微之信（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/2ts19aefhp.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:31:39","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1489z13us3.mp4","topicName":"微电影"},{"author":"","docid":"66095","title":"错误的荣誉（报送单位：安康市纪委）","wbtop":"0","imgsrc":"http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/01/2zdys35fwa.jpg","topicId":"1887","showType":"左图右文","ptime":"2017-08-28 12:10:55","url":"http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/08/28/1t412b1ud7.mp4","topicName":"微电影"}]
         */

        private int sumcount;
        private List<ListEntity> list;

        public int getSumcount() {
            return sumcount;
        }

        public void setSumcount(int sumcount) {
            this.sumcount = sumcount;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            /**
             * author :
             * docid : 66098
             * title : 云安纪事（报送单位：宝鸡市纪委）
             * wbtop : 0
             * imgsrc : http://www.qinfeng.gov.cn/_mediafile/${ownername}/2017/09/04/242i6xk9rz.jpg
             * topicId : 1887
             * showType : 左图右文
             * ptime : 2017-09-04 10:56:26
             * url : http://www.qinfeng.gov.cn/_mediafile/qfwxb/2017/09/04/1mv4s139ho.mp4
             * topicName : 微电影
             */

            private String author;
            private String docid;
            private String title;
            private String wbtop;
            private String imgsrc;
            private String topicId;
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

            public String getWbtop() {
                return wbtop;
            }

            public void setWbtop(String wbtop) {
                this.wbtop = wbtop;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getTopicId() {
                return topicId;
            }

            public void setTopicId(String topicId) {
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
    }
}
