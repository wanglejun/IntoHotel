package com.intohotel.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.intohotel.R;
import com.intohotel.activity.adapter.DeviceListAdapter;
import com.intohotel.bean.DeviceBean;
import com.intohotel.utils.BluetoothSocketUtil;
import com.intohotel.utils.ResponseStatus;
import com.intohotel.utils.ToastUtil;
import com.intohotel.view.CustomDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * 蓝牙设备列表
 */
public class DeviceListActivity extends BaseActivity implements Handler.Callback {

    @ViewInject(R.id.device_listview)
    private ListView listView;
    private DeviceListAdapter adapter;
    private ArrayList<DeviceBean> deviceBeanList = new ArrayList<DeviceBean>();
    private SearchDeviceBoradcast mReceiver;
    private Handler handler;
    private BluetoothSocketUtil bluetoothSocketUtil;
    private int connPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        ViewUtils.inject(this);
        init();
    }

    @Override
    protected void loadViewLayout() {
        setTitleText("蓝牙配对设备");
        handler = new Handler(this);
        bluetoothSocketUtil = new BluetoothSocketUtil(context, this.handler);

        adapter = new DeviceListAdapter(context);
        listView.setAdapter(adapter);

        mReceiver = new SearchDeviceBoradcast();
        //注册搜索蓝牙设备广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        registerReceiver(mReceiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);

        mBtAdapter.startDiscovery();
    }

    @Override
    protected void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog("系统提示", "确定连接" + deviceBeanList.get(position).getDeviceName(), position);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 == ResponseStatus.OK) {
            switch (msg.what){
                //连接设备
                case R.id.getConnection:
                    deviceBeanList.get(connPosition).setIsConnection(true);
                    adapter.clearTo(deviceBeanList);
                    break;
            }
        }
        return false;
    }


    private class SearchDeviceBoradcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            System.out.println("action---------------" + action);
            //扫描到蓝牙设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 当前设备已绑定
//                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                DeviceBean deviceBean = new DeviceBean();
                deviceBean.setDeviceName(device.getName());
                deviceBean.setMacAddress(device.getAddress());
                deviceBean.setIsConnection(false);
                deviceBeanList.add(deviceBean);
                adapter.clearTo(deviceBeanList);
//                }
            }
            //扫描结束
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                if (listView.getCount() == 0) {
                    ToastUtil.show(context, "扫描结束");
                }
            }

        }
    }


    public void showDialog(String title, String message, final int position) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                bluetoothSocketUtil.connection(deviceBeanList.get(position).getMacAddress());
                connPosition = position;
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister broadcast listeners
        this.unregisterReceiver(mReceiver);
    }
}
