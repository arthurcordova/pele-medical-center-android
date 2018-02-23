package com.mobway.pelemedicalcenter.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.mobway.pelemedicalcenter.MainActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.adapters.RVAdapterCity;
import com.mobway.pelemedicalcenter.adapters.RVAdapterClinic;
import com.mobway.pelemedicalcenter.adapters.RVAdapterConsult;
import com.mobway.pelemedicalcenter.adapters.RVAdapterInsurance;
import com.mobway.pelemedicalcenter.adapters.RVAdapterSpecialty;
import com.mobway.pelemedicalcenter.controllers.CityController;
import com.mobway.pelemedicalcenter.controllers.ClinicController;
import com.mobway.pelemedicalcenter.controllers.ConsultController;
import com.mobway.pelemedicalcenter.controllers.InsuranceController;
import com.mobway.pelemedicalcenter.controllers.SpecialtyController;
import com.mobway.pelemedicalcenter.fcm.PeleMedicalCenterFirebaseInstanceIdService;
import com.mobway.pelemedicalcenter.models.CityResponse;
import com.mobway.pelemedicalcenter.models.ClinicResponse;
import com.mobway.pelemedicalcenter.models.Consult;
import com.mobway.pelemedicalcenter.models.Filter;
import com.mobway.pelemedicalcenter.models.Insurance;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.Specialty;
import com.mobway.pelemedicalcenter.utils.FilterManager;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private FilterManager mFilterManager;
    private SpecialtyController mSpecialtyController;

    Button buttonConsultType;
    Button buttonConsultTypeID;
    Button buttonInsurance;
    Switch switchEmergency;

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

        mFilterManager = new FilterManager(getContext());
        Filter filterSaved = mFilterManager.getFilters();

        RecyclerView recyclerView = root.findViewById(R.id.rv_specialty);

        List<Specialty> list = new ArrayList<>();

        final RVAdapterSpecialty adapter = new RVAdapterSpecialty(list);

        mSpecialtyController = new SpecialtyController(getActivity());
        mSpecialtyController.delegateRecyclerView(recyclerView);
        mSpecialtyController.delegateAdapter(adapter);
        mSpecialtyController.getSpecialties(mFilterManager.getFilters().getPlaceID());

        switchEmergency = root.findViewById(R.id.switch_emergency);
        final Button buttonCity = root.findViewById(R.id.button_city);
        final Button buttonPlace = root.findViewById(R.id.button_place);
        buttonInsurance = root.findViewById(R.id.button_insurance);
        buttonConsultType = root.findViewById(R.id.button_consult_type);
        buttonConsultTypeID = root.findViewById(R.id.button_consult_type_id);

        switchEmergency.setChecked(filterSaved.getEmergency());
        buttonCity.setText(filterSaved.getPlace());
        buttonPlace.setText(filterSaved.getClinic());
        buttonInsurance.setText(filterSaved.getInsurance());
        buttonConsultType.setText(filterSaved.getConsult().getDescription());
        buttonConsultTypeID.setText(filterSaved.getConsult().getUuid());

        validateConsultType();

        root.findViewById(R.id.button_apply_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonConsultType.getText().toString().equals("")) {
                    final AlertDialog.Builder b =
                            new AlertDialog.Builder(getActivity());
                    b.setTitle("Campo obrigatório");
                    b.setMessage("Por favor, selecione o tipo da consulta!");
                    b.setPositiveButton("Fechar", null);
                    b.setNegativeButton("", null);
                    b.show();
                    return;
                }

                List<Specialty> specialties = adapter.getSelectedSpecialties();

                Filter filter = new Filter();
                filter.setPlace(buttonCity.getText().toString());
                filter.setEmergency(switchEmergency.isChecked());
                filter.setInsurance(buttonInsurance.getText().toString());
                filter.setClinic(buttonPlace.getText().toString());
