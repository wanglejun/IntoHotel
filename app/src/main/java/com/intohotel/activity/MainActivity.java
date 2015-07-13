package com.intohotel.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intohotel.R;
import com.intohotel.constants.Constants;
import com.intohotel.fragment.SettingFragment;
import com.intohotel.fragment.SingleValveFragment;
import com.intohotel.fragment.SystemValveFragment;
import com.intohotel.fragment.WarmPressingFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private WarmPressingFragment warmPressingFragment;
    private SingleValveFragment singleValveFragment;
    private SystemValveFragment systemValveFragment;
    private SettingFragment settingFragment;

    //温压测量
    @ViewInject(R.id.warm_pressing_layout)
    private RelativeLayout warmPressingLayout;
    @ViewInject(R.id.warm_pressing_image)
    private ImageView warmPressingImage;
    @ViewInject(R.id.warm_pressing_text)
    private TextView warmPressingText;
    //单阀调试
    @ViewInject(R.id.single_valve_layout)
    private RelativeLayout singleValveLayout;
    @ViewInject(R.id.single_valve_image)
    private ImageView singleValveImage;
    @ViewInject(R.id.single_valve_text)
    private TextView singleValveText;
    //系统提示
    @ViewInject(R.id.system_layout)
    private RelativeLayout systemValveLayout;
    @ViewInject(R.id.system_valve_image)
    private ImageView systemValveImage;
    @ViewInject(R.id.system_text)
    private TextView systemValveText;
    //系统设置
    @ViewInject(R.id.setting_layout)
    private RelativeLayout settingLayout;
    @ViewInject(R.id.setting_image)
    private ImageView settingImage;
    @ViewInject(R.id.setting_text)
    private TextView settingText;

    //模块标记
    private int moduleMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        init();
    }

    @Override
    protected void loadViewLayout() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        moduleMark = bundle.getInt("module_mark");
        switchContent(moduleMark);
    }

    @Override
    protected void setListener() {
        warmPressingLayout.setOnClickListener(this);
        singleValveLayout.setOnClickListener(this);
        systemValveLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.warm_pressing_layout:
                switchContent(Constants.FRAGMENT_WARM_PRESSING);
                break;

            case R.id.single_valve_layout:
                switchContent(Constants.FRAGMENT_SINGLE_VALVE);
                break;

            case R.id.system_layout:
                switchContent(Constants.FRAGMENT_SYSTEM_VALVE);
                break;

            case R.id.setting_layout:
                switchContent(Constants.FRAGMENT_SETTING);
                break;
        }
    }

    /**
     * @param @param switchPosition
     * @return void
     * @throws
     * @Method: switchContent
     * @Description: 切换fragment 修改显示的内容 不会重新加载
     */
    private void switchContent(int switchPosition) {
        // 开启一个Fragment事务
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        hideFragment(transaction);

        switch (switchPosition) {
            case Constants.FRAGMENT_WARM_PRESSING:
                setTitleText("温压测量");
                if (warmPressingFragment == null) {
                    warmPressingFragment = new WarmPressingFragment();
                    transaction.add(R.id.main_fragment, warmPressingFragment);
                }
                transaction.show(warmPressingFragment);
                cutBar(Constants.FRAGMENT_WARM_PRESSING);
                break;

            case Constants.FRAGMENT_SINGLE_VALVE:
                setTitleText("单阀调试");
                if (singleValveFragment == null) {
                    singleValveFragment = new SingleValveFragment();
                    transaction.add(R.id.main_fragment, singleValveFragment);
                }
                transaction.show(singleValveFragment);
                cutBar(Constants.FRAGMENT_SINGLE_VALVE);
                break;

            case Constants.FRAGMENT_SYSTEM_VALVE:
                setTitleText("系统调试");
                if (systemValveFragment == null) {
                    systemValveFragment = new SystemValveFragment();
                    transaction.add(R.id.main_fragment, systemValveFragment);
                }
                transaction.show(systemValveFragment);
                cutBar(Constants.FRAGMENT_SYSTEM_VALVE);
                break;

            case Constants.FRAGMENT_SETTING:
                setTitleText("系统设置");
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.main_fragment, settingFragment);
                }
                transaction.show(settingFragment);
                cutBar(Constants.FRAGMENT_SETTING);
                break;
            default:
                break;
        }

        transaction.commit();
    }

    /**
     * @param @param transaction
     * @return void
     * @throws
     * @Method: hideFragment
     * @Description: 隐藏fragment
     */

    private void hideFragment(FragmentTransaction transaction) {

        if (warmPressingFragment != null) {
            transaction.hide(warmPressingFragment);
        }

        if (singleValveFragment != null) {
            transaction.hide(singleValveFragment);
        }

        if (systemValveFragment != null) {
            transaction.hide(systemValveFragment);
        }

        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }


    /**
     * 底部切换
     *
     * @param moduleMark 模块标识
     */
    private void cutBar(int moduleMark) {
        switch (moduleMark) {
            case Constants.FRAGMENT_WARM_PRESSING:
                warmPressingLayout.setBackgroundResource(R.color.gray);
                warmPressingImage.setBackgroundResource(R.mipmap.icon_warm_pressing_press);
                warmPressingText.setTextColor(getResources().getColor(R.color.dark_bule));

                singleValveLayout.setBackgroundResource(R.color.transparent);
                singleValveImage.setBackgroundResource(R.mipmap.icon_single_valve_normal);
                singleValveText.setTextColor(getResources().getColor(R.color.white));

                systemValveLayout.setBackgroundResource(R.color.transparent);
                systemValveImage.setBackgroundResource(R.mipmap.icon_system_valve_normal);
                systemValveText.setTextColor(getResources().getColor(R.color.white));

                settingLayout.setBackgroundResource(R.color.transparent);
                settingImage.setBackgroundResource(R.mipmap.icon_setting_normal);
                settingText.setTextColor(getResources().getColor(R.color.white));

                break;

            case Constants.FRAGMENT_SINGLE_VALVE:
                warmPressingLayout.setBackgroundResource(R.color.transparent);
                warmPressingImage.setBackgroundResource(R.mipmap.icon_warm_pressing_noraml);
                warmPressingText.setTextColor(getResources().getColor(R.color.white));

                singleValveLayout.setBackgroundResource(R.color.gray);
                singleValveImage.setBackgroundResource(R.mipmap.icon_single_valve_press);
                singleValveText.setTextColor(getResources().getColor(R.color.dark_bule));

                systemValveLayout.setBackgroundResource(R.color.transparent);
                systemValveImage.setBackgroundResource(R.mipmap.icon_system_valve_normal);
                systemValveText.setTextColor(getResources().getColor(R.color.white));

                settingLayout.setBackgroundResource(R.color.transparent);
                settingImage.setBackgroundResource(R.mipmap.icon_setting_normal);
                settingText.setTextColor(getResources().getColor(R.color.white));
                break;

            case Constants.FRAGMENT_SYSTEM_VALVE:
                warmPressingLayout.setBackgroundResource(R.color.transparent);
                warmPressingImage.setBackgroundResource(R.mipmap.icon_warm_pressing_noraml);
                warmPressingText.setTextColor(getResources().getColor(R.color.white));

                singleValveLayout.setBackgroundResource(R.color.transparent);
                singleValveImage.setBackgroundResource(R.mipmap.icon_single_valve_normal);
                singleValveText.setTextColor(getResources().getColor(R.color.white));

                systemValveLayout.setBackgroundResource(R.color.gray);
                systemValveImage.setBackgroundResource(R.mipmap.icon_system_valve_press);
                systemValveText.setTextColor(getResources().getColor(R.color.dark_bule));

                settingLayout.setBackgroundResource(R.color.transparent);
                settingImage.setBackgroundResource(R.mipmap.icon_setting_normal);
                settingText.setTextColor(getResources().getColor(R.color.white));
                break;

            case Constants.FRAGMENT_SETTING:

                warmPressingLayout.setBackgroundResource(R.color.transparent);
                warmPressingImage.setBackgroundResource(R.mipmap.icon_warm_pressing_noraml);
                warmPressingText.setTextColor(getResources().getColor(R.color.white));

                singleValveLayout.setBackgroundResource(R.color.transparent);
                singleValveImage.setBackgroundResource(R.mipmap.icon_single_valve_normal);
                singleValveText.setTextColor(getResources().getColor(R.color.white));

                systemValveLayout.setBackgroundResource(R.color.transparent);
                systemValveImage.setBackgroundResource(R.mipmap.icon_system_valve_normal);
                systemValveText.setTextColor(getResources().getColor(R.color.white));

                settingLayout.setBackgroundResource(R.color.gray);
                settingImage.setBackgroundResource(R.mipmap.icon_setting_press);
                settingText.setTextColor(getResources().getColor(R.color.dark_bule));
                break;
        }
    }
}
