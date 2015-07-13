package com.intohotel.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intohotel.R;
import com.intohotel.bean.KvBean;
import com.intohotel.bean.ValveBean;
import com.intohotel.bean.ValveParamBean;
import com.intohotel.bean.WheelViewContentBean;
import com.intohotel.utils.BaseDataUtil;
import com.intohotel.utils.ResponseStatus;
import com.intohotel.utils.ToastUtil;
import com.intohotel.view.CustomDialog;
import com.intohotel.view.WheelViewDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * 单阀调试
 */
public class SingleValveFragment extends BaseFragment implements View.OnClickListener,
        WheelViewDialog.IPositveBtnOnclick,Handler.Callback {
    //阀门类型
    @ViewInject(R.id.valve_type_layout)
    private RelativeLayout valveTypeLayout;
    @ViewInject(R.id.vavle_type_text)
    private TextView vavleTypeText;
    //阀门口径
    @ViewInject(R.id.valve_size_layout)
    private RelativeLayout valveSizeLayout;
    @ViewInject(R.id.vavle_size_text)
    private TextView valveSizeText;
    @ViewInject(R.id.vavle_setting_layout)
    private LinearLayout vavleSettingLayout;

    //目标流量设置
    @ViewInject(R.id.valve_setting_flow_layout)
    private RelativeLayout flowSettingLayout;
    @ViewInject(R.id.flow_setting_text)
    private TextView flowSettingText;
    //阀门当前读数
    @ViewInject(R.id.valve_setting_ring_layout)
    private RelativeLayout ringSettingLayout;
    @ViewInject(R.id.ring_setting_text)
    private TextView ringSettingText;

    @ViewInject(R.id.valve_setting_show_flow_layout)
    private LinearLayout showFlowLayout;
    //显示流量
    @ViewInject(R.id.show_flow_text)
    private TextView showFlowText;
    //压差
    @ViewInject(R.id.pressure_diff_text)
    private TextView pressureDiffText;

    //建议圈数
    @ViewInject(R.id.advice_ring_text)
    private TextView adviceRingText;


    //调试引导1
    @ViewInject(R.id.debug_valve_layout)
    private RelativeLayout debugValeLayout;
    @ViewInject(R.id.debug_valve_btn)
    private Button debugBtn;
    //调试引导2
    @ViewInject(R.id.debug_valve_layout1)
    private RelativeLayout debugValeLayout1;
    @ViewInject(R.id.debug_valve_text1)
    private TextView debugValveText1;
    @ViewInject(R.id.debug_valve_confirm_btn)
    private Button confirmBtn;
    @ViewInject(R.id.debug_valve_cancle_btn)
    private Button cancleBtn;
    //调试引导3
    @ViewInject(R.id.debug_valve_layout2)
    private RelativeLayout debugValeLayout2;
    @ViewInject(R.id.debug_valve_text2)
    private TextView debugValveText2;
    @ViewInject(R.id.debug_valve_confirm_btn2)
    private Button confirmBnt2;
    //调试引导4
    @ViewInject(R.id.debug_valve_layout3)
    private RelativeLayout debugValeLayout3;
    @ViewInject(R.id.debug_valve_text3)
    private TextView debugValveText3;
    @ViewInject(R.id.debug_valve_confirm_btn3)
    private Button confirmBtn3;
    @ViewInject(R.id.debug_valve_cancle_btn3)
    private Button cancleBtn3;

    private BaseDataUtil baseDataUtil;
    private ArrayList<ValveBean> valveBeanList;
    private ArrayList<ValveParamBean> valveParamBeanList;

    //是否开始调试
    private boolean isStart = false;
    private boolean flag = true;
    private View contentView;


    private WheelViewDialog wheelViewDialog;
    private ArrayList<String> cotentList;
    private WheelViewContentBean wheelViewContentBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_single_valve, container, false);
        ViewUtils.inject(this,contentView);
        init();
        return contentView;
    }


    @Override
    protected void loadViewLayout() {
        baseDataUtil = BaseDataUtil.getBaseDataUtil();
        valveParamBeanList = baseDataUtil.initKvData();
        valveBeanList = baseDataUtil.initValveData();

        wheelViewDialog = new WheelViewDialog(mainActivity, mainActivity.widthScreen);
        wheelViewDialog.setPositveBtnOnclick(this);
    }

    @Override
    protected void initData() {
        wheelViewContentBean = new WheelViewContentBean();
        ArrayList<String> content = new ArrayList<String>();
        content.add("0");
        content.add("1");
        content.add("2");
        content.add("3");
        content.add("4");
        content.add("5");
        content.add("6");
        content.add("7");
        content.add("8");
        content.add("9");
        wheelViewContentBean.setConten1(content);
        wheelViewContentBean.setContent2(content);
        wheelViewContentBean.setContent3(content);
        wheelViewContentBean.setContent4(content);
    }

    @Override
    protected void setListener() {
        valveTypeLayout.setOnClickListener(this);
        valveSizeLayout.setOnClickListener(this);
        flowSettingLayout.setOnClickListener(this);
        ringSettingLayout.setOnClickListener(this);
        vavleSettingLayout.setOnClickListener(this);

        debugBtn.setOnClickListener(this);

        confirmBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        confirmBnt2.setOnClickListener(this);
        confirmBtn3.setOnClickListener(this);
        cancleBtn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择阀门类型
            case R.id.valve_type_layout:
                cotentList = new ArrayList<String>();
                for (int i = 0; i < valveBeanList.size(); i++) {
                    cotentList.add(valveBeanList.get(i).getValveName());
                }
                //打开不循环的选择器
                wheelViewDialog.createWheelDialogNoCyclic(cotentList, "请选择阀门类型", R.id.valve_type_layout);
                break;

            //选择阀门口径
            case R.id.valve_size_layout:
                if(valveParamBeanList == null){
                    ToastUtil.show(mainActivity,"请选择阀门类型");
                    return;
                }

                cotentList = new ArrayList<String>();
                for (int i = 0; i < valveParamBeanList.size(); i++) {
                    cotentList.add(String.valueOf(valveParamBeanList.get(i).getValveSize()));
                }
                wheelViewDialog.createWheelDialogNoCyclic(cotentList, "请选择阀门口径", R.id.valve_size_layout);
//                wheelViewDialog.createWheelsDialog(wheelViewContentBean,"请选择阀门口径",R.id.valve_size_layout);
                break;

            //设置目标流量
            case R.id.valve_setting_flow_layout:
                wheelViewDialog.createWheelsDialog(wheelViewContentBean,"请设定目标流量",R.id.valve_setting_flow_layout);
                break;

            //设置阀门当前读数
            case R.id.valve_setting_ring_layout:
                wheelViewDialog.createWheelsDialog(wheelViewContentBean, "请设定阀门当前读数", R.id.valve_setting_ring_layout);
                break;

            //调试阀门
            case R.id.debug_valve_btn:
                String message = "请确认手机和仪表通信在线\n当前阀门类型为："+vavleTypeText.getText().toString()
                        +"\n当前阀门口径为："+valveSizeText.getText().toString()+"\n阀门当前圈数为："
                        +ringSettingText.getText().toString()+"\n设定目标流量后点击确定";
                isStart = true;
                adviceRingText.setText("？圈");
                debugValeLayout.setVisibility(View.GONE);
                debugValeLayout1.setVisibility(View.VISIBLE);

                vavleSettingLayout.setVisibility(View.GONE);
                ringSettingLayout.setVisibility(View.GONE);

                showFlowLayout.setVisibility(View.VISIBLE);
//                showDialog("调试阀门",message,"确定","取消");
                break;

            //确定
            case R.id.debug_valve_confirm_btn:
                debugValeLayout1.setVisibility(View.GONE);
                debugValeLayout2.setVisibility(View.VISIBLE);
                break;

            //取消
            case R.id.debug_valve_cancle_btn:
                debugValeLayout.setVisibility(View.VISIBLE);
                debugValeLayout1.setVisibility(View.GONE);

                vavleSettingLayout.setVisibility(View.VISIBLE);
                ringSettingLayout.setVisibility(View.VISIBLE);

                showFlowLayout.setVisibility(View.GONE);
                break;

            //确定
            case R.id.debug_valve_confirm_btn2:
                debugValeLayout2.setVisibility(View.GONE);
                debugValeLayout3.setVisibility(View.VISIBLE);
                break;

            //继续优化
            case R.id.debug_valve_confirm_btn3:
                break;

            //返回
            case R.id.debug_valve_cancle_btn3:
                debugValeLayout3.setVisibility(View.GONE);
                debugValeLayout.setVisibility(View.VISIBLE);

                vavleSettingLayout.setVisibility(View.VISIBLE);
                ringSettingLayout.setVisibility(View.VISIBLE);

                showFlowLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPositveBtnOnclick(int position, int id) {
        switch (id){
            case R.id.valve_type_layout:
                vavleTypeText.setText(cotentList.get(position));
                valveParamBeanList = valveBeanList.get(position).getValveParamList();
                break;
            case R.id.valve_size_layout:
                valveSizeText.setText(cotentList.get(position)+"mm");

                break;
            case R.id.valve_setting_flow_layout:
                break;
            case R.id.valve_setting_ring_layout:
                break;
        }
    }

    @Override
    public void onPositveBtnOnclick(int position1, int positon2, int positon3, int positon4, int id) {
        switch (id){
            case R.id.valve_setting_flow_layout:
                flowSettingText.setText(wheelViewContentBean.getConten1().get(position1)+wheelViewContentBean.getContent2().get(positon2)
                       +wheelViewContentBean.getContent3().get(positon3) + "." + wheelViewContentBean.getContent4().get(positon4) + " m³/h");
                break;
            case R.id.valve_setting_ring_layout:
                ringSettingText.setText(wheelViewContentBean.getConten1().get(position1)+wheelViewContentBean.getContent2().get(positon2)
                        +wheelViewContentBean.getContent3().get(positon3) + "." + wheelViewContentBean.getContent4().get(positon4)+"圈");

                break;
        }
    }

    /**
     * 调试对话框
     */
    public void showDialog(String title,String message, final String positiveButtonText, final String negativeButtonText){
        CustomDialog.Builder builder = new CustomDialog.Builder(mainActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                if (positiveButtonText.equals("继续优化")) {
                    debugBtn.setVisibility(View.VISIBLE);
                }
                if (negativeButtonText == null) {
                    adviceRingText.setText("3圈");
                    String message = "当前阀门类型为：" + vavleTypeText.getText().toString() + "\n当前阀门口径为："
                            + valveSizeText.getText().toString() + "\n阀门目标流量为：" + flowSettingText.getText().toString()
                            + "\n阀门当前圈数为：" + ringSettingText.getText().toString() + "\n阀门显示流量为："
                            + showFlowText.getText().toString() + "\n建议圈数为：" + adviceRingText.getText().toString();
                    showDialog("调试成功", message, "继续优化", "返回");
                } else {
                    if (isStart) {
                        showDialog("系统提示", "将阀门关闭，点击确定\n之后会计算得到阀门打开的建议圈数。\n旋转阀门到建议开度，并观察显示流量到变化。", "确定", null);
                        isStart = false;
                        debugBtn.setVisibility(View.GONE);
                    }
                }

            }
        });

        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                if (positiveButtonText.equals("继续优化")) {
                    debugBtn.setVisibility(View.VISIBLE);
                }
            }
        });
        builder.create().show();
    }

    /**
     * 获取kv系数
     * @param valveSize 阀门口径
     * @param valveValue 阀门读数
     * @return
     */
    private double getKvValue(int valveSize,int valveValue){
        double kv = 0;
        ArrayList<KvBean> kvBeans = null;
        for (int i = 0;i < valveParamBeanList.size();i++){
            if(valveParamBeanList.get(i).getValveSize() == valveSize){
                kvBeans = valveParamBeanList.get(i).getKvBeanList();
            }
        }

        //当前阀门读数正好有对应的kv
        if(kvBeans.contains(valveValue)){
            for (int i = 0; i <kvBeans.size() ; i++) {
                if (kvBeans.get(i).getVavleValue() == valveValue){
                    kv = kvBeans.get(i).getKvValue();
                    break;
                }
            }
        }else{//没有对应的kv 通过公式计算
            for (int i = 0; i < kvBeans.size(); i++) {
                //查找阀门读数list中第一个比当前阀门读数大的，当前阀门读数对应kv系数区间即为当前位置的阀门读数到前一个位置阀门读数kv
                if(kvBeans.get(i).getVavleValue()>valveValue){
                    // 0.7 + （0.8-0.7）（33-30）/（40-30）
                    kv = kvBeans.get(i-1).getKvValue()+(kvBeans.get(i).getKvValue()-kvBeans.get(i-1).getKvValue())
                            *(valveValue-kvBeans.get(i-1).getVavleValue())/(kvBeans.get(i).getVavleValue()-kvBeans.get(i-1).getVavleValue());
                    break;
                }
            }
        }
        return kv;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == ResponseStatus.OK){
            switch (msg.what){
                case R.id.getHpa:
                    break;

                case R.id.getLpa:
                    break;
            }
        }
        return false;
    }
}
