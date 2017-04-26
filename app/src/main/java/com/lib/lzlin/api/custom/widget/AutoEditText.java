package com.lib.lzlin.api.custom.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import static android.icu.lang.UProperty.WHITE_SPACE;

/**
 * 项目名称:  Lib-lz
 * <p>
 * 类的描述: 自动化编辑框, 实现EditText 每输入N位, 自动添加 XXX 作为间隔符
 *          参考资料: https://github.com/smuyyh/BankCardFormat/blob/master/library/src/main/java/com/example/library/BandCardEditText.java
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/29 18:05
 * 修改人:
 * 修改备注:
 */

public class AutoEditText extends EditText{

    private boolean shouldStopChange = false; // 是否停止改变
    private final String AUTO_CHACTER = " "; // 间隔符, 自动填充

    private AutoListener listener; // 自动化监听事件

    private int distance  = 4; // 间隔长度
    private int offset  = 0; // 间隔长度, 偏移量

    public AutoEditText(Context context) {
        super(context);
    }

    public AutoEditText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public AutoEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        format(getText());
        shouldStopChange = false;
        setFocusable(true);
        setEnabled(true);
        setFocusableInTouchMode(true);
        addTextChangedListener(new AutoTextWatcher());
    }

    class AutoTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable editable) {
            format(editable);
        }
    }


    private void format(Editable editable) {
        if (shouldStopChange) {
            shouldStopChange = false;
            return;
        }

        shouldStopChange = true;

        String str = editable.toString().trim().replaceAll(AUTO_CHACTER, "");
        int len = str.length();
        int courPos;

        StringBuilder builder = new StringBuilder();
        for (int i = offset > 0 ? offset : 0; i < len; i++) { // 当且仅当有内容时, 才会调用
            builder.append(str.charAt(i));

            if ((i + 1) % distance == 0) { // 间断添加
                builder.append(WHITE_SPACE);
            }
        }
        courPos = builder.length();
        setText(builder.toString());
        setSelection(courPos);
    }

    public String getRealText() {
        return getText().toString().trim().replaceAll(AUTO_CHACTER, "");
    }

    // TODO lz 根据实际需要添加监听事件
    public interface AutoListener {

    }

    public boolean isShouldStopChange() {
        return shouldStopChange;
    }

    public void setShouldStopChange(boolean shouldStopChange) {
        this.shouldStopChange = shouldStopChange;
    }

    public String getAUTO_CHACTER() {
        return AUTO_CHACTER;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
