package com.sagosoft.app.android.notewriter.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import kenel.app.sagosoft.com.Components.Pull2FreshView.UIMessageView;

/**
 * Created by FRED on 2015/12/2.
 */
public class BaseAppCompatActivity extends AppCompatActivity {


    /**
     * 新启动意图
     * @param cls
     * @return
     */
    protected Intent newIntent(Class cls){
        Intent intent=new Intent();
        intent.setClass(this,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 清除栈顶的活动意图
     * @param cls
     * @return
     */
    protected Intent newSingleIntent(Class cls){
        Intent intent=new Intent();
        intent.setClass(this,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }


    protected void showMessage(String msg){
        UIMessageView.showMessage(this,msg);
    }

}
