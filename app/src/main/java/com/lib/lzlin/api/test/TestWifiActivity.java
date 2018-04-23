package com.lib.lzlin.api.test;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lib.lzlin.api.R;
import com.lib.lzlin.api.commonAdapter.list_gridViewAdapter.CommonAdapter;
import com.lib.lzlin.api.commonAdapter.list_gridViewAdapter.CommonViewHolder;
import com.lib.lzlin.api.utils.uiUtils.UIHelper;
import com.lib.lzlin.api.utils.wifiUtils.WifiAdmin;
import com.lib.lzlin.api.utils.wifiUtils.model.WifiBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试WIFI 类
 */

@EActivity(R.layout.activity_test_wifi)
public class TestWifiActivity extends AppCompatActivity {
    private Activity activity;
    private List<WifiBean> mData = new ArrayList<>();
    private WifiAdmin mWifiAdmin;

    @AfterViews
    void init() {
        mWifiAdmin = WifiAdmin.getInstance();
        mWifiAdmin.initConfig(this);
    }

    // TODO lz　所有使用onClick 的点击事件, 都需要申明成public
    public void testConnectWifi(View view) {
        mWifiAdmin.openWifi();
        mWifiAdmin.addNetwork(mWifiAdmin.CreateWifiInfo("@PHICOMM_18", "189968ab", 3));
    }

    @ViewById
    ListView mListView;
    public void btnInitListView (final View view) {
        activity = this;
        screen();
        mListView.setAdapter(new CommonAdapter<WifiBean>(this, R.layout.item_layout_wifi, mData) {

            @Override
            public void initItemData(UIHelper holder, int position, final WifiBean item) {
                holder.setText4Tv(R.id.tvWifiName, item.getSsid())
                        .setText4Tv(R.id.tvWifiPwd, item.getScanResult().toString())
                        .setOnClickListener(R.id.btnConnect, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(activity, "测试连接到WIFI " + item.getSsid() , Toast.LENGTH_SHORT).show();
                                mWifiAdmin.openWifi();
                                mWifiAdmin.addNetwork(mWifiAdmin.CreateWifiInfo(item.getSsid(), "", 1));
                            }
                        });
                int cipherType = mWifiAdmin.getCipherType(item.getScanResult());
                switch (cipherType) {
                    case WifiAdmin.SECURITY_PSK:
                    case WifiAdmin.SECURITY_WEP:
                    case WifiAdmin.SECURITY_EAP:
                        holder.setClickEnablility(R.id.btnConnect, false)
                                .setText4Tv(R.id.btnConnect, "已加密");
                        break;
                    case WifiAdmin.SECURITY_NONE:
                        holder.setClickEnablility(R.id.btnConnect, true)
                                .setText4Tv(R.id.btnConnect, "连接WIFI");
                        break;
                    case WifiAdmin.SECURITY_ESS:
                        holder.setClickEnablility(R.id.btnConnect, true)
                                .setText4Tv(R.id.btnConnect, "尝试连接, 可能需要其他登录信息");
                        break;
                    case WifiAdmin.SECURITY_UNKNOW:
                        holder.setClickEnablility(R.id.btnConnect, true)
                                .setText4Tv(R.id.btnConnect, "尝试连接");
                        break;
                }
            }
        });
    }

    public void btnScreen(View view) {
        screen();
    }

    private void screen() {
        mData.clear(); // 每次扫描时必须先清空
        mWifiAdmin.startScan();
        List<ScanResult> wifiList = mWifiAdmin.getWifiList();
        for (int i = 0; i < wifiList.size(); i++) {
            // 将ScanResult信息转换成一个字符串包
            // 其中把包括：BSSID、SSID、capabilities、frequency、level
            ScanResult scanResult = wifiList.get(i);
            mData.add(new WifiBean(i, scanResult.SSID + "", scanResult)); // 第一个参数没有实际意义id
        }
    }

}
