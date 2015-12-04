package com.sagosoft.app.android.notewriter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagosoft.app.android.notewriter.R;
import com.sagosoft.app.android.notewriter.base.BaseAppFragment;

/**
 * Created by FRED_anjujia on 2015/12/2.
 */
public class GlobalSearchFragment extends BaseAppFragment {

    public static BaseAppFragment getInstance(Bundle bundle) {
        BaseAppFragment baseAppFragment = new GlobalSearchFragment();
        if(bundle!=null){
            baseAppFragment.setArguments(bundle);
        }
        return baseAppFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_fragment_left,container,false);
        return view;
    }



}
