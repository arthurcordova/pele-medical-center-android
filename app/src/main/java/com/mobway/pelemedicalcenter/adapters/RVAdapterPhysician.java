package com.mobway.pelemedicalcenter.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.DateTimeActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.Schedule;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterPhysician extends RecyclerView.Adapter<RVAdapterPhysician.ViewHolder> {

    private List<Physician> mList;

    public RVAdapterPhysician(List<Physician> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_doctor, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final Physician model = mList.get(index);

        holder.tvName.setText(model.getName());
        holder.tvSpecialty.setText(model.getSpecialty());
        holder.tvNextdate.setText(model.getNextFreeSchedule()!=null ? "Disponível em: \n"+model.getNextFreeSchedule():"");
        holder.onClick(model);

    }

    public void setFilter(List<Physician> list) {
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
        TextView tvSpecialty;
        TextView tvNextdate;
        View content;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvSpecialty = view.findViewById(R.id.tv_specialty);
            tvNextdate = view.findViewById(R.id.tv_crm);

            content = view;
        }

        public void onClick(final Physician physician) {
            final Schedule schedule = new Schedule();
            schedule.setPhysician(physician);

            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(view.getContext(), DateTimeActivity.class);
                    it.putExtra("schedule", schedule);
                    view.getContext().startActivity(it);
                }
            });
        }
    }
}