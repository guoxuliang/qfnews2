package me.weyye.todaynews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class News {

    /**
     * code : 200
     * info : [{"sumcount":6,"list":[{"author":"","docid":"48442","title":" 王岐山在全国纪检监察系统表彰大会上强调 谦虚谨慎 再接再厉 向党和人民交上优异答卷","wbtop":"0","imgsrc":"http://10.5.27.185/__local/3/D7/5B/60FB511CA6FB51CB9EF61809955_B697812A_AD0A.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:47:58","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48442","topicName":"首页"},{"author":"","docid":"48441","title":"电视专题片《巡视利剑》第四集《巡视全覆盖》","wbtop":"0","imgsrc":"http://10.5.27.185/__local/3/8F/89/96D5D2BDDB8C0D9862954DBEB9E_5D3D1BD1_7D5E.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:47:29","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48441","topicName":"首页"},{"author":"","docid":"48440","title":"中央纪委监察部网站推出中秋国庆期间监督","wbtop":"0","imgsrc":"http://10.5.27.185/__local/A/83/31/8268BF379FEB5704427759007EE_8E891C97_501A.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:46:56","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48440","topicName":"首页"},{"author":"","docid":"48439","title":"只要方向正确 迈出一步就是胜利\u2014\u2014总结全面从严治党成果，让制度更加科学严密之二","wbtop":"0","imgsrc":"http://10.5.27.185/__local/B/D7/29/9A0A9584301BC0ABB856562AAE2_8EFFCECB_5422.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:46:17","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48439","topicName":"首页"},{"author":"","docid":"48438","title":"秦风论坛：惩前才能毖后 治病为了救人","wbtop":"0","imgsrc":"http://10.5.27.185/__local/0/12/61/6553B1F3F403854F0B19B4BF80D_57C888E7_4E15.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:44:16","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48438","topicName":"首页"}]}]
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
         * sumcount : 6
         * list : [{"author":"","docid":"48442","title":" 王岐山在全国纪检监察系统表彰大会上强调 谦虚谨慎 再接再厉 向党和人民交上优异答卷","wbtop":"0","imgsrc":"http://10.5.27.185/__local/3/D7/5B/60FB511CA6FB51CB9EF61809955_B697812A_AD0A.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:47:58","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48442","topicName":"首页"},{"author":"","docid":"48441","title":"电视专题片《巡视利剑》第四集《巡视全覆盖》","wbtop":"0","imgsrc":"http://10.5.27.185/__local/3/8F/89/96D5D2BDDB8C0D9862954DBEB9E_5D3D1BD1_7D5E.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:47:29","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48441","topicName":"首页"},{"author":"","docid":"48440","title":"中央纪委监察部网站推出中秋国庆期间监督","wbtop":"0","imgsrc":"http://10.5.27.185/__local/A/83/31/8268BF379FEB5704427759007EE_8E891C97_501A.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:46:56","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48440","topicName":"首页"},{"author":"","docid":"48439","title":"只要方向正确 迈出一步就是胜利\u2014\u2014总结全面从严治党成果，让制度更加科学严密之二","wbtop":"0","imgsrc":"http://10.5.27.185/__local/B/D7/29/9A0A9584301BC0ABB856562AAE2_8EFFCECB_5422.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:46:17","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48439","topicName":"首页"},{"author":"","docid":"48438","title":"秦风论坛：惩前才能毖后 治病为了救人","wbtop":"0","imgsrc":"http://10.5.27.185/__local/0/12/61/6553B1F3F403854F0B19B4BF80D_57C888E7_4E15.jpg","topicId":"1001","showType":"左图右文","ptime":"2017-09-22 18:44:16","url":"http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48438","topicName":"首页"}]
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
             * docid : 48442
             * title :  王岐山在全国纪检监察系统表彰大会上强调 谦虚谨慎 再接再厉 向党和人民交上优异答卷
             * wbtop : 0
             * imgsrc : http://10.5.27.185/__local/3/D7/5B/60FB511CA6FB51CB9EF61809955_B697812A_AD0A.jpg
             * topicId : 1001
             * showType : 左图右文
             * ptime : 2017-09-22 18:47:58
             * url : http://10.5.27.185/content.jsp?urltype=news.NewsContentUrl&wbtreeid=1001&wbnewsid=48442
             * topicName : 首页
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
