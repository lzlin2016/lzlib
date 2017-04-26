package com.lib.lzlin.api.custom.widget.numberButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lib.lzlin.api.R;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 购物车, 计数控件
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/23 13:59
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class NumberButton extends LinearLayout implements View.OnClickListener, TextWatcher {
    /**
     * 最大的数量
     **/
    public static final int MAX_VALUE = 100;

    /**
     * 最小的数量
     **/
    public static final int MIN_VALUE = 1;

    private int countValue = 1;//数量

    private ImageView ivAdd, ivMinu;

    private EditText etCount;

    private int maxValue = MAX_VALUE;

    private Context mContext;

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs);
    }

    /**
     * 功能描述：设置最大数量
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/3 18:33
     * 参数：
     */
    public void setMaxValue(int max) {
        this.maxValue = max;
    }

    /**
     * 功能描述：设置当前数量
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/3 18:33
     * 参数：
     */
    public void setCurrentValue(int currentValue) {
        this.countValue = currentValue;
        etCount.setText("" + currentValue);
    }

    /**
     * 功能描述：获取当前数量
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/3 18:33
     * 参数：
     */
    public int getCurrentValue() {
        return countValue;
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.number_button, this);

        ivMinu = (ImageView) findViewById(R.id.iv_count_minus);
        ivMinu.setOnClickListener(this);

        ivAdd = (ImageView) findViewById(R.id.iv_count_add);
        ivAdd.setOnClickListener(this);

        etCount = (EditText) findViewById(R.id.et_count);
        etCount.addTextChangedListener(this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberButton);

        int maxValue = a.getInt(R.styleable.NumberButton_nbMaxCount, 100);
        int currentValue = a.getInt(R.styleable.NumberButton_nbCurrentCount, 1);
        int ivWidth = a.getDimensionPixelSize(R.styleable.NumberButton_nbIvWidth, -1);
        int ivHeight = a.getDimensionPixelSize(R.styleable.NumberButton_nbIvHeight, -1);
        int etMinWidth = a.getDimensionPixelSize(R.styleable.NumberButton_nbEtMinWidth, -1);
        int etMinHeight = a.getDimensionPixelSize(R.styleable.NumberButton_nbEtMinHeight, -1);
        int etTextSize = a.getDimensionPixelSize(R.styleable.NumberButton_nbEtTextSize, -1);
        // 减号
        ViewGroup.LayoutParams lp_1 = ivMinu.getLayoutParams();
        lp_1.width = ivWidth;
        lp_1.height = ivHeight;
        ivMinu.setLayoutParams(lp_1);
        // 加号
        ViewGroup.LayoutParams lp_2 = ivAdd.getLayoutParams();
        lp_2.width = ivWidth;
        lp_2.height = ivHeight;
        ivAdd.setLayoutParams(lp_2);
        // 输入框
        etCount.setMinWidth(etMinWidth);
        etCount.setMinHeight(etMinHeight);
        etCount.setTextSize(DensityUtil.px2sp(context, etTextSize));

        setMaxValue(maxValue);
        setCurrentValue(currentValue);
        a.recycle();
        btnChangeWord();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_count_add:
                addAction();
                break;
            case R.id.iv_count_minus:
                minuAction();
                break;


        }
    }

    /**
     * 添加操
     */
    private void addAction() {
        countValue++;
        btnChangeWord();
    }


    /**
     * 删除操作
     */
    private void minuAction() {
        countValue--;
        btnChangeWord();
    }

    /**
     * 功能描述：
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/12 10:29
     * 参数：boolean 是否需要重新赋值
     */
    private void changeWord(boolean needUpdate) {
        if (needUpdate) {
            etCount.removeTextChangedListener(this);
            if (!TextUtils.isEmpty(etCount.getText().toString().trim())) {  //不为空的时候才需要赋值
                etCount.setText(String.valueOf(countValue));
            }
            etCount.addTextChangedListener(this);
        }
        etCount.setSelection(etCount.getText().toString().trim().length());
        if (listener != null) {
            //
            listener.change(countValue);
        }
    }

    private void btnChangeWord() {
        ivMinu.setEnabled(countValue > MIN_VALUE);
        ivAdd.setEnabled(countValue < maxValue);
        etCount.setText(String.valueOf(countValue));
        etCount.setSelection(etCount.getText().toString().trim().length());
        if (listener != null) {
            //
            listener.change(countValue);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean needUpdate = false;
        if (!TextUtils.isEmpty(s)) {
            countValue = Integer.valueOf(s.toString());
            if (countValue <= MIN_VALUE) {
                countValue = MIN_VALUE;
                ivMinu.setEnabled(false);
                ivAdd.setEnabled(true);
                needUpdate = true;
//                Toast.makeText(mContext, String.format("最少添加%s个数量", MIN_VALUE), Toast.LENGTH_SHORT).show();
            } else if (countValue >= maxValue) {
                countValue = maxValue;
                ivMinu.setEnabled(true);
                ivAdd.setEnabled(false);
                needUpdate = true;
//                Toast.makeText(mContext, String.format("最多只能添加%s个数量", maxValue), Toast.LENGTH_SHORT).show();

            } else {
                ivMinu.setEnabled(true);
                ivAdd.setEnabled(true);
            }
        } else {  //如果编辑框被清空了，直接填1
            countValue = 1;
            ivMinu.setEnabled(false);
            ivAdd.setEnabled(true);
            needUpdate = true;
//            Toast.makeText(mContext, String.format("最少添加%s个数量", MIN_VALUE), Toast.LENGTH_SHORT).show();

        }
        changeWord(needUpdate);
    }

    @Override
    public void afterTextChanged(Editable s) {
//        boolean needUpdate = false;
//        if (!TextUtils.isEmpty(s)) {
//            countValue = Integer.valueOf(s.toString());
//            if (countValue <= MIN_VALUE) {
//                countValue = MIN_VALUE;
//                ivMinu.setEnabled(false);
//                ivAdd.setEnabled(true);
//                needUpdate = true;
//                Toast.makeText(mContext, String.format("最少添加%s个数量", MIN_VALUE), Toast.LENGTH_SHORT).show();
//            } else if (countValue >= maxValue) {
//                countValue = maxValue;
//                ivMinu.setEnabled(true);
//                ivAdd.setEnabled(false);
//                needUpdate = true;
//                Toast.makeText(mContext, String.format("最多只能添加%s个数量", maxValue), Toast.LENGTH_SHORT).show();
//
//            } else {
//                ivMinu.setEnabled(true);
//                ivAdd.setEnabled(true);
//            }
//        } else {  //如果编辑框被清空了，直接填1
//            countValue = 1;
//            ivMinu.setEnabled(false);
//            ivAdd.setEnabled(true);
//            needUpdate = true;
//            Toast.makeText(mContext, String.format("最少添加%s个数量", MIN_VALUE), Toast.LENGTH_SHORT).show();
//
//        }
//        changeWord(needUpdate);
    }

    // 定义监听接口
    interface OnChangeCountListener {
        void change(int count);
    }

    // 声明接口变量
    private OnChangeCountListener listener;

    // 提供给外部使用的方法
    public void setOnChangeCountListener(OnChangeCountListener l) {
        listener = l;
    }

}
