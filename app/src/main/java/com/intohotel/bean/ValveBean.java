package com.intohotel.bean;

import java.util.ArrayList;

/**
 * 阀门
 */
public class ValveBean {
    //阀门名称
    private String valveName;
    //阀门类型
    private int valveType;
    //阀门参数
    private ArrayList<ValveParamBean> valveParamList;
    public String getValveName() {
        return valveName;
    }

    public void setValveName(String valveName) {
        this.valveName = valveName;
    }

    public int getValveType() {
        return valveType;
    }

    public void setValveType(int valveType) {
        this.valveType = valveType;
    }

    public ArrayList<ValveParamBean> getValveParamList() {
        return valveParamList;
    }

    public void setValveParamList(ArrayList<ValveParamBean> valveParamList) {
        this.valveParamList = valveParamList;
    }
}
