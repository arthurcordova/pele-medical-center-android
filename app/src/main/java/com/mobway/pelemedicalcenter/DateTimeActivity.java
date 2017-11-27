package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobway.pelemedicalcenter.adapters.RVAdapterTime;
import com.mobway.pelemedicalcenter.controllers.TimeController;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;

import java.util.ArrayList;
import java.util.List;

public class DateTimeActivity extends AppCompatActivity {

    private Button mButtonDate;
    private Button mButtonNext;
    private CalendarView mCalendarView;
    private RecyclerView mRecyclerView;
    private View mContentCalendar;
    private View mContentTime;
    private TextView mLabelName;
    private TextView mLabelSpecialty;
    private View mWarningLine;
    private ProgressBar mProgressBar;

    private Schedule mSchedule;
    private RVAdapterTime mAdapterTime;
    private String mErrorMsg;
    private String mErroTitle = "Oops, encontramos um erro!";
    private TimeController mControllerTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data/Horário");

        mSchedule = (Schedule) getIntent().getSerializableExtra("schedule");

        mButtonDate = findViewById(R.id.button_date);
        mButtonNext = findViewById(R.id.button_next);
        mCalendarView = findViewById(R.id.calendar_view);
        mContentCalendar = findViewById(R.id.content_calendar);
        mContentTime = findViewById(R.id.content_time);
        mRecyclerView = findViewById(R.id.recycler_view_time);
        mLabelName = findViewById(R.id.tv_name);
        mLabelSpecialty = findViewById(R.id.tv_specialty);
        mWarningLine = findViewById(R.id.line_warning);
        mProgressBar = findViewById(R.id.progressBarTime);

        mLabelName.setText(mSchedule.getPhysician().getName());
        mLabelSpecialty.setText(mSchedule.getPhysician().getSpecialty());

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        //MOCK
        List<Time> times = new ArrayList<>();
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));
//        times.add(new Time("10:15"));


        mAdapterTime = new RVAdapterTime(times);
        mRecyclerView.setAdapter(mAdapterTime);

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
                if (mSchedule.getPhysician().getOrdemChegada()) {
                    mWarningLine.setVisibility(View.VISIBLE);
                    mContentCalendar.setVisibility(View.GONE);
                } else {
                    mControllerTime = new TimeController(DateTimeActivity.this)
                            .delegateAdapter(mAdapterTime)
                            .delegateProgressBar(mProgressBar);
                    mControllerTime.getHours(date.replaceAll("/","-"),mSchedule.getPhysician().getCodSala());

                    changeStatusView(mContentTime);
                }
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateStep()) {
                    MobwayDialog.show(view.getContext(), mErrorMsg, mErroTitle);
                    return;
                }
                mSchedule.setDate(mButtonDate.getText().toString());

                mSchedule.setTime(!mSchedule.getPhysician().getOrdemChegada() ? mAdapterTime.getSelectedTime().getHour(): "Ordem de chegada");

                Intent it = new Intent(getBaseContext(), PatientListActivity.class);
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
        if (mButtonDate.getText().equals("")) {
            mErrorMsg = "Antes de avançar, por favor selecione uma data.";
            return false;
        }
        if (mAdapterTime.getSelectedTime() == null) {
            if (!mSchedule.getPhysician().getOrdemChegada()) {
                mErrorMsg = "Antes de avançar, por favor selecione um horário.";
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

}
