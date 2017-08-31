//package com.lib.lzlin.api.base;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.lib.lzlin.api.R;
//import com.lib.lzlin.api.application.MyApp;
//import com.lib.lzlin.api.application.MyAppUtil;
//import com.lib.lzlin.api.utils.customUtils.ToastUtils;
//import com.lib.lzlin.api.utils.uiUtils.UIHelper;
//import com.lib.lzlin.api.utils.uiUtils.WrapUtils;
//
///**
// * BaseActiivty
// * <p>
// * 类的描述: Activity 基类
// * 创建人: lz - Administrator
// * 创建时间:  2017/6/7 15:30
// * 修改人: lz - Administrator
// * 修改备注:
// */
//
//public abstract class BaseActiivty extends AppCompatActivity implements View.OnClickListener, BseImpl {
//    protected final String TAG = this.getClass().getName();
//    private TwinklingRefreshLayout mRefresh; // 刷新布局
//    protected Activity mActivity;
//    protected LayoutInflater mInflater = null;
//    protected Intent mIntent = null;
//    protected View mLayout = null;
//    private UIHelper mToolbarHelper = null;
//    protected UIHelper helper = null;
//    private boolean isLoadingMore = false; // 是否是加载更多. FALSE表示下拉刷新
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 竖屏
//        setContentView(R.layout.activity_base);
//        Log.i(TAG, "onCreate 执行了");
//        initOnCreate();
//    }
//
//    public void initOnCreate() {
//        init();
//        wrap();
//        initConfig();
//        initUI();
//        initData();
//    }
//
//    @Override
//    public void initConfig() {  }
//
//    private void init() {
//        mActivity = this;
//        mIntent = getIntent();
//        mInflater = getLayoutInflater();
//        MyAppUtil.addActivity(mActivity);
//        mLayout = mInflater.inflate(getLayoutID(), null);
//        helper = UIHelper.getInstance(this, mLayout);
//    }
//
//    protected UIHelper initToolbar(Object title) {
//        return initToolbar(R.layout.toolbar, title);
//    }
//
//    protected UIHelper initTitle4Toolbar(int title) {
//        return initToolbar(R.layout.toolbar, title);
//    }
//
//    protected UIHelper initToolbar(int toolbarLayoutId) {
//        Log.e(TAG, "initToolbar 执行了");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        View toolbarLayout = mInflater.inflate(toolbarLayoutId, null);
//        setToolbarFill(toolbarLayout);
//        mToolbarHelper = UIHelper.getInstance(mActivity, toolbarLayout);
//        toolbar.addView(toolbarLayout);
//        toolbar.setVisibility(View.VISIBLE);
//        return mToolbarHelper;
//    }
//
//    private void setToolbarFill(View toolbarLayout) {
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        toolbarLayout.setLayoutParams(params);
//    }
//
//    protected UIHelper initToolbar(int toolbarLayoutId, Object title) {
//        initToolbar(toolbarLayoutId);
//        setToobarTitle(title);
//        return mToolbarHelper;
//    }
//
//    protected UIHelper initToolbarShowBack (Object title) {
//        initToolbar(R.layout.toolbar);
//        setToobarTitle(title);
//        showBack();
//        return mToolbarHelper;
//    }
//
//    /**
//     * 显示back 键
//     */
//    private void showBack() {
//        mToolbarHelper.setVisibility(R.id.layoutBack, View.VISIBLE)
//                .setOnClickListener(R.id.layoutBack, this);
//    }
//
//    private void setToobarTitle(Object title) {
//        if (title instanceof Integer) {
//            mToolbarHelper
//                    .setText4Tv(R.id.tvToolbarTitle, (int)title);
//        } else if (title instanceof String) {
//            mToolbarHelper
//                    .setText4Tv(R.id.tvToolbarTitle, (String) title);
//        } else {
//            Log.e(TAG, TAG + "  --> setToobarTitle  Exception\n"
//                    + "title isn\'t an int or a String, please check your title params");
//            mToolbarHelper
//                    .setText4Tv(R.id.tvToolbarTitle, title.toString());
//        }
//    }
//
//    /**
//     * 初始化刷新控件
//     * @param mRefreshID Refresh 控件 ID
//     */
//    protected void initRefreshLayout(int mRefreshID) {
//        mRefresh = helper.getViewById(mRefreshID);
//        if (mRefresh == null || !(mRefresh instanceof TwinklingRefreshLayout)) {
//            ToastUtils.showToast(mActivity, "mRefreshID 找不到对应控件, 请检查xml 文件");
//            return;
//        }
//        RefreshHelper.initRefreshLayout(mActivity, mRefresh);
//        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                current_page = HConstant.constance.DEFAULT_CURRENT_PAGE;
//                isLoadingMore = false;
//                initData();
//            }
//
//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                isLoadingMore = true;
//                initData();
//            }
//        });
//    }
//
//    /**
//     * 处理刷新控件
//     */
//    protected void finishRefreshLayout() {
//        if (mRefresh == null) {
//            ToastUtils.showToast(mActivity, "mRefresh == null, 请先调用 initRefreshLayout() 初始化");
//            return;
//        }
//        if (isLoadingMore) {
//            mRefresh.finishLoadmore();
//        } else {
//            mRefresh.finishRefreshing();
//        }
//    }
//
//    /**
//     * 处理刷新事件
//     */
//    protected void dealRefreshEvent(boolean isLastPage) {
//        if (mRefresh == null) {
//            ToastUtils.showShort(mActivity, "mRefresh == null, 请先调用 initRefreshLayout() 初始化");
//            return;
//        }
//        mRefresh.setEnableLoadmore(!isLastPage);
//        if (!isLastPage) {
//            ++ current_page;
//        }
//    }
//
//    @Override
//    public void wrap() {
//        WrapUtils.wrapSelf((LinearLayout) findViewById(R.id.mLayout), mLayout);
//    }
//
//    public void wrapRootLayout() {
//        WrapUtils.wrap((LinearLayout) findViewById(R.id.mLayout), mLayout);
//    }
//
//    public void wrapWithoutRecorded() {
//        WrapUtils.wrap((LinearLayout) findViewById(R.id.mLayout), mInflater.inflate(R.layout.without_recorded, null));
//    }
//
//    @Override
//    public void wrapWithoutNetWork() {
//        WrapUtils.wrap((LinearLayout) findViewById(R.id.mLayout), mInflater.inflate(R.layout.without_network, null));
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mInflater = null;
//        mIntent = null;
//        mLayout = null;
//        helper = null;
//        MyAppUtil.deleteActivity(mActivity); // 移除
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.layoutBack:
//                finish();
//                break;
//        }
//    }
//}
