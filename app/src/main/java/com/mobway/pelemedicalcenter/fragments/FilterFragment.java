package com.mobway.pelemedicalcenter.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.mobway.pelemedicalcenter.MainActivity;
import com.mobway.pelemedicalcenter.PaymentActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.adapters.RVAdapterSpecialty;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(String param1, String param2) {
        FilterFragment fragment = new FilterFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filter, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rv_specialty);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), list.size()));
        recyclerView.setAdapter(new RVAdapterSpecialty(list));

        Switch switchEmergency = root.findViewById(R.id.switch_emergency);
        Switch switchPrivate = root.findViewById(R.id.switch_private);
        final Button buttonPlace = root.findViewById(R.id.button_place);


        root.findViewById(R.id.button_apply_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), MainActivity.class);
                startActivity(it);
            }
        });
        buttonPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = getLayoutInflater().inflate(R.layout.dialog_select_place, null);
                builder.setView(v);
                final AlertDialog alert = builder.create();
                v.findViewById(R.id.line_fortaleza).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonPlace.setText("Fortaleza");
                        alert.dismiss();
                    }
                });
                v.findViewById(R.id.line_maceio).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonPlace.setText("Maceió");
                        alert.dismiss();
                    }
                });

                alert.show();
            }
        });


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
