package com.lib.lzlin.api.utils.wifiUtils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.lib.lzlin.api.utils.wifiUtils.model.ScanResultModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述:    Android中自动连接到指定SSID的Wi-Fi
 *          参考资料:  http://www.cnblogs.com/pied/p/3767336.html
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/30 14:45
 * 修改人:
 * 修改备注:
 */

public class WifiAdmin {
    // 定义WifiManager对象
    private static WifiAdmin instance;
    // 定义WifiManager对象
    private static WifiManager mWifiManager;
    // 定义WifiInfo对象
    private WifiInfo mWifiInfo;
    // 扫描出的网络连接列表
    private List<ScanResult> mWifiList;
    // 网络连接列表
    private List<WifiConfiguration> mWifiConfiguration;
    // 定义一个WifiLock
    WifiManager.WifiLock mWifiLock;
    //四种加密类型
    private final static int NONE = 0x00000000;
    private final static int WPA_PSK = 0x00000001; //WPA-PSK/WPA2-PSK(目前最安全家用加密)
    private final static int WPA_EAP = 0x00000002;
    private final static int IEEE8021X = 0x00000003;

    /**
     * 构造方法私有化, 禁止instance对象
     */
    private WifiAdmin() {}

    /**
     * 单例模式，懒汉，线程安全
     * @return  WifiAdmins 实例
     */
    public static synchronized WifiAdmin getInstance() {
        if(instance == null) {
            instance = new WifiAdmin();
        }
        return instance;
    }

    /**
     * 配置初始化工作
     * @param context
     */
    public void initConfig(Context context) {
        // 取得WifiManager对象
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        // 取得WifiInfo对象
        mWifiInfo = mWifiManager.getConnectionInfo();
    }

    /**
     * 获得WifiManager管理器
     * @return
     */
    public WifiManager getWifiManager() {
        return mWifiManager;
    }

    /**
     * 检查WIFI开启状态
     * @return 返回WIFI_STATE_DISABLED, //wifi关闭状态
     * WIFI_STATE_DISABLING , //wifi正在关闭状态
     * WIFI_STATE_ENABLED, //wifi开启状态
     * WIFI_STATE_ENABLING, //wifi正在开启状态
     * WIFI_STATE_UNKNOWN //未知状态，不在以上四种状态时未知状态
     * 这五种状态中的一种
     */
    public int getWifiStatus() {
        return mWifiManager.getWifiState();
    }

    /**
     * 打开WIFI
     * @return 操作成功返回true
     */
    public boolean openWifi() {
        if (!mWifiManager.isWifiEnabled()) {
            return mWifiManager.setWifiEnabled(true);
        }
        return false;
    }

    /**
     * 关闭WIFI
     * @return 操作成功返回true
     */
    public boolean closeWifi() {
        if (mWifiManager.isWifiEnabled()) {
            return mWifiManager.setWifiEnabled(false);
        }
        return false;
    }

    /**
     * 锁定WifiLock
     */
    public void acquireWifiLock() {
        mWifiLock.acquire();
    }

    /**
     * 解锁WifiLock
     */
    public void releaseWifiLock() {
        // 判断时候锁定
        if (mWifiLock.isHeld()) {
            mWifiLock.acquire();
        }
    }

    /**
     * 创建一个WifiLock
     */
    public void creatWifiLock() {
        mWifiLock = mWifiManager.createWifiLock("Test");
    }

    /**
     * 得到配置好的网络
     * @return
     */
    public List<WifiConfiguration> getConfiguration() {
        return mWifiConfiguration;
    }

