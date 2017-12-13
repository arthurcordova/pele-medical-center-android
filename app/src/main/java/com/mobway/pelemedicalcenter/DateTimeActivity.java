package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.adapters.RVAdapterConsult;
import com.mobway.pelemedicalcenter.adapters.RVAdapterTime;
import com.mobway.pelemedicalcenter.controllers.ArriveOrderController;
import com.mobway.pelemedicalcenter.controllers.ConsultController;
import com.mobway.pelemedicalcenter.controllers.TimeController;
import com.mobway.pelemedicalcenter.models.Consult;
import com.mobway.pelemedicalcenter.models.Filter;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.utils.FilterManager;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;

import java.util.ArrayList;
import java.util.List;

public class DateTimeActivity extends AppCompatActivity {

    private Button mButtonDate;
    private Button mButtonNext;
//    private Button mButtonConsultType;
//    private Button mButtonConsultTypeID;
    private CalendarView mCalendarView;
    private RecyclerView mRecyclerView;
    private View mContentCalendar;
    private View mContentTime;
    private TextView mLabelName;
    private TextView mLabelSpecialty;
    private View mWarningLine;
    private ProgressBar mProgressBar;
    private View mLineVacancy;
    private Button mButtonVacancy;

    private Schedule mSchedule;
    private RVAdapterTime mAdapterTime;
    private String mErrorMsg;
    private String mErroTitle = "Oops, encontramos um erro!";
    private TimeController mControllerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data/Horário");

        mSchedule = (Schedule) getIntent().getSerializableExtra("schedule");

        mButtonDate = findViewById(R.id.button_date);
//        mButtonConsultType = findViewById(R.id.button_consult_type);
//        mButtonConsultTypeID = findViewById(R.id.button_consult_type_id);
        mButtonNext = findViewById(R.id.button_next);
        mCalendarView = findViewById(R.id.calendar_view);
        mContentCalendar = findViewById(R.id.content_calendar);
        mContentTime = findViewById(R.id.content_time);
        mRecyclerView = findViewById(R.id.recycler_view_time);
        mLabelName = findViewById(R.id.tv_name);
        mLabelSpecialty = findViewById(R.id.tv_specialty);
        mWarningLine = findViewById(R.id.line_warning);
        mProgressBar = findViewById(R.id.progressBarTime);
        mLineVacancy = findViewById(R.id.line_vacancies);
        mButtonVacancy = findViewById(R.id.button_vacancy);

