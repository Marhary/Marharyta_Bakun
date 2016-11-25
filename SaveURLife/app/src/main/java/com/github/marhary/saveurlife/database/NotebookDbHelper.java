package com.github.marhary.saveurlife.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.github.marhary.saveurlife.database.NotebookDb.CREATE_TABLE_NOTE;
import static com.github.marhary.saveurlife.database.NotebookDb.NOTE_TABLE;

public class NotebookDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 2;

    NotebookDbHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(NotebookDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
        onCreate(db);
    }
}
