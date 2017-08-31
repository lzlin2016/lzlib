//package com.lib.lzlin.api.base;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
//import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
//import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
//
//import www.ibenhong.com.happygo.R;
//import www.ibenhong.com.happygo.constance.HConstant;
//import www.ibenhong.com.happygo.utils.ToastUtils;
//import www.ibenhong.com.happygo.utils.uiUtils.UIHelper;
//import www.ibenhong.com.happygo.utils.uiUtils.WrapUtils;
//
///**
// * BaseFragment
// * <p>
// * 类的描述: Fragment 基类
// * 创建人: lz -  Administrator
// * 创建时间: 2017/6/9  11:02
// * 修改人: lz -  Administrator
// * 修改备注:
// */
//public abstract class BaseFragment extends Fragment implements View.OnClickListener, BseImpl {
//    protected final String TAG = this.getClass().getName();
//    protected boolean isRefresh = false;
//    private TwinklingRefreshLayout mRefresh; // 刷新布局
//    protected Context mContext;
//    protected LayoutInflater mInflater = null;
//    protected Intent mIntent = null;
//    protected View mLayout = null;
//    private View mRootView = null;
//    protected UIHelper helper = null;
//    private UIHelper mToolbarHelper = null;
//
//    public BaseFragment() {
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        if (mRootView == null) {
//            mInflater = inflater;
//            mRootView = inflater.inflate(R.layout.fragment_base, container, false);
//            Log.i(TAG, "onCreateView 执行了");
//            initOnCreate();
//        }
//        return mRootView;
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
//        Toolbar toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
//        View toolbarLayout = mInflater.inflate(toolbarLayoutId, null);
//        setToolbarFill(toolbarLayout);
//        mToolbarHelper = UIHelper.getInstance(mContext, toolbarLayout);
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
//    private void setToobarTitle(Object title) {
//        if (title instanceof Integer) {
//            mToolbarHelper
//                    .setText4Tv(R.id.tvToolbarTitle, (int) title);
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
//    @Override
//    public void initOnCreate() {
//        init();
//        initConfig();
//        initUI();
//        initData();
//    }
//
//    private void init() {
//        mContext = getContext();
//        mIntent = getActivity().getIntent();
//        mLayout = mInflater.inflate(getLayoutID(), null);
//        helper = UIHelper.getInstance(mContext, mLayout);
//    }
//
//    @Override
//    public void initConfig() {
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        wrap();
//    }
//
//    @Override
//    public void wrap() {
//        WrapUtils.wrapSelf((LinearLayout) mRootView.findViewById(R.id.mLayout), mLayout);
//    }
//
//    public void wrapRootLayout() {
//        WrapUtils.wrap((LinearLayout) mRootView.findViewById(R.id.mLayout), mLayout);
//    }
//
//    @Override
//    public void wrapWithoutRecorded() {
//        WrapUtils.wrap((LinearLayout) mRootView.findViewById(R.id.mLayout), mInflater.inflate(R.layout.without_recorded, null));
//    }
//
//    @Override
//    public void wrapWithoutNetWork() {
//        WrapUtils.wrap((LinearLayout) mRootView.findViewById(R.id.mLayout), mInflater.inflate(R.layout.without_network, null));
//    }
//
//    /**
//     * 初始化刷新控件
//     * @param mRefreshID Refresh 控件 ID
//     */
//    protected void initRefreshLayout(int mRefreshID) {
//        mRefresh = helper.getViewById(mRefreshID);
//        if (mRefresh == null || !(mRefresh instanceof TwinklingRefreshLayout)) {
//            ToastUtils.showShort(mContext, "mRefreshID 找不到对应控件, 请检查xml 文件");
//            return;
//        }
//        ProgressLayout headerView = new ProgressLayout(mContext);
//        mRefresh.setHeaderView(headerView);
//        mRefresh.setOverScrollRefreshShow(false);
//        mRefresh.setEnableOverScroll(false);
//        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                isRefresh = true;
//                current_page = HConstant.constance.DEFAULT_CURRENT_PAGE;
//                initData();
//            }
//
//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                isRefresh = false;
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
//            ToastUtils.showShort(mContext, "mRefresh == null, 请先调用 initRefreshLayout() 初始化");
//            return;
//        }
//        if (isRefresh) {
//            mRefresh.finishRefreshing();
//        } else {
//            mRefresh.finishLoadmore();
//        }
//    }
//
//    /**
//     * 处理刷新事件
//     */
//    protected void dealRefreshEvent(boolean isLastPage) {
//        if (mRefresh == null) {
//            ToastUtils.showShort(mContext, "mRefresh == null, 请先调用 initRefreshLayout() 初始化");
//            return;
//        }
//        mRefresh.setEnableLoadmore(!isLastPage);
//        if (!isLastPage) {
//            ++current_page;
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mInflater = null;
//        mIntent = null;
//        mLayout = null;
//        helper = null;
//    }
//
//    @Override
//    public void onClick(View v) {
//    }
//}
