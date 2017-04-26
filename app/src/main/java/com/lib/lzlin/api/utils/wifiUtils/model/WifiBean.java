package com.lib.lzlin.api.utils.wifiUtils.model;

import android.net.wifi.ScanResult;

import static android.R.attr.type;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述:
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/30 15:42
 * 修改人:
 * 修改备注:
 */

public class WifiBean {
    private int id;
    private String ssid;
    private ScanResult scanResult;

    public WifiBean(int id, String ssid, ScanResult scanResult) {
        this.id = type;
        this.ssid = ssid;
        this.scanResult = scanResult;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public ScanResult getScanResult() {
        return scanResult;
    }

    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
