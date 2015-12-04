package com.sagosoft.app.android.notewriter.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

import kenel.app.sagosoft.com.Components.Pull2FreshView.UIMessageView;

/**
 * Created by FRED_anjujia on 2015/12/2.
 */
public class BaseAppFragment extends Fragment {


    /**
     * 新启动意图
     *
     * @param cls
     * @return
     */
    protected Intent newIntent(Class cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 清除栈顶的活动意图
     *
     * @param cls
     * @return
     */
    protected Intent newSingleIntent(Class cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }


    protected void showMessage(String msg) {
        UIMessageView.showMessage(getActivity(), msg);
    }
}
