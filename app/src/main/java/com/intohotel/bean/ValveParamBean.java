package com.intohotel.bean;

import java.util.ArrayList;

/**
 * 阀门参数
 * Created by wanglejun on 15/7/12.
 */
public class ValveParamBean {
    //阀门口径
    public int valveSize;
    //最大流量
    private double maxFlow;
    //阀门kv系数
    public ArrayList<KvBean> kvBeanList;
    public int getValveSize() {
        return valveSize;
    }

    public void setValveSize(int valveSize) {
        this.valveSize = valveSize;
    }

    public double getMaxFlow() {
        return maxFlow;
    }

    public void setMaxFlow(double maxFlow) {
        this.maxFlow = maxFlow;
    }

    public ArrayList<KvBean> getKvBeanList() {
        return kvBeanList;
    }

    public void setKvBeanList(ArrayList<KvBean> kvBeanList) {
        this.kvBeanList = kvBeanList;
    }
}
