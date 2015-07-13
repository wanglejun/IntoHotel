package com.intohotel.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.intohotel.R;
import com.intohotel.activity.DeviceListActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 系统设置
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener{
    @ViewInject(R.id.device_btn)
    private Button deviceBnt;
    private View contentView;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_setting, container, false);
        ViewUtils.inject(this,contentView);
        init();
        return contentView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    protected void loadViewLayout() {
        
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        deviceBnt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.device_btn:
                activityIntent.turnToActivity(DeviceListActivity.class);
                break;
        }
    }
}
