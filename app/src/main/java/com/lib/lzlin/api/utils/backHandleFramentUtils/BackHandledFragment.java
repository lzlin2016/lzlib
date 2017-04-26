package com.lib.lzlin.api.utils.backHandleFramentUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
 *          FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
 *          如果没有Fragment消息时FragmentActivity自己才会消费该事件
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public abstract class BackHandledFragment extends Fragment {
    protected BackHandledInterface mBackHandledInterface;

    public abstract boolean onBackPressed();

    public BackHandledFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(getActivity() instanceof BackHandledInterface)){
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        }else{
            this.mBackHandledInterface = (BackHandledInterface)getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //告诉FragmentActivity，当前Fragment在栈顶
        mBackHandledInterface.setSelectedFragment(this);
    }

}
