package com.lib.lzlin.api.custom.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 用于记录EditText 的一些特殊用法 / 不常用方法
 * 创建人: Administrator
 * 创建时间:  2017/3/23 13:59
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class EditText {
    /**
     * 描述 ：文字改变监听事件
     * 作者  ： lz - Administrator
     * 时间  ： 2017/3/23  14:05
     *
     * @describe
     *      用法: mEdt.addTextChangedListener(new MyTextWatcher());  // 设置文本监听
     */
    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO lz 处理文字改变事件
            if (s == null || s.length() == 0) {
            } else {
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * 描述  ：键盘输入监听事件
     * 作者  ： Administrator
     * 时间  ： 2017/3/23  14:04
     *
     * @describe
     *      用法: mEdt.setOnEditorActionListener(new MyOnEditorActionListener());  // 编辑完之后点击软键盘上的回车键才会触发
     */
    private class MyOnEditorActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            //当actionId == XX_SEND 或者 XX_DONE时都触发
            //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                    && KeyEvent.ACTION_DOWN == event.getAction())) {
                // TODO lz 处理键盘输入事件
            }
            return false;
        }
    }
}
