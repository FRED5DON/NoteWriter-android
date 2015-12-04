package com.sagosoft.app.android.notewriter.presenter;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.sagosoft.app.android.notewriter.bean.NoteBean;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by FRED_anjujia on 2015/12/4.
 */
public class NoterPresenter {
    private static NoterPresenter noterPresenter;
    private Handler delegateHandler;

    public static NoterPresenter getInstance(Handler delegateHandler) {
        if (noterPresenter == null) {
            noterPresenter = new NoterPresenter();
            noterPresenter.delegateHandler = delegateHandler;
        }
        return noterPresenter;
    }

    public void setDelegate(NoterPresenterService delegate) {
        this.delegate = delegate;
    }


    public void loadAllNotesSheet() {
        //测试代码
        String nodes = "[{groupId:\"1000\",groupname:\"本地随笔\",childs:[{groupId:\"100001\",groupname:\"一年心情\",id:1011,name:\"笔记1\",contentUri:\"\",content:\"\",logo:\"\",createTime:\"2015-12-0117:29:02\",lastModifyTime:\"2015-12-0417:28:58\",noteType:2,SourceType:2,childs:[]},{groupId:\"100001\",groupname:\"一年心情\",id:1011,name:\"笔记2\",contentUri:\"\",content:\"\",logo:\"\",createTime:\"2015-12-0117:29:02\",lastModifyTime:\"2015-12-0417:28:58\",noteType:2,SourceType:2,childs:[]}]},{groupId:\"1002\",groupname:\"云笔记\",childs:[{groupId:\"100032\",groupname:\"待办事项\",id:1011,name:\"笔记1\",contentUri:\"\",content:\"\",logo:\"\",createTime:\"2015-12-0117:29:02\",lastModifyTime:\"2015-12-0417:28:58\",noteType:2,SourceType:2,childs:[]}]}]";

        final List<NoteBean> elements = JSON.parseArray(nodes, NoteBean.class);
        /*Dictionary<String,T> dict=new Hashtable<String,T>();
        for (NoteBean bean : elements){
        }*/
        if (delegate != null) {
            noterPresenter.delegateHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    delegate.onResponse(elements);
                }
            }, 200);

        }
    }

    private NoterPresenterService delegate;

    public interface NoterPresenterService {
        void onResponse(List<NoteBean> elements);
    }

}
