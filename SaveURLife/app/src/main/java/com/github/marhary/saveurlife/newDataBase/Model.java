package com.github.marhary.saveurlife.newDataBase;

import android.content.ContentValues;
import android.database.Cursor;


public abstract class Model {
    public abstract ContentValues convertToCV();
    public abstract Model convertFromCursor(Cursor cursor);

}
