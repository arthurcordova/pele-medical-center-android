package com.mobway.pelemedicalcenter.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobway.pelemedicalcenter.BR;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.models.NotificationResponse;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterNotification extends RecyclerView.Adapter<RVAdapterNotification.ViewHolder> {

    private List<NotificationResponse> mList;

    public RVAdapterNotification(List<NotificationResponse> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_notification, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        final NotificationResponse notif = mList.get(index);

        holder.getBinding().setVariable(BR.notificBind, notif);
        holder.getBinding().executePendingBindings();

    }

    public void setFilter(List<NotificationResponse> list) {
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

        private ViewDataBinding binding;

        ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {

            return binding;

        }

    }
}