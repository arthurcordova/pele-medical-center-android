package com.mobway.pelemedicalcenter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterSchedule extends RecyclerView.Adapter<RVAdapterSchedule.ViewHolder> {

    private List<Schedule> mList;

    public RVAdapterSchedule(List<Schedule> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_schedule, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final Schedule model = mList.get(index);

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobwayDialog.dialogDetailSchedule(view.getContext(), model);
            }
        });

    }

    public void setFilter(List<Schedule> list) {
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

        TextView tvName;
        View content;

        ViewHolder(View view) {
            super(view);
            content = view;
//            tvName = view.findViewById(R.id.tv_name);
        }
    }
}