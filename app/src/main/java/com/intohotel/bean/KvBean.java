package com.intohotel.bean;

/**
 * kv 系数 构建基础数据
 * Created by wanglejun on 15/7/12.
 */
public class KvBean {
    //阀门当前读数
    public int vavleValue;
    //当前读数下对应的kv系数
    public double kvValue;

    public int getVavleValue() {
        return vavleValue;
    }

    public void setVavleValue(int vavleValue) {
        this.vavleValue = vavleValue;
    }

    public double getKvValue() {
        return kvValue;
    }

    public void setKvValue(double kvValue) {
        this.kvValue = kvValue;
    }
}
