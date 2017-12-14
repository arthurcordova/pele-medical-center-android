package com.mobway.pelemedicalcenter.utils;

import android.app.Activity;
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
import com.mobway.pelemedicalcenter.models.ScheduleRequest;

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
        View buttonClose = v.findViewById(R.id.button_close);

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
        final AlertDialog alert = builder.create();
        alert.show();
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });
    }


    public static void finishScheduleDialog(final Activity activity, final Schedule schedule){
//        final Context context = activity.getBaseContext();
//        schedule.setPayment(paymentStr);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_schedule_details, null);

        TextView physicianName = v.findViewById(R.id.tv_physician_name);
        TextView specialty = v.findViewById(R.id.tv_specialty);
        TextView date = v.findViewById(R.id.label_date);
        TextView time = v.findViewById(R.id.label_time);
        TextView patient = v.findViewById(R.id.label_patient);
        TextView payment = v.findViewById(R.id.label_payment);
        Button buttonConfirm = v.findViewById(R.id.button_confirm);
        Button buttonCancel = v.findViewById(R.id.button_cancel);
        View buttonClose = v.findViewById(R.id.button_close);

        String round = null;
        if (schedule.getTimerOrderArrive() != null && schedule.getTimerOrderArrive().round != null) {
            if (schedule.getTimerOrderArrive().round == 1) {
                round = "Turno da manhã";
            } else {
                round = "Turno da tarde";
            }
        }

        physicianName.setText(schedule.getPhysician().getName());
        specialty.setText(schedule.getPhysician().getSpecialty());
        date.setText(schedule.getDate());
        time.setText(schedule.getTime() != null ? schedule.getTime() : round);
        patient.setText(schedule.getPatient().getName());
        payment.setText(schedule.getPayment());

        builder.setView(v);
        final AlertDialog alert = builder.create();
        alert.show();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String round = null;
                String date = null;
                if (schedule.getTimerOrderArrive() != null && schedule.getTimerOrderArrive().round != null) {
                    round = schedule.getTimerOrderArrive().round.toString();
                    date = schedule.getTimerOrderArrive().date;
                }

                ScheduleRequest request = new ScheduleRequest();
                request.setCodAgenda(schedule.getUuid() == null ? schedule.getTimerOrderArrive().code.toString() : schedule.getUuid());
                request.setCodProcedimento(schedule.getType().getUuid());
                request.setTurno(round);
                request.setCodCliente(schedule.getPatient().getUuid());
                request.setData(date);

                ScheduleController controller = new ScheduleController(activity);
                controller.postSchedule(request);

            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder b =
                        new AlertDialog.Builder(activity);
                b.setTitle("Cancelar agendamento");
                b.setMessage("Todos os dados do agendamento serão perdidos. Deseja cancelar o agendamento?");
                b.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MobwayDialog.TAB_SCHEDULE = false;
                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                        activity.startActivity(intent);
                    }
                });
                b.setNegativeButton("Não", null);
                b.show();
            }
        });
    }

}
