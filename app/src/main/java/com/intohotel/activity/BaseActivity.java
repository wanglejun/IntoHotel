package com.intohotel.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.intohotel.R;
import com.intohotel.utils.ActivityIntentUtils;

/**
 * activity基类
 */
public abstract class BaseActivity extends FragmentActivity {

    public int widthScreen;// 屏幕宽
    public int heightScreen;// 屏幕高

    private TextView tabTitleTextView;
    private TextView tabRightButton;
    public Context context;

    public ActivityIntentUtils activityIntent;
    /* 取得默认的蓝牙适配器 */
    public BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activityIntent = new ActivityIntentUtils(context);

        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        widthScreen = display.getWidth();
        heightScreen = display.getHeight();
    }

    /**
     * 设置tab bar 标题
     * @param text
     */
    public void setTitleText(String text) {
        tabTitleTextView = (TextView) findViewById(R.id.title_bar_layout)
                .findViewById(R.id.title_bar_text);
        tabTitleTextView.setText(text);
    }

    public TextView getTabRightButton() {
        tabRightButton = (TextView) findViewById(R.id.title_bar_layout)
                .findViewById(R.id.title_bar_right_btn);
        tabRightButton.setVisibility(View.VISIBLE);
        return tabRightButton;
    }

    protected void init(){
        loadViewLayout();
        setListener();
        initData();
    }

    /**
     * 功能描述：<加载页面View>
     */
    protected abstract void loadViewLayout();

    /**
     * 功能描述：<设置监听>
     */
    protected abstract void setListener();

    /**
     * 功能描述：<初始化数据>
     */
    protected abstract void initData();

}
