package com.mobway.pelemedicalcenter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPayment;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pagamento");

        List<String> payments = new ArrayList<>();
        payments.add("Cartão");
        payments.add("Dinheiro");
        payments.add("Plano de saúde");

        RVAdapterPayment adapterPayment = new RVAdapterPayment(payments);

        mRecyclerView = findViewById(R.id.recycler_view_payment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapterPayment);

        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                View v = getLayoutInflater().inflate(R.layout.dialog_schedule_details, null);

                builder.setView(v);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


}
