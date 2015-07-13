package com.intohotel.bean;

/**
 * 设备
 */
public class DeviceBean {
    //设备名称
    private String deviceName;
    //设备Mac地址
    private String macAddress;
    //是否连接
    private boolean isConnection;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void setIsConnection(boolean isConnection) {
        this.isConnection = isConnection;
    }
}
