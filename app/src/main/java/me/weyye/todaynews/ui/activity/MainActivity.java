package me.weyye.todaynews.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseActivity;
import me.weyye.todaynews.base.VPApplication;
import me.weyye.todaynews.jpush.ExampleUtil;
import me.weyye.todaynews.jpush.LocalBroadcastManager;
import me.weyye.todaynews.ui.fragment.FragmentController;
import me.weyye.todaynews.ui.updateservice.UpdateService;

public class MainActivity extends BaseActivity {

    private long exitTime = 0;
    public static boolean isForeground = false;

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.ivIconHome)
    ImageView ivIconHome;
    @BindView(R.id.tvTextHome)
    TextView tvTextHome;
    @BindView(R.id.tvBadgeHome)
    TextView tvBadgeHome;
    @BindView(R.id.ivIconVideo)
    ImageView ivIconVideo;
    @BindView(R.id.tvTextVideo)
    TextView tvTextVideo;
    @BindView(R.id.ivIconAttention)
    ImageView ivIconAttention;
    @BindView(R.id.tvTextAttention)
    TextView tvTextAttention;
    @BindView(R.id.ivIconMe)
    ImageView ivIconMe;
    @BindView(R.id.tvTextMe)
    TextView tvTextMe;
    private FragmentController mController;//主界面Fragment控制器

    @Override
    protected void loadViewLayout() {
        /**
         * 初始化布局文件
         * 绑定点击事件
         */
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        JPushInterface.init(getApplicationContext());
        JPushInterface.getRegistrationID(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());
//        JPushInterface.stopPush(getApplicationContext());
        registerMessageReceiver();  // used for receive msg
//        update();
    }


    @Override
    protected void bindViews() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        //初始化主界面Fragment控制器
        mController = FragmentController.getInstance(this, R.id.fl_content, true);//除下边tab页之外的区域
        setEnableSwipe(false);//下边的Tab不可滑动
        mController.showFragment(0);//控制显示那个Tab页
    }

    private View lastSelectedIcon;
    private View lastSelectedText;


    /**
     * Tab页来回切换  默认设置
     */
    @Override
    protected void setListener() {
        for (int i = 0; i < llBottom.getChildCount(); i++) {
            if (i == 0) {
                //默认选中首页
                setSelectIcon(ivIconHome, tvTextHome);
            }
            final int position = i;
            llBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                    if (lastSelectedText != null) lastSelectedText.setSelected(false);

                    RelativeLayout rl = (RelativeLayout) v;
                    ImageView icon = (ImageView) rl.getChildAt(0);
                    TextView text = (TextView) rl.getChildAt(1);
                    mController.showFragment(position);
                    setSelectIcon(icon, text);
                }
            });
        }
    }

    /**
     * 选择不同的Tab  然后设置图片和文字
     * @param iv
     * @param tv
     */
    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        mController.onDestroy();
    }




    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "me.weyye.todaynews.jpush.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//        }
    }



    /**
     *
     * @param view
     */
    public void update(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //启动服务
                Intent service = new Intent(MainActivity.this,UpdateService.class);
                startService(service);
            }
        }).start();

    }

    /**
     *再按一次退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
