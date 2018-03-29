package me.weyye.todaynews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.weyye.todaynews.R;


/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class AboutActivity extends AppCompatActivity {
    private TextView tv, about;
    private ImageView imageview_back, imgcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tv = (TextView) findViewById(R.id.textView2);
        about = (TextView) findViewById(R.id.about);
        about.setText("\u3000\u3000" + "“秦风网”是由中共陕西省纪律检查委员会、陕西省监察委员会主办的官方门户网站，英文域名为www.qinfeng.gov.cn。 \n" +"\u3000\u3000"+
                "进一步加强官方网站建设，是省纪委省监察厅深入贯彻落实党的十九大精神，根据党风廉政建设和反腐败斗争形势需要，主动运用互联网开展工作的新举措。\n" +"\u3000\u3000"+
                "网站注重发挥在信息公开、权威发布、政策阐释、舆论引导、警示教育、在线学习、互动交流、网络举报等方面的主渠道、主阵地作用，倾力将网站打造成讲述陕西纪检故事的载体，展示陕西纪检形象的窗口。");
        tv.setText("关于我们");
        imageview_back = (ImageView) findViewById(R.id.imageview_back);
        imgcode = (ImageView) findViewById(R.id.imgcode);
        imageview_back.setVisibility(View.VISIBLE);
        imageview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.this.finish();
            }
        });


    }

}


