package com.mobway.pelemedicalcenter.adapters;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Insurance;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterInsurance extends RecyclerView.Adapter<RVAdapterInsurance.ViewHolder> {

    private List<Insurance> mList;
    private Insurance mSelectedInsurance;
    private AlertDialog mDialog;
    private Button mButtonInsurance;
    private Switch mSwitchExpress;

    public Insurance getSelectedInsurance() {
        return mSelectedInsurance;
    }

    public void delegateDialog(AlertDialog dialog) {
        mDialog = dialog;
    }

    public void delegateButton(Button button) {
        mButtonInsurance = button;
    }

    public void delegateSwitchExpress(Switch switchExp) {
        mSwitchExpress = switchExp;
    }

    public RVAdapterInsurance(List<Insurance> list) {
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
        final Insurance model = mList.get(index);
        holder.tvDescription.setText(model.getDescription());
        holder.imgIcon.setImageDrawable(model.getDescription().equals("PARTICULAR") ? holder.imgIcon.getContext().getDrawable(R.drawable.ic_monetization) : holder.imgIcon.getContext().getDrawable(R.drawable.ic_credit_card));

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedInsurance = model;
                if (mButtonInsurance != null) {
                    mButtonInsurance.setText(mSelectedInsurance.getDescription());
                    if (mSelectedInsurance.getDescription().equals("PARTICULAR")) {
                        if (mSwitchExpress.isChecked()) {
                            mSwitchExpress.setChecked(false);
                        }
                        mSwitchExpress.setEnabled(false);
                    } else {
                        mSwitchExpress.setEnabled(true);
                    }
                }

                if (mDialog != null) {
                    if (mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                }
            }
        });
    }

    public void setFilter(List<Insurance> list) {
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
        ImageView imgIcon;
        View content;

        ViewHolder(View view) {
            super(view);
            tvDescription = view.findViewById(R.id.tv_insurance_name);
            imgIcon = view.findViewById(R.id.imageView2);
            content = view;
        }

    }
}