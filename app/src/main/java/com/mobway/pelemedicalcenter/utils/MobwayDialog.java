package com.mobway.pelemedicalcenter.utils;

import android.content.Context;
import android.content.DialogInterface;
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
import com.mobway.pelemedicalcenter.models.Physician;
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

        if (schedule.getPhysician() != null) {
            physicianName.setText(schedule.getPhysician().getName());
            specialty.setText(schedule.getPhysician().getSpecialty());
        }
        if (schedule.getPatient() != null) {
            patient.setText(schedule.getPatient().getName());
        }
        date.setText(schedule.getDate());
        time.setText(schedule.getTime());
        payment.setText(schedule.getPayment());


        v.findViewById(R.id.button_confirm).setVisibility(View.GONE);
        v.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        alert.dismiss();

                final AlertDialog.Builder b =
                        new AlertDialog.Builder(context);
                b.setTitle("Cancelar agendamento");
                b.setMessage("Todos os dados do agendamento serão perdidos. Deseja cancelar o agendamento?");
                b.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                b.setNegativeButton("Não", null);
                b.show();


            }
        });



        builder.setView(v);
        AlertDialog alert = builder.create();
        alert.show();
    }

}
