package com.intohotel.utils;

import com.intohotel.bean.KvBean;
import com.intohotel.bean.ValveBean;
import com.intohotel.bean.ValveParamBean;

import java.util.ArrayList;

/**
 * 初始基础数据
 * Created by wanglejun on 15/7/12.
 */
public class BaseDataUtil {
    public static BaseDataUtil baseDataUtil;


    public static BaseDataUtil getBaseDataUtil() {
        if(baseDataUtil == null){
            baseDataUtil = new BaseDataUtil();
        }
        return baseDataUtil;
    }

    //初始阀门基础数据
    public ArrayList<ValveBean> initValveData(){
        //DIA_LIST=25:7,32:8.5,40:20,50:28,65:53,80:78,125:116,150:171,200:263

        ArrayList<ValveBean> valveBeans = new ArrayList<ValveBean>();
        //ZETA静态阀
        ValveBean valveBean = new ValveBean();
        valveBean.setValveName("ZETA静态阀");
        valveBean.setValveType(1);

        ArrayList<ValveParamBean> valveParamBeans = new ArrayList<ValveParamBean>();
        ValveParamBean valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(25);
        valveParamBean.setMaxFlow(7);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(32);
        valveParamBean.setMaxFlow(8.5);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(40);
        valveParamBean.setMaxFlow(20);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(50);
        valveParamBean.setMaxFlow(28);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(65);
        valveParamBean.setMaxFlow(53);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(80);
        valveParamBean.setMaxFlow(78);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(125);
        valveParamBean.setMaxFlow(116);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(150);
        valveParamBean.setMaxFlow(171);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(200);
        valveParamBean.setMaxFlow(263);
        valveParamBeans.add(valveParamBean);

        valveBean.setValveParamList(valveParamBeans);
        valveBeans.add(valveBean);


        //ZETA智慧阀门
        valveBean = new ValveBean();
        valveBean.setValveName("ZETA智慧阀门");
        valveBean.setValveType(2);
        //俩个阀门口径最大值参数相同 参考文档
        valveBean.setValveParamList(valveParamBeans);
        valveBeans.add(valveBean);
        return valveBeans;
    }


    //初始ZETA2.csv基础数据
    public ArrayList<ValveParamBean> initKvData(){
        ArrayList<ValveParamBean> valveParamBeans = new ArrayList<ValveParamBean>();
        ArrayList<KvBean> kvBeans = new ArrayList<KvBean>();
        KvBean kvBean = new KvBean();
        kvBean.setVavleValue(10);
        kvBean.setKvValue(0.5);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(20);
        kvBean.setKvValue(0.6);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(30);
        kvBean.setKvValue(0.7);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(40);
        kvBean.setKvValue(0.8);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(50);
        kvBean.setKvValue(0.9);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(60);
        kvBean.setKvValue(1);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(70);
        kvBean.setKvValue(1.1);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(80);
        kvBean.setKvValue(1.2);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(90);
        kvBean.setKvValue(1.2);
        kvBeans.add(kvBean);

        kvBean = new KvBean();
        kvBean.setVavleValue(100);
        kvBean.setKvValue(1.3);
        kvBeans.add(kvBean);

        ValveParamBean valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(25);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(32);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(40);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(50);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(65);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(50);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(100);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(125);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(150);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(200);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(250);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(300);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);

        valveParamBean = new ValveParamBean();
        valveParamBean.setValveSize(400);
        valveParamBean.setKvBeanList(kvBeans);
        valveParamBeans.add(valveParamBean);
        return valveParamBeans;
    }
}
