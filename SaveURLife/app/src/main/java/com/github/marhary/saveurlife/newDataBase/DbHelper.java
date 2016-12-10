package com.github.marhary.saveurlife.newDataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.github.marhary.saveurlife.auth.IConstant;

public class DbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 2;

    DbHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableuerysGenerator.createQuert(Note.class));
        db.execSQL(TableuerysGenerator.createQuert(Note2.class));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                IConstant.UPGRADING + oldVersion + IConstant.TO
                        + newVersion + IConstant.DESTROY);
        db.execSQL(TableuerysGenerator.deleteQuey(Note.class));
        db.execSQL(TableuerysGenerator.deleteQuey(Note2.class));
        onCreate(db);
    }
}
