package com.mobway.pelemedicalcenter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Filter;
import com.mobway.pelemedicalcenter.models.Specialty;
import com.mobway.pelemedicalcenter.utils.FilterManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterSpecialty extends RecyclerView.Adapter<RVAdapterSpecialty.ViewHolder> {

    private List<Specialty> mList;
    private List<Specialty> mSelectedSpecialties = new ArrayList<>();
    private List<RVAdapterSpecialty.ViewHolder> mHolders = new ArrayList<>();
    public Specialty selectedSpecialty;

    public List<Specialty> getSelectedSpecialties() {
        return mSelectedSpecialties;
    }

    public RVAdapterSpecialty(List<Specialty> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_specialty, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final Specialty model = mList.get(index);
        mHolders.add(holder);
        holder.tvName.setText(model.getName());
//        holder.viewBack.setBackground(model.isSelected() ? holder.viewBack.getContext().getDrawable(R.drawable.ic_selected) : holder.viewBack.getContext().getDrawable(R.drawable.ic_unselected));

        holder.viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ViewHolder h : mHolders) {
                    h.setChecked(false, h.viewBack);
                }
                holder.setChecked(true, holder.viewBack);
                selectedSpecialty = model;
                Filter filter = new Filter();
                filter.setSpecialtyID(Integer.parseInt(model.getUuid()));

                FilterManager fm = new FilterManager(view.getContext());
                fm.save(filter);
            }
        });
    }

    public void setFilter(List<Specialty> list) {
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
        View viewBack;
        boolean checked = false;

        ViewHolder(View view) {
            super(view);
            viewBack = view.findViewById(R.id.img_background);
            tvName = view.findViewById(R.id.tv_name);
        }

        public void setChecked(boolean value, View v) {
            if (value) {
                v.setBackground(v.getContext().getDrawable(R.drawable.ic_selected));
            } else {
                v.setBackground(v.getContext().getDrawable(R.drawable.ic_unselected));
            }
        }

    }
}