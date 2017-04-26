package com.lib.lzlin.api.utils.wifiUtils.model;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 扫描结果实体模型
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/31 11:25
 * 修改人:
 * 修改备注:
 */

public class ScanResultModel {
    private String SSID; //WIFI名称
    private String BSSID; //MAC地址
    private int level; //WIFI强度
    private int frequency; //频率
    private double distance; //距离
    private String capabilities; //描述了身份验证、密钥管理和访问点支持的加密方案
    private long timestamp; //Wifi同步时间

    /**
     * 默认无参构造方法
     */
    public ScanResultModel() {
    }

    public ScanResultModel(String SSID, String BSSID, int level, int frequency, double distance, String capabilities, long timestamp) {
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.level = level;
        this.frequency = frequency;
        this.distance = distance;
        this.capabilities = capabilities;
        this.timestamp = timestamp;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
