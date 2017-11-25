package com.mobway.pelemedicalcenter.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.DateTimeActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterTime extends RecyclerView.Adapter<RVAdapterTime.ViewHolder> {

    private List<Time> mList;
    private List<ViewHolder> mListHolders = new ArrayList<>();
    private Time mSelectedTime;

    public Time getSelectedTime() {
        return mSelectedTime;
    }

    public RVAdapterTime(List<Time> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_time, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final Time time = mList.get(index);
        mListHolders.add(holder);
        holder.tvTime.setText(time.getHour());
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ViewHolder hold : mListHolders) {
                    hold.setChecked(false, hold.tvTime);
                }
                time.setSelected(true);
                mSelectedTime = time;
                holder.setChecked(time.isSelected(), view);
            }
        });

    }

    public void setFilter(List<Time> list) {
        if (!mList.isEmpty()) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;
        View content;

        ViewHolder(View view) {
            super(view);
            tvTime = view.findViewById(R.id.tv_time);
            content = view;
        }

        public void setChecked(boolean value, View v) {
            if (value) {
                v.setBackground(v.getContext().getDrawable(R.drawable.bg_radius_green));
                ((TextView)v).setTextColor(v.getContext().getResources().getColor(R.color.white));
            } else {
                v.setBackground(v.getContext().getDrawable(R.drawable.bg_radius_white));
                ((TextView)v).setTextColor(v.getContext().getResources().getColor(R.color.textPrimaryColor));
            }
        }
    }
}