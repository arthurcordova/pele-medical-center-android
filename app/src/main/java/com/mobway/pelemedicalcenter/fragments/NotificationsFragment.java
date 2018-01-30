package com.mobway.pelemedicalcenter.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.adapters.RVAdapterNotification;
import com.mobway.pelemedicalcenter.controllers.NotificationController;
import com.mobway.pelemedicalcenter.models.NoticationData;
import com.mobway.pelemedicalcenter.models.NotificationResponse;

import java.util.ArrayList;

public class NotificationsFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ViewDataBinding notificationsFragment;

    BottomSheetBehavior sheetBehavior;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final NoticationData noticationData = new NoticationData();
        noticationData.notification.set(new ArrayList<NotificationResponse>());

//        notificationsFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
//        View root = notificationsFragment.getRoot();
//        notificationsFragment.setVariable(1, noticationData);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        RVAdapterNotification adapterNotification = new RVAdapterNotification(noticationData.notification.get());
        adapterNotification.mFragmentManager = getFragmentManager();
        adapterNotification.context = getContext();
        adapterNotification.activity = getActivity();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterNotification);

        NotificationController controller = new NotificationController(getActivity());
        adapterNotification.controller = controller;

        controller.delegateAdapter(adapterNotification).getNotifications();

//        root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                noticationData.celsius.set(String.valueOf(Integer.parseInt(noticationData.celsius.get()) + 10) );
//
//            }
//        });

//        LinearLayout layoutBottomSheet = root.findViewById(R.id.bottom_sheet);
//        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//
//        adapterNotification.sheetBehavior = sheetBehavior;
//        adapterNotification.layoutBottomSheet = layoutBottomSheet;

//        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED: {
//
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_COLLAPSED: {
//
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });
//

        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