        mLabelName.setText(mSchedule.getPhysician().getName());
        mLabelSpecialty.setText(mSchedule.getPhysician().getSpecialty());

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        List<Time> times = new ArrayList<>();
        mAdapterTime = new RVAdapterTime(times);
        mRecyclerView.setAdapter(mAdapterTime);

//        mButtonConsultType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(DateTimeActivity.this);
//                View v = getLayoutInflater().inflate(R.layout.dialog_select_consult_type, null);
//                builder.setView(v);
//                final AlertDialog alert = builder.create();
//
//                RVAdapterConsult adapterConsult = new RVAdapterConsult(new ArrayList<Consult>());
//                adapterConsult.delegateDialog(alert);
//                adapterConsult.delegateButton(mButtonConsultType);
//                adapterConsult.delegateSchedule(mSchedule);
//
//                RecyclerView recyclerViewInsurance = v.findViewById(R.id.recycler_view_consult_type);
//                recyclerViewInsurance.setLayoutManager(new LinearLayoutManager(DateTimeActivity.this));
//                recyclerViewInsurance.setAdapter(adapterConsult);
//
////                mSchedule.setType(adapterConsult.getSelectedConsult());
//
//                ConsultController consultController = new ConsultController(DateTimeActivity.this).delegateAdapter(adapterConsult);
//                consultController.getTypes();
//
//                alert.show();
//            }
//        });

        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatusView(mContentCalendar);
            }
        });

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                mButtonDate.setText(date);
                if (isOrderChegada()) {
                    mWarningLine.setVisibility(View.VISIBLE);
                    mLineVacancy.setVisibility(View.VISIBLE);
                    mContentCalendar.setVisibility(View.GONE);

                    ArriveOrderController arriveOrderController = new ArriveOrderController(DateTimeActivity.this);
                    arriveOrderController.delegateSchedule(mSchedule);
                    arriveOrderController.vacancies(date.replaceAll("/","-"), mSchedule.getPhysician().getCodSala());

                } else {
                    mControllerTime = new TimeController(DateTimeActivity.this)
                            .delegateAdapter(mAdapterTime)
                            .delegateProgressBar(mProgressBar);
                    mControllerTime.getHours(date.replaceAll("/","-"),mSchedule.getPhysician().getCodSala());

                    changeStatusView(mContentTime);
                }
            }
        });

        mButtonVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DateTimeActivity.this);
                View v = getLayoutInflater().inflate(R.layout.dialog_select_round, null);
                builder.setView(v);
                final AlertDialog alert = builder.create();
                v.findViewById(R.id.line_manha).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mButtonVacancy.setText("Manhã");
                        if (mSchedule.getArriveOrderResponse().vacanciesFirstRound != null &&
                                mSchedule.getArriveOrderResponse().vacanciesFirstRound > 0) {
                            mButtonVacancy.setText("Manhã - Indisponível");
                        }
                        alert.dismiss();
                    }
                });
                v.findViewById(R.id.line_tarde).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mButtonVacancy.setText("Tarde");
                        if (mSchedule.getArriveOrderResponse().vacanciesFirstRound != null &&
                                mSchedule.getArriveOrderResponse().vacanciesFirstRound > 0) {
                            mButtonVacancy.setText("tarde - Indisponível");
                        }
                        alert.dismiss();
                    }
                });

                alert.show();
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateStep()) {
                    MobwayDialog.show(view.getContext(), mErrorMsg, mErroTitle);
                    return;
                }
                FilterManager filter = new FilterManager(getBaseContext());
                Consult consult = filter.getFilters().getConsult();

                mSchedule.setType(consult);
                mSchedule.setDate(mButtonDate.getText().toString());
                mSchedule.setTime(!mSchedule.getPhysician().getOrdemChegada() ? mAdapterTime.getSelectedTime().getHour(): "Ordem de chegada");
                mSchedule.setTimeInfo(!mSchedule.getPhysician().getOrdemChegada() ? mAdapterTime.getSelectedTime(): null);
                mSchedule.setUuid(!mSchedule.getPhysician().getOrdemChegada() ? mAdapterTime.getSelectedTime().getId(): null);

                Intent it = new Intent(getBaseContext(), PaymentActivity.class);
//                Intent it = new Intent(getBaseContext(), PatientListActivity.class);
                it.putExtra("schedule", mSchedule);
                startActivity(it);
            }
        });

    }

    public void changeStatusView(View viewtoShow) {
        viewtoShow.setVisibility(View.VISIBLE);

        if (viewtoShow.getId() == R.id.content_calendar) {
            mContentTime.setVisibility(View.GONE);
        } else if (viewtoShow.getId() == R.id.content_time) {
            mContentCalendar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validateStep() {
//        if (mButtonConsultType.getText().equals("")) {
//            mErrorMsg = "Antes de avançar, por favor selecione tipo da consulta.";
//            return false;
//        }

        if (mButtonDate.getText().equals("")) {
            mErrorMsg = "Antes de avançar, por favor selecione uma data.";
            return false;
        }
        if (mAdapterTime.getSelectedTime() == null) {
            if (!isOrderChegada()) {
                mErrorMsg = "Antes de avançar, por favor selecione um horário.";
                return false;
            } else {
                if (mButtonVacancy.getText().toString().equals("")) {
                    mErrorMsg = "Antes de avançar, por favor selecione um turno.";
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    private boolean isOrderChegada() {
        return mSchedule.getPhysician().getOrdemChegada();
    }

}
