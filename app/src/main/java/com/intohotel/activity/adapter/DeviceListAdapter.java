package com.intohotel.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intohotel.R;
import com.intohotel.bean.DeviceBean;

/**
 * 设备列表 adapter
 * Created by wanglejun on 15/7/11.
 */
public class DeviceListAdapter extends AdapterBase<DeviceBean>{

    public DeviceListAdapter(Context con) {
        super(con);
    }

    @Override
    protected View getExView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_list_device,null);
            viewHolder.nameText = (TextView)convertView.findViewById(R.id.item_device_name_text);
            viewHolder.addressText = (TextView)convertView.findViewById(R.id.item_device_address_text);
            viewHolder.statusText = (TextView)convertView.findViewById(R.id.item_device_status_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        DeviceBean deviceBean = (DeviceBean)getItem(position);
        viewHolder.nameText.setText(deviceBean.getDeviceName());
        viewHolder.addressText.setText(deviceBean.getMacAddress());
        if(deviceBean.isConnection()){
            viewHolder.statusText.setVisibility(View.VISIBLE);
//            convertView.setBackgroundColor(Color.parseColor("#cccccc"));
        }else{
            viewHolder.statusText.setVisibility(View.GONE);
//            convertView.setBackgroundResource(context.getResources().getColor(R.color.white));
        }
        return convertView;
    }

    private class ViewHolder{
        private TextView nameText;
        private TextView addressText;
        private TextView statusText;
    }
}
