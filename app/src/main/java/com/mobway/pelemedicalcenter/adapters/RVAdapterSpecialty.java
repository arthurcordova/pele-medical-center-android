package com.mobway.pelemedicalcenter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Physician;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterSpecialty extends RecyclerView.Adapter<RVAdapterSpecialty.ViewHolder> {

    private List<String> mList;

    public RVAdapterSpecialty(List<String> list) {
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
        final String model = mList.get(index);

//        holder.tvName.setText(model.getName());
//        Picasso.with(holder.imgCategory.getContext())
//                .load(model.getImagem())
//                .into(holder.imgCategory);
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

        ViewHolder(View view) {
            super(view);



        }
    }
}