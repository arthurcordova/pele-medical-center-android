package com.mobway.pelemedicalcenter.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.R;

/**
 * Created by acstapassoli on 24/11/17.
 */

public class MobwayDialog {

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

}
