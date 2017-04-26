package com.lib.lzlin.api.test.testPercentLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lib.lzlin.api.R;

public class TestPercentLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_percent_layout);
    }

    public void gotoDemo1(View v) {
        startActivity(new Intent(this, Demo1Activity.class));
    }

    public void gotoDemo2(View v) {
        startActivity(new Intent(this, Demo2Activity.class));
    }

    public void gotoDemo3(View v) {
        startActivity(new Intent(this, Demo3Activity.class));
    }
}