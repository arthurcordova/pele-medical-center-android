package com.mobway.pelemedicalcenter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPayment;
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

                MobwayDialog.dialogDetailSchedule(PaymentActivity.this, mSchedule, true);

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
