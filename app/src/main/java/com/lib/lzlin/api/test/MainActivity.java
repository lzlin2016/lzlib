package com.lib.lzlin.api.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.lib.lzlin.api.R;
import com.lib.lzlin.api.activity.VirtualKeyBaseActivity;
import com.lib.lzlin.api.custom.widget.StarBarView;
import com.lib.lzlin.api.test.testPercentLayout.TestPercentLayoutActivity;
import com.lib.lzlin.api.utils.activityUtils.ActivityHelper;
import com.lib.lzlin.api.utils.activityUtils.KeyVaule;
import com.lib.lzlin.api.utils.commonUtils.DensityUtils;
import com.lib.lzlin.api.utils.customUtils.ToastUtils_custom;
import com.lib.lzlin.api.utils.gsonFormat.GsonUtils1;

import java.util.List;

public class MainActivity extends VirtualKeyBaseActivity implements View.OnClickListener {
    private LineIndicatoir mLineIndicatoir;
    private TextView textView;
    private TextView textView2;
    private StarBarView mStarBarView;
    private int startAmount = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTv();
        initListView();
        initStartBar();
        findViewById(R.id.btnGsonFormatExecption).setOnClickListener(this);
        Log.e("MainActivity", "onCreate");
        String id = getIntent().getStringExtra("id");
        textView.setText(id == null ? "Test Router" : "Test Router " + id);
        textView2.setText("Test PagerSlidingTabStrip");

        mLineIndicatoir = (LineIndicatoir) findViewById(R.id.mLineIndicatoir);
    }

    /**
     * 初始化listview
     */
    private void initListView() {
//        ListView mListView = (ListView) findViewById(R.id.mListView);
//        List<String> mData = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            mData.add("position = " + i);
//        }
//        mListView.setAdapter(new CommonRecyclerAdapter<String>(this, R.layout.item_layout_wifi, mData) {
//            @Override
//            baseCommon void initItemData(CommonRecyclerViewHolder holder, String item) {
//
//            }
//        });
    }

    /**
     * 初始化星级评定
     */
    private void initStartBar() {
        mStarBarView = (StarBarView) findViewById(R.id.mStarBarView);
    }

    public void testWifi(View view) {
        startActivity(new Intent(this, TestWifiActivity_.class));
    }
    public void testRecyclerView(View view) {
        startActivity(new Intent(this, TestRecyclerActivity.class));
    }
    public void btnTestPermissions(View view) {
        startActivity(new Intent(this, TestPermission6Activity.class));
    }
    public void btnSlipViewPager(View view) {
        startActivity(new Intent(this, TestSlipViewPagerActivity.class));
    }

    public void testPercentLayout(View view) {
        startActivity(new Intent(this, TestPercentLayoutActivity.class));
    }

    public void btnSub(View view) {
        mStarBarView.setStarRating(startAmount <= 1 ? 1 : --startAmount);
    }

    public void btnAdd(View view) {
        mStarBarView.setStarRating(startAmount >= 5 ? 5 : ++startAmount);
    }

    private void initTv() {
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestWebActivity.class));
                finish();
            }
        });
        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, TestPagerSlidingtabStripActivity.class));
                ActivityHelper.startActivity(MainActivity.this, TestPagerSlidingtabStripActivity.class, new KeyVaule("1", true), new KeyVaule("1", 1.0));
//                ActivityHelper.startActivity(MainActivity.this, TestPagerSlidingtabStripActivity.class, new KeyVaule[]{ new KeyVaule("true",true), new KeyVaule("1",1)});
                finish();
            }
        });
        TextView tvPhoneInfo = (TextView) findViewById(R.id.tvPhoneInfo);
        DensityUtils.hideNaviationBar(this);
        tvPhoneInfo.setText( "" +
                "dp2px 5 = " + DensityUtils.dp2px(this, 5) + "\n" +
                "px2dp 5 = " + DensityUtils.px2dp(this, 5) + "\n" +
                "getScreenWidth : " + DensityUtils.getScreenWidth(this) + "\n" +
                "getScreenHeight : " + DensityUtils.getScreenHeight(this) + "\n" +
                "getStatuesHeight : " + DensityUtils.getStatuesHeight(this) + "\n" +
                "getVirtualKeyHeight : " + DensityUtils.getVirtualKeyHeight(this) + "\n" +
                "getHeightWithoutVirtualKey : " + DensityUtils.getHeightWithoutVirtualKey(this) + "\n" +
                "getHeightWithVirtualKey : " + DensityUtils.getHeightWithVirtualKey(this) + "\n" +
                "checkDeviceHasNavigationBar : " + DensityUtils.checkDeviceHasNavigationBar(this) + "\n"
        );
    }

    /**
     * 测试Gson 格式化异常
     */
    public void btnGsonFormatExecption() {
        String json = "{\"index\":\"这是什么鬼\", \"age\":\"1.1\", \"like\":null, \"name\":null}";
//        String json = "{\"index\":\"这是什么鬼\", \"age\":\"1.1\", \"like\":[\"测试\",\"测试2\"], \"name\":null}";
        Bean1 bean1 = GsonUtils1.GsonToBean(json, Bean1.class);
        ToastUtils_custom.showToast(this, bean1.toString() + (bean1.getName() == null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGsonFormatExecption: // 测试Gson 格式化异常
                btnGsonFormatExecption();
                break;
        }
    }

    class Bean1 {
        double index;
        int age;
        String name;
        private List<String> like;

        public double getIndex() {
            return index;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public List<String> getLike() {
            return like;
        }

        @Override
        public String toString() {
            return "Bean1{" +
                    "index=" + index +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    ", like=" + like +
                    '}';
        }
    }

    public void moveUp(View view) {
//        mLineIndicatoir.startMoveAnimation( LineIndicatoir.DIRECTION_UP, 200);
        // (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
        Animation anim = new TranslateAnimation(0, 0, 0, 200); // 左 右 上 下;
        anim.setInterpolator(this, android.R.interpolator.linear);
        anim.setFillAfter(true);
        anim.setDuration(200);
        mLineIndicatoir.startAnimation(anim);
    }

    public void moveDown(View view) {
//        mLineIndicatoir.startMoveAnimation( LineIndicatoir.DIRECTION_DOWN, 200);
        Animation anim = new TranslateAnimation(0, 0, 200, 0); // 左 右 上 下;
        anim.setInterpolator(this, android.R.interpolator.linear);
        anim.setFillAfter(true);
        anim.setDuration(200);
        mLineIndicatoir.startAnimation(anim);
    }

    public void moveLeft(View view) {
//        mLineIndicatoir.startMoveAnimation( LineIndicatoir.DIRECTION_LEFT, 200);
        Animation anim = new TranslateAnimation(200, 0, 0, 0); // 左 右 上 下;
        anim.setInterpolator(this, android.R.interpolator.linear);
        anim.setFillAfter(true);
        anim.setDuration(200);
        mLineIndicatoir.startAnimation(anim);
    }

    public void moveRight(View view) {
//        mLineIndicatoir.startMoveAnimation( LineIndicatoir.DIRECTION_RIGHT, 200);
        Animation anim = new TranslateAnimation(0, 200, 0, 0); // 左 右 上 下;
        anim.setInterpolator(this, android.R.interpolator.linear);
        anim.setFillAfter(true);
        anim.setDuration(200);
        mLineIndicatoir.startAnimation(anim);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

}
