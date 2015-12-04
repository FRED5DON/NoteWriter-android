package com.sagosoft.app.android.notewriter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sagosoft.app.android.notewriter.R;
import com.sagosoft.app.android.notewriter.base.BaseAppFragment;
import com.sagosoft.app.android.notewriter.bean.NoteBean;
import com.sagosoft.app.android.notewriter.presenter.NoterPresenter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kenel.app.sagosoft.com.Components.Pull2FreshView.PullToRefreshView;
import kenel.app.sagosoft.com.Components.SectionListView.SectionListView;

/**
 * Created by FRED on 2015/12/4.
 */
public class CategoriesFragment extends BaseAppFragment implements NoterPresenter.NoterPresenterService {

    @InjectView(R.id.categories_fragment_recyclerview)
    RecyclerView categoriesRecyclerView;
    @InjectView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;
    @InjectView(R.id.categories_fragment_sectionlistview)
    SectionListView categoriesListview;
    private int kDELAY_TIMES = 600;
    private NoterPresenter noterPresenter;
    private CategoriesAdapter adapter;

    public static BaseAppFragment getInstance(Bundle bundle) {
        BaseAppFragment baseAppFragment = new CategoriesFragment();
        if (bundle != null) {
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
        View view = inflater.inflate(R.layout.categories_fragment, container, false);
        ButterKnife.inject(this, view);
        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.setRefreshing(false);
                    }
                }, kDELAY_TIMES);
            }
        });
        initListView();
        Handler handler = new Handler();
        noterPresenter = NoterPresenter.getInstance(handler);
        noterPresenter.setDelegate(this);
        noterPresenter.loadAllNotesSheet();
        return view;
    }

    private void initListView() {
        adapter = new CategoriesAdapter(null);
        //TODO 待修改 未做完 需要找一个SectionTableView
//        categoriesListview.setAdapter(adapter);
    }


    @Override
    public void onResponse(List<NoteBean> elements) {
        adapter.setCatogories(elements);
    }



    static class CategoriesAdapter extends BaseAdapter implements SectionIndexer {

        private Item[] sections;

        List<NoteBean> catogories;
        private CategoriesAdapter(List<NoteBean> catogories){
            if(catogories==null){
                this.catogories=new ArrayList<NoteBean>();
            }else{
                this.catogories=catogories;
            }

        }

        public void setCatogories(List<NoteBean> catogories) {
            if(catogories==null){
                return;
            }
            this.catogories = catogories;
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public Object[] getSections() {
            return new Object[0];
        }

        @Override
        public int getPositionForSection(int sectionIndex) {
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }
    }

    static class Item {

        public static final int ITEM = 0;
        public static final int SECTION = 1;

        public final int type;
        public final String text;

        public int sectionPosition;
        public int listPosition;

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }

        @Override public String toString() {
            return text;
        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
