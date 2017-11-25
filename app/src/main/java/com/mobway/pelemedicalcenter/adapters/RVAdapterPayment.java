package com.mobway.pelemedicalcenter.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.DateTimeActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Physician;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterPayment extends RecyclerView.Adapter<RVAdapterPayment.ViewHolder> {

    private List<String> mList;
    private List<ViewHolder> mListHolders = new ArrayList<>();
    private String mSelectedPayment;

    public String getSelectedPayment() {
        return mSelectedPayment;
    }

    public RVAdapterPayment(List<String> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_payment, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final String paymentName = mList.get(index);
        mListHolders.add(holder);
        holder.tvName.setText(paymentName);

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ViewHolder hold : mListHolders) {
                    hold.setChecked(false, hold.mImgSelect);
                }
                mSelectedPayment = paymentName;
                holder.setChecked(true, holder.mImgSelect);
            }
        });

    }

    public void setFilter(List<String> list) {
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
        ImageView mImgSelect;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.label_payment_name);
            mImgSelect = view.findViewById(R.id.img_check);
            content = view;
        }

        public void setChecked(boolean value, ImageView img) {
            if (value) {
                img.setImageDrawable(img.getContext().getDrawable(R.drawable.ic_checked));

            } else {
                img.setImageDrawable(img.getContext().getDrawable(R.drawable.ic_unchecked));

            }
        }

    }
}