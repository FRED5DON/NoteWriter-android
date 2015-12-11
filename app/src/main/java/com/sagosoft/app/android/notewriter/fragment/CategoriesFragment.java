package com.sagosoft.app.android.notewriter.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagosoft.app.android.notewriter.R;
import com.sagosoft.app.android.notewriter.base.BaseAppFragment;
import com.sagosoft.app.android.notewriter.bean.NoteBean;
import com.sagosoft.app.android.notewriter.fragment.adapter.CategoriesAdapter;
import com.sagosoft.app.android.notewriter.presenter.NoterPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kenel.app.sagosoft.com.Components.Pull2FreshView.PullToRefreshView;
import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by FRED on 2015/12/4.
 */
public class CategoriesFragment extends BaseAppFragment implements NoterPresenter.NoterPresenterService {

    @InjectView(R.id.categories_fragment_recyclerview)
    RecyclerView categoriesRecyclerView;
    @InjectView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;
    @InjectView(R.id.categories_fragment_sticklistview)
    StickyListHeadersListView categoriesListview;
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
        adapter = new CategoriesAdapter(getActivity(), null);
        categoriesListview.setAdapter(adapter);
    }

    @Override
    public void onResponse(List<NoteBean> elements) {
        adapter.setCatogories(elements);
    }

    @Override
    public void onDestroyView() {
//        noterPresenter.setDelegate(null);
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
