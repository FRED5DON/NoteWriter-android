package com.sagosoft.app.android.notewriter.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sagosoft.app.android.notewriter.R;
import com.sagosoft.app.android.notewriter.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by FRED on 2015/12/11.
 */
public class CategoriesAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private final LayoutInflater inflater;
    List<NoteBean> catogories;
    private Context context;

    public CategoriesAdapter(Context context, List<NoteBean> catogories) {
        this.context = context;
        if (catogories == null) {
            this.catogories = new ArrayList<NoteBean>();
        } else {
            this.catogories = catogories;
        }
        this.inflater=LayoutInflater.from(context);

    }

    public void setCatogories(List<NoteBean> catogories) {
        if (catogories == null) {
            return;
        }
        this.catogories = catogories;
        notifyDataSetChanged();
    }

    @Override
    public long getHeaderId(int position) {
        return this.catogories.get(position).getGroupId();
    }

    @Override
    public int getCount() {
        return this.catogories.size();
    }

    @Override
    public NoteBean getItem(int position) {
        return this.catogories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder ic = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.categories_item, null);
            ic = new ViewHolder(convertView);
            convertView.setTag(ic);
        } else {
            ic = (ViewHolder) convertView.getTag();
        }
        NoteBean item = getItem(position);
        ic.titleText.setText(item.getName());
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadCacher ic = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.categories_item_head, null);
            ic = new HeadCacher(convertView);
            convertView.setTag(ic);
        } else {
            ic = (HeadCacher) convertView.getTag();
        }
        NoteBean item = getItem(position);
        ic.titleHeadText.setText(item.getGroupname());
        return convertView;
    }


    class HeadCacher {
        @InjectView(R.id.categories_item_head_text)
        TextView titleHeadText;

        HeadCacher(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class ViewHolder {
        @InjectView(R.id.categories_item_text)
        TextView titleText;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}