package com.github.marhary.saveurlife.newDataBase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DbHelperForModel<T extends Model> {

    private DbHelper dbHelper;
    static final String NOTE_TABLE = "note";

    public DbHelperForModel(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    long put(T t){
        final ContentValues values = t.convertToCV();
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        return sqlDB.insert(DbHelperForModel.NOTE_TABLE, null, values);
    }
}
