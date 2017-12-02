package com.mobway.pelemedicalcenter.adapters;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Consult;
import com.mobway.pelemedicalcenter.models.Insurance;
import com.mobway.pelemedicalcenter.models.Schedule;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterConsult extends RecyclerView.Adapter<RVAdapterConsult.ViewHolder> {

    private List<Consult> mList;
    private Consult mSelectedConsult;
    private AlertDialog mDialog;
    private Button mButtonConsult;
    private Schedule mParentSchedule;

    public Consult getSelectedConsult() {
        return mSelectedConsult;
    }

    public void delegateDialog(AlertDialog dialog){
        mDialog = dialog;
    }

    public void delegateButton(Button button){
        mButtonConsult = button;
    }

    public void delegateSchedule(Schedule schedule){
        mParentSchedule = schedule;
    }

    public RVAdapterConsult(List<Consult> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_insurance, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final Consult model = mList.get(index);
        holder.tvDescription.setText(model.getDescription());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mSelectedConsult = model;
                mParentSchedule.setType(model);
                if (mButtonConsult != null) {
                    mButtonConsult.setText(model.getDescription());
                }

                if (mDialog != null) {
                    if (mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                }
            }
        });
    }

    public void setFilter(List<Consult> list) {
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
            tvDescription = view.findViewById(R.id.tv_insurance_name);

            content = view;
        }

    }
}