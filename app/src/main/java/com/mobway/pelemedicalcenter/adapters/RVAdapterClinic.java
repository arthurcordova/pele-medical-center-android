package com.mobway.pelemedicalcenter.adapters;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.ClinicResponse;
import com.mobway.pelemedicalcenter.models.Filter;
import com.mobway.pelemedicalcenter.utils.FilterManager;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterClinic extends RecyclerView.Adapter<RVAdapterClinic.ViewHolder> {

    private List<ClinicResponse> mList;
    private ClinicResponse mSelectedClinic;
    private AlertDialog mDialog;
    private Button mButtonClinic;
    private FilterManager mFilterManager;

    public ClinicResponse getSelectedInsurance() {
        return mSelectedClinic;
    }

    public void delegateDialog(AlertDialog dialog) {
        mDialog = dialog;
    }

    public void delegateButton(Button button) {
        mButtonClinic = button;
    }

    public RVAdapterClinic(List<ClinicResponse> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final ClinicResponse model = mList.get(index);
        holder.tvDescription.setText(model.getName());

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedClinic = model;
                if (mButtonClinic != null) {
                    mButtonClinic.setText(mSelectedClinic.getName());

                    if (mFilterManager == null) {
                        mFilterManager = new FilterManager(view.getContext());
                    }

                    Filter filterPlace = new Filter();
                    filterPlace.setClinic(mSelectedClinic.getName());

                    mFilterManager.save(filterPlace);
                }

                if (mDialog != null) {
                    if (mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                }
            }
        });
    }

    public void setFilter(List<ClinicResponse> list) {
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

        TextView tvDescription;
        View content;

        ViewHolder(View view) {
            super(view);
            tvDescription = view.findViewById(R.id.tv_city_name);
            content = view;
        }
    }
}