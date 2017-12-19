package com.mobway.pelemedicalcenter.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.DateTimeActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterPatient extends RecyclerView.Adapter<RVAdapterPatient.ViewHolder> {

    private List<Patient> mList;
    private List<ViewHolder> mListHolders = new ArrayList<>();
    private Patient mSelectedPatient;

    public Patient getSelectedPatient() {
        return mSelectedPatient;
    }

    public RVAdapterPatient(List<Patient> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_patient, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final Patient model = mList.get(index);
        mListHolders.add(holder);

        holder.tvName.setText(model.getName());
        holder.tvGender.setText(model.getGender());
        holder.tvBirth.setText(model.getBirth());

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ViewHolder hold : mListHolders) {
                    hold.setChecked(false, hold.content);
                }
                mSelectedPatient = model;
                holder.setChecked(true, view);
            }
        });

    }

    public void setFilter(List<Patient> list) {
//        if (!mList.isEmpty()) {
//            mList.clear();
//        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvGender;
        TextView tvBirth;
        View content;
        View checked;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvGender = view.findViewById(R.id.tv_gender);
            tvBirth = view.findViewById(R.id.tv_birth);
            checked = view.findViewById(R.id.img_checked);
            content = view;
        }

        public void setChecked(boolean value, View v) {
            if (value) {
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.lighterGray));
                checked.setVisibility(View.VISIBLE);

            } else {
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.white));
                checked.setVisibility(View.GONE);

            }
        }
    }
}