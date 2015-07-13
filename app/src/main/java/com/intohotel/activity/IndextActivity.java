package com.intohotel.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.intohotel.R;
import com.intohotel.constants.Constants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 首页
 */
public class IndextActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.warm_pressing_layout)
    private RelativeLayout warmPressingLayout;
    @ViewInject(R.id.single_valve_layout)
    private RelativeLayout singleValveLayout;
    @ViewInject(R.id.system_valve_layout)
    private RelativeLayout systemValveLayout;
    @ViewInject(R.id.setting_layout)
    private RelativeLayout settingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indext);
        ViewUtils.inject(this);
        init();
    }

    @Override
    protected void loadViewLayout() {

    }

    @Override
    protected void setListener() {
        warmPressingLayout.setOnClickListener(this);
        singleValveLayout.setOnClickListener(this);
        systemValveLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled.

        //打开蓝牙
        if (!mBtAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // 设置蓝牙可见性，最多300秒
            enableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivityForResult(enableIntent, 3);
        }
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.warm_pressing_layout:
                bundle.putInt("module_mark", Constants.FRAGMENT_WARM_PRESSING);
                break;

            case R.id.single_valve_layout:
                bundle.putInt("module_mark", Constants.FRAGMENT_SINGLE_VALVE);
                break;

            case R.id.system_valve_layout:
                bundle.putInt("module_mark", Constants.FRAGMENT_SYSTEM_VALVE);

                break;

            case R.id.setting_layout:
                bundle.putInt("module_mark", Constants.FRAGMENT_SETTING);

                break;
        }
        activityIntent.turnToActivity(MainActivity.class,bundle);

    }
}
