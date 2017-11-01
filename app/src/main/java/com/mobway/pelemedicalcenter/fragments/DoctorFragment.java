package com.mobway.pelemedicalcenter.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.adapters.RVAdapterDoctor;
import com.mobway.pelemedicalcenter.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorFragment extends android.support.v4.app.Fragment {

    private OnFragmentInteractionListener mListener;

    public DoctorFragment() {
        // Required empty public constructor
    }

    public static DoctorFragment newInstance(String param1, String param2) {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        // MOCK ONLY
        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Doctor d1 = new Doctor();
            d1.setName("Arthur Cordova Stapassoli");
            doctors.add(d1);
        }
        // MOCK ONLY

        RVAdapterDoctor adapterDoctor = new RVAdapterDoctor(doctors);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_doctor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterDoctor);

        return view;
    }

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
