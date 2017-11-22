package com.mobway.pelemedicalcenter.database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by arthur.stapassoli on 23/10/2017.
 */

public class AppDatabaseBuilder {

    private static AppDatabase mAppDatabase;
    private static final String DB_NAME = "database-pelemedical";

    public static AppDatabase getInstance(Context ctx) {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(ctx,
                    AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return mAppDatabase;
    }

}
