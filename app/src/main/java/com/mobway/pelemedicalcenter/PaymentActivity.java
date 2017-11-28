package com.mobway.pelemedicalcenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPayment;
import com.mobway.pelemedicalcenter.controllers.ScheduleController;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RVAdapterPayment mAdapterPayment;
    private Schedule mSchedule;
    private String mErrorMsg;
    private String mErroTitle = "Oops, encontramos um erro!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pagamento");

        mSchedule = (Schedule) getIntent().getSerializableExtra("schedule");

        List<String> payments = new ArrayList<>();
        payments.add("Cartão");
        payments.add("Dinheiro");
        payments.add("Plano de saúde");

        mAdapterPayment = new RVAdapterPayment(payments);

        mRecyclerView = findViewById(R.id.recycler_view_payment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapterPayment);

        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateStep()) {
                    MobwayDialog.show(view.getContext(), mErrorMsg, mErroTitle);
                    return;
                }
                mSchedule.setPayment(mAdapterPayment.getSelectedPayment());

//                MobwayDialog.dialogDetailSchedule(PaymentActivity.this, mSchedule, true);

                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.dialog_schedule_details, null);

                TextView physicianName = v.findViewById(R.id.tv_physician_name);
                TextView specialty = v.findViewById(R.id.tv_specialty);
                TextView date = v.findViewById(R.id.label_date);
                TextView time = v.findViewById(R.id.label_time);
                TextView patient = v.findViewById(R.id.label_patient);
                TextView payment = v.findViewById(R.id.label_payment);
                Button button = v.findViewById(R.id.button_confirm);

                physicianName.setText(mSchedule.getPhysician().getName());
                specialty.setText(mSchedule.getPhysician().getSpecialty());
                date.setText(mSchedule.getDate());
                time.setText(mSchedule.getTime());
                patient.setText(mSchedule.getPatient().getName());
                payment.setText(mSchedule.getPayment());

                builder.setView(v);
                final AlertDialog alert = builder.create();
                alert.show();

                v.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
//
//                        ScheduleController mController = new ScheduleController(getBaseContext());
//                        mController.saveSchedule(mSchedule);

                        MobwayDialog.TAB_SCHEDULE = true;
                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                        startActivity(intent);


                        //finishAffinity();
                    }
                });



            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validateStep() {
        if (mAdapterPayment.getSelectedPayment() == null) {
            mErrorMsg = "Antes de avançar, por favor selecione uma forma de pagamento.";
            return false;
        }
        return true;
    }

}
