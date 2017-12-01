package com.mobway.pelemedicalcenter.component;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.mobway.pelemedicalcenter.R;

/**
 * Created by acstapassoli on 01/12/17.
 */

public class InputMobway extends TextInputLayout {

    private View mView;
    private Context mContext;

    public InputMobway(Context context) {
        super(context);
        mContext = context;
        addView(inflateLayout());
    }

    public InputMobway(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        addView(inflateLayout());
    }

    public InputMobway(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addView(inflateLayout());
    }


    private View inflateLayout(){
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.component_edit_text, null);
        return mView;
    }






}
