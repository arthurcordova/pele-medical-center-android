package com.mobway.pelemedicalcenter.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.MainActivity;
import com.mobway.pelemedicalcenter.PatientRecordActivity;
import com.mobway.pelemedicalcenter.PaymentActivity;
import com.mobway.pelemedicalcenter.R;
import com.mobway.pelemedicalcenter.controllers.Controller;
import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.controllers.ScheduleController;
import com.mobway.pelemedicalcenter.models.Schedule;

/**
 * Created by acstapassoli on 24/11/17.
 */

public class MobwayDialog {

    public static boolean TAB_SCHEDULE = false;

    public static void show(Context context, String msg, String tit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_validation, null);
        TextView title = v.findViewById(R.id.tv_title);
        TextView message = v.findViewById(R.id.tv_message);

        title.setText(tit);
        message.setText(msg);

        builder.setView(v);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void dialogDetailSchedule(final Context context, final Schedule schedule, boolean toHome) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_schedule_details, null);

        TextView physicianName = v.findViewById(R.id.tv_physician_name);
        TextView specialty = v.findViewById(R.id.tv_specialty);
        TextView date = v.findViewById(R.id.label_date);
        TextView time = v.findViewById(R.id.label_time);
        TextView patient = v.findViewById(R.id.label_patient);
        TextView payment = v.findViewById(R.id.label_payment);
        Button button = v.findViewById(R.id.button_confirm);

        physicianName.setText(schedule.getPhysician().getName());
        specialty.setText(schedule.getPhysician().getSpecialty());
        date.setText(schedule.getDate());
        time.setText(schedule.getTime());
        patient.setText(schedule.getPatient().getName());
        payment.setText(schedule.getPayment());

        if (toHome) {
            v.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ScheduleController mController = new ScheduleController(context);
//                    mController.saveSchedule(schedule);

                    TAB_SCHEDULE = true;
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                    context.startActivity(intent);

                    //finishAffinity();
                }
            });
        }

        builder.setView(v);
        AlertDialog alert = builder.create();
        alert.show();
    }

}