//                filter.setPrivateSchedule(switchPrivate.isChecked());
                filter.setSpecialties(specialties);
                filter.setConsult(new Consult(buttonConsultTypeID.getText().toString(), buttonConsultType.getText().toString()));
                mFilterManager.save(filter);

                Intent it = new Intent(getContext(), MainActivity.class);
                startActivity(it);

            }
        });
        buttonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = getLayoutInflater().inflate(R.layout.dialog_select_city, null);
                builder.setView(v);
                final AlertDialog alert = builder.create();

                RVAdapterCity adapterCity = new RVAdapterCity(new ArrayList<CityResponse>());
                adapterCity.delegateDialog(alert);
                adapterCity.delegateButton(buttonCity);
                adapterCity.delegateSpecialtyController(mSpecialtyController);

                RecyclerView rvCities = v.findViewById(R.id.recycler_view_cities);
                rvCities.setLayoutManager(new LinearLayoutManager(getContext()));
                rvCities.setAdapter(adapterCity);

                alert.show();

                CityController cityController = new CityController(getActivity()).delegateAdapter(adapterCity);
                cityController.getCities(alert.getContext());
            }
        });
        buttonPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = getLayoutInflater().inflate(R.layout.dialog_select_place, null);
                builder.setView(v);
                final AlertDialog alert = builder.create();

                Filter filter = mFilterManager.getFilters();

                RVAdapterClinic adapterClinic = new RVAdapterClinic(new ArrayList<ClinicResponse>());
                adapterClinic.delegateDialog(alert);
                adapterClinic.delegateButton(buttonPlace);

                RecyclerView rvClinics = v.findViewById(R.id.recycler_view_clinics);
                rvClinics.setLayoutManager(new LinearLayoutManager(getContext()));
                rvClinics.setLayoutManager(new LinearLayoutManager(getContext()));
                rvClinics.setAdapter(adapterClinic);

                alert.show();

                ClinicController cityController = new ClinicController(getActivity()).delegateAdapter(adapterClinic);
                cityController.getClinics(filter.getPlaceID(), filter.getSpecialtyID(), alert.getContext());
            }
        });
        buttonInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = getLayoutInflater().inflate(R.layout.dialog_select_insurance, null);
                builder.setView(v);
                final AlertDialog alert = builder.create();

                RVAdapterInsurance adapterInsurance = new RVAdapterInsurance(new ArrayList<Insurance>());
                adapterInsurance.delegateDialog(alert);
                adapterInsurance.delegateButton(buttonInsurance);
                adapterInsurance.delegateSwitchExpress(switchEmergency);

                RecyclerView recyclerViewInsurance = v.findViewById(R.id.recycler_view_insurance);
                recyclerViewInsurance.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewInsurance.setAdapter(adapterInsurance);

                alert.show();

                InsuranceController insuranceController = new InsuranceController(getActivity()).delegateAdapter(adapterInsurance);
                insuranceController.getInsurances(mFilterManager.getFilters().getPlaceID(), alert.getContext());
            }
        });
        buttonConsultType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = getLayoutInflater().inflate(R.layout.dialog_select_consult_type, null);
                builder.setView(v);
                final AlertDialog alert = builder.create();

                RVAdapterConsult adapterConsult = new RVAdapterConsult(new ArrayList<Consult>());
                adapterConsult.delegateDialog(alert);
                adapterConsult.delegateButton(buttonConsultType);
                adapterConsult.delegateButtonID(buttonConsultTypeID);
                adapterConsult.delegateSchedule(new Schedule());
                adapterConsult.delegateFragment(FilterFragment.this);

                RecyclerView recyclerViewInsurance = v.findViewById(R.id.recycler_view_consult_type);
                recyclerViewInsurance.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewInsurance.setAdapter(adapterConsult);

                alert.show();

                ConsultController consultController = new ConsultController(getActivity()).delegateAdapter(adapterConsult);
                consultController.getTypes(mFilterManager.getFilters().getPlaceID(), alert.getContext());
            }
        });

        new PeleMedicalCenterFirebaseInstanceIdService(getContext()).onTokenRefresh();

        return root;
    }

    public void
    validateConsultType() {
        if (buttonConsultType.getText().toString().equals("CONSULTA PARTICULAR")) {
            buttonInsurance.setText("");
            switchEmergency.setChecked(false);

            buttonInsurance.setEnabled(false);
            switchEmergency.setEnabled(false);
        } else {
            buttonInsurance.setEnabled(true);
            switchEmergency.setEnabled(true);
        }
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
