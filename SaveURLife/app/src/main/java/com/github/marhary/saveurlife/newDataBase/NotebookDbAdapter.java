package com.github.marhary.saveurlife.newDataBase;


import android.database.Cursor;

import com.github.marhary.saveurlife.models.Note;

public class NotebookDbAdapter {
    public static Note cursorToNote(Cursor cursor) {
        Note newNote = new Note(cursor.getString(1), cursor.getString(2),
                Note.Category.valueOf(cursor.getString(3)), cursor.getLong(0), cursor.getLong(4));
        return newNote;
    }
}