    /**
     * 指定配置好的网络进行连接
     * @param index
     */
    public void connectConfiguration(int index) {
        // 索引大于配置好的网络索引返回
        if (index > mWifiConfiguration.size()) {
            return;
        }
        // 连接配置好的指定ID的网络
        mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId, true);
    }

    /**
     * 开启wifi扫描
     */
    public boolean  startScan() {
        // 判断wifi是否开启
        if(getWifiStatus() == WifiManager.WIFI_STATE_ENABLED) {
            return  mWifiManager.startScan();
        }
        return false;
    }

    /**
     * 得到网络列表, 扫描结果
     * @return
     */
    public List<ScanResult> getWifiList() {
        //先开启扫描获得最新wifi结果集
        if(startScan()) {
            // 得到扫描结果
            this.mWifiList = mWifiManager.getScanResults();
            return mWifiList;
        }
        return null;
    }

    /**
     * 获取当前环境下所有网络配置信息
     * @return
     */
    public List<WifiConfiguration> getConfiguredNetworks() {
        // 得到配置好的网络连接
        this.mWifiConfiguration = mWifiManager.getConfiguredNetworks();
        return mWifiConfiguration;
    }

    /**
     * 获取扫描结果ScanResultModel实体模型集合
     * @return
     */
    public List<ScanResultModel> getScanResultModel() {
        List<ScanResultModel> scanResultModels = new ArrayList<ScanResultModel>();

        if(mWifiList == null) {
            getWifiList();
        }
        if(mWifiList != null) {
            for (ScanResult scanResult : mWifiList) {
                ScanResultModel scanResultModel = new ScanResultModel();
                scanResultModel.setSSID(scanResult.SSID);
                scanResultModel.setBSSID(scanResult.BSSID);
                scanResultModel.setLevel(scanResult.level);
                scanResultModel.setCapabilities(scanResult.capabilities); //是否需要密码
                scanResultModel.setFrequency(scanResult.frequency);
                scanResultModels.add(scanResultModel);
            }
            return scanResultModels;
        }
        return null;
    }

    /**
     * 查看扫描结果
     * @return
     */
    public StringBuilder lookUpScanResult () {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mWifiList.size(); i++) {
            stringBuilder.append("Index_" + new Integer(i + 1).toString() + ":");
            // 将ScanResult信息转换成一个字符串包
            // 其中把包括：BSSID、SSID、capabilities、frequency、level
            stringBuilder.append((mWifiList.get(i)).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    /**
     * 检查之前是否配置过该网，并返回该网络配置
     * @param SSID 网络名称（WIFI名称）
     * @return 存在则返回网络配置对象WifiConfiguration，否则返回null
     */
    public WifiConfiguration checkPreWhetherConfigured(String SSID) {
        List<WifiConfiguration> existingConfigs = getConfiguredNetworks();
        for(WifiConfiguration configuration : existingConfigs) {
            if(configuration.SSID.equals(SSID)) {
                return configuration;
            }
        }
        return null;
    }

    /**
     * 添加一个新网络到本地网络列表，添加完成后默认是不激活状态，需要掉用enableNetwork函数连接
     * @param SSID wifi名称
     * @param password 需要连接wifi密码
     * @return 添加成功返回网络标示(networkId)
     */
    public int addWifiConfig(String SSID, String password) {
        //检查本地配置列表是否存在，不存在进行添加
        if(checkPreWhetherConfigured(SSID) == null) {
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            wifiConfiguration.SSID = "\"" + SSID + "\""; //为什么要这样，看源码注释
            wifiConfiguration.preSharedKey = "\"" + password + "\"";
            wifiConfiguration.hiddenSSID = false;
            wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
            return mWifiManager.addNetwork(wifiConfiguration);
        }
        return -1;
    }

    /**
     * 通过指定networkId连接wifi
     * @param networkId
     * @return 连接成功返回true，否则返回false
     */
    public boolean connectWifByNetWorkId(int networkId) {
        List<WifiConfiguration> wifiConfigurations = getConfiguredNetworks();
        for(WifiConfiguration configuration : wifiConfigurations) {
            if(configuration.networkId == networkId) {
                return mWifiManager.enableNetwork(networkId, true); //激活该ID Wifi连接
            }
        }
        return false;
    }

    /**
     * 计算信号等级
     * @param rssi
     * @param numLevels
     * @return 计算后的等级
     */
    public int calculateSignalLevel(int rssi, int numLevels) {
        return mWifiManager.calculateSignalLevel(rssi, numLevels);
    }

    /**
     * 从扫描结果里面得到单个wifi信号
     * @param BSSID
     * @return
     */
    public int getSignalLevelByScanResults(String BSSID) {
        if(mWifiList == null) {
            getWifiList();
        }
        for (ScanResult scanResult : mWifiList) {
            if(scanResult.BSSID.equals(BSSID)) {
                return scanResult.level;
            }
        }
        return 0;
    }

    /**
     * 添加一个网络并连接
     * @param wcg   WifiConfiguration 配置信息
     */
    public boolean addNetwork(WifiConfiguration wcg) {
        int wcgID = mWifiManager.addNetwork(wcg);
        return mWifiManager.enableNetwork(wcgID, true);
    }

    /**
     * 断开指定ID的网络
     * @param netId     网络的ID
     */
    public void disconnectWifi(int netId) {
        mWifiManager.disableNetwork(netId);
        mWifiManager.disconnect();
    }

    /**
     * 对CreateWifiInfo进行简单的说明:
     * @param SSID      第一参数是SSID的名称；
     * @param Password  第二个参数是指定SSID网络的密码，当不需要密码是置空（”“）；
     * @param Type      第三个参数是热点类型：1-无密码 / 2-WEP密码验证（未测试）/ 3-WAP或WAP2 PSK密码验证。
     * @return
     */
    public WifiConfiguration CreateWifiInfo(String SSID, String Password, int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

        // TODO　需要特别注意的
        // 成功加入指定的wifi后，都会在终端的wifi列表中新添加一个以该指定ssid的网络，所以每运行一次程序，列表中就会多一个相同名字的ssid。
        // 该方法就是检查wifi列表中是否有以输入参数为名的wifi热点，如果存在，则在CreateWifiInfo方法开始配置wifi网络之前将其移除，以避免ssid的重复：
        WifiConfiguration tempConfig = this.IsExsits(SSID);
        if(tempConfig != null) {
            mWifiManager.removeNetwork(tempConfig.networkId);
        }

        if(Type == 1) { //WIFICIPHER_NOPASS
            // TODO lz 未加密, 即没有密码的, 不能直接传 "" , 而是需要传 "\"\""
            config.wepKeys[0] = "\"\"";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if(Type == 2) { //WIFICIPHER_WEP
            config.hiddenSSID = true;
            config.wepKeys[0]= "\""+Password+"\"";
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if(Type == 3) { //WIFICIPHER_WPA
            config.preSharedKey = "\""+Password+"\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    /**
     * CreateWifiInfo方法开始配置wifi网络之前移除已配置过的SSID 网络，以避免ssid的重复
     * @param SSID
     * @return
     */
    private WifiConfiguration IsExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }

    //    1.一般WIFI加密有几种方式：
//    (1).WPA-PSK/WPA2-PSK(目前最安全家用加密)
//    (2).WPA/WPA2(较不安全)
//    (3).WEP(安全较差)
//    (4).EAP(迄今最安全的)
    public static final int SECURITY_PSK = 1;
    public static final int SECURITY_NONE = 2;
    public static final int SECURITY_WEP = 3;
    public static final int SECURITY_EAP = 4;
    public static final int SECURITY_ESS = 5; // 可能需要其他登录信息
    public static final int SECURITY_UNKNOW = 6; // 未知

    /**
     * 通过 WifiConfiguration 获取WIFI 的加密方式 / 秘钥类型
     * @param config    WIFI 配置信息
     * @return          加密方式
     */
    public int getSecurity(WifiConfiguration config) {
        if (config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_PSK)) {
            return SECURITY_PSK;
        }
        if (config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_EAP) || config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.IEEE8021X)) {
            return SECURITY_EAP;
        }
        return (config.wepKeys[0] != null) ? SECURITY_WEP : SECURITY_NONE;
    }

    /**
     * 通过 ScanResult 获取WIFI 的加密方式 / 秘钥类型
     * @param scanResult    扫描结果
     * @return              加密方式
     */
    public int getCipherType(ScanResult scanResult) {
        String capabilities = scanResult.capabilities.toUpperCase();
        if (capabilities.contains("WPA")) {     //  WPA2-PSK 加密 / WPA-PSK 加密
            Log.e("WIFI 的加密方式: ", "wpa");
            return SECURITY_PSK;
        } else if (capabilities.contains("WEP")) {  //  WEP 加密
            Log.e("WIFI 的加密方式: ", "wep");
            return SECURITY_WEP;
        } else if (capabilities.contains("ESS")) {  // 可能需要其他登录信息
            Log.e("WIFI 的加密方式: ", "ESS");
            return SECURITY_EAP;
        } else {                                    // 没有加密
            Log.e("WIFI 的加密方式: ", "no");
            return SECURITY_NONE;
        }
    }

    /**
     *  通过 SSID 获取WIFI 的加密方式 / 秘钥类型
     * @param SSID wifi名称
     * @return
     */
    public String getKeyType(String SSID) {
        if(mWifiList == null) {
            getWifiList();
        }
        for (ScanResult scResult : mWifiList) {
            if (!TextUtils.isEmpty(scResult.SSID) && scResult.SSID.equals(SSID)) {
                String capabilities = scResult.capabilities;
                if (!TextUtils.isEmpty(capabilities)) {
                    if (capabilities.contains("WPA") || capabilities.contains("wpa")) {
                        return "WPA/WPA2";
                    } else if (capabilities.contains("WEP") || capabilities.contains("wep")) {
                        return "WEP";
                    } else {
                        return "";
                    }
                }
            }
        }
        return null;
    }

    //-----------------------------当前连接wifi信息-----------------------------------
    /**
     * 得到当前连接Wifi信息
     * @return
     */
    public WifiInfo getCurConnectedWifiInfo() {
        return mWifiManager.getConnectionInfo();
    }

    /**
     * 得到连接的MAC地址
     * @return
     */
    public String getConnectedMacAddress() {
        WifiInfo wifiInfo = getCurConnectedWifiInfo();
        return (wifiInfo == null) ? "null" : wifiInfo.getMacAddress();
    }

    /**
     * 得到连接的名称SSID
     * @return
     */
    public String getConnectedSSID() {
        WifiInfo wifiInfo = getCurConnectedWifiInfo();
        return (wifiInfo == null) ? "null" : wifiInfo.getSSID();
    }

    /**
     * 得到接入点的BSSID
     * @return
     */
    public String getBSSID() {
        WifiInfo wifiInfo = getCurConnectedWifiInfo();
        return (wifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
    }

    /**
     * 得到连接的IP地址
     * @return
     */
    public int getConnectedIPAddress(){
        WifiInfo wifiInfo = getCurConnectedWifiInfo();
        return (wifiInfo == null) ?  0 : wifiInfo.getIpAddress();
    }

    /**
     * 得到连接的networkId
     * @return
     */
    public int getNetworkId(){
        WifiInfo wifiInfo = getCurConnectedWifiInfo();
        return (wifiInfo == null) ?  0 : wifiInfo.getNetworkId();
    }

}
