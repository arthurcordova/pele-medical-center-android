package com.mobway.pelemedicalcenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobway.pelemedicalcenter.models.Consult;
import com.mobway.pelemedicalcenter.models.Filter;


/**
 * Created by arthurcordova on 7/5/16.
 */
public final class FilterManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private final String PREF_NAME = "filters_manager";

    private final String KEY_PLACE = "place";
    private final String KEY_CLINIC = "clinic";
    private final String KEY_EMERGENCY = "emergency";
    private final String KEY_INSURANCE = "insurance";
    private final String KEY_PRIVATE = "private";
    private final String KEY_SPECIALTIES = "specialties";
    private final String KEY_CONSULT_TYPE = "consult_type";
    private final String KEY_CONSULT_TYPE_ID = "consult_type_id";

    public FilterManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void save(Filter filter) {
        editor.putString(KEY_PLACE, filter.getPlace());
        editor.putBoolean(KEY_EMERGENCY, filter.getEmergency());
        editor.putString(KEY_CLINIC, filter.getClinic());
        editor.putString(KEY_INSURANCE, filter.getInsurance());
//        editor.putBoolean(KEY_PRIVATE, filter.getPrivateSchedule());
        editor.putString(KEY_SPECIALTIES, filter.convertSpecialtiesToSave(filter.getSpecialties()));
        editor.putString(KEY_CONSULT_TYPE, filter.getConsult().getDescription());
        editor.putString(KEY_CONSULT_TYPE_ID, filter.getConsult().getUuid());
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public Filter getFilters() {
        Filter filter = new Filter();
        filter.setPlace(preferences.getString(KEY_PLACE,""));
        filter.setClinic(preferences.getString(KEY_CLINIC,""));
        filter.setInsurance(preferences.getString(KEY_INSURANCE,""));
        filter.setEmergency(preferences.getBoolean(KEY_EMERGENCY, false));
//        filter.setPrivateSchedule(preferences.getBoolean(KEY_PRIVATE, false));
        filter.setSpecialties(filter.convertSpecialtiesToList(preferences.getString(KEY_SPECIALTIES, "")));
        filter.setConsult(new Consult(preferences.getString(KEY_CONSULT_TYPE_ID,""), preferences.getString(KEY_CONSULT_TYPE,"")));
        return filter;
    }
}
