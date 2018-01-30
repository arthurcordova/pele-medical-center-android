package com.mobway.pelemedicalcenter.adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobway.pelemedicalcenter.BR;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.controllers.NotificationController;
import com.mobway.pelemedicalcenter.models.NotificationResponse;

import java.util.List;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class RVAdapterNotification extends RecyclerView.Adapter<RVAdapterNotification.ViewHolder> {

    private List<NotificationResponse> mList;
    public FragmentManager mFragmentManager;
    public Context context;
    public Activity activity;
    public NotificationController controller;
//    public BottomSheetBehavior sheetBehavior;
//    public LinearLayout layoutBottomSheet;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleBottomSheetFragment(notif);
//                BottomSheetDialogFragment bottomSheetFragment = new BottomSheetDialogFragment();
//                bottomSheetFragment.show(mFragmentManager, bottomSheetFragment.getTag());
            }
        });

    }

//    public void toggleBottomSheet() {
//        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        } else {
//            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        }
//    }

    public void toggleBottomSheetFragment(final NotificationResponse nofication) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bottom_sheet_notification, null);

        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(view);
        dialog.show();

        view.findViewById(R.id.line_set_as_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.setAsRead(nofication.uuid, dialog);
            }
        });
        view.findViewById(R.id.line_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.delete(nofication.uuid, dialog);
            }
        });


//        BottomSheetDialogFragment bottomSheetFragment = new BottomSheetDialogFragment();
//        bottomSheetFragment.show(mFragmentManager, bottomSheetFragment.getTag());
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