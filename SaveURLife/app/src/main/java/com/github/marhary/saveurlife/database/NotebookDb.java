package com.github.marhary.saveurlife.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.marhary.saveurlife.models.Note;

import java.util.ArrayList;
import java.util.Calendar;

import static com.github.marhary.saveurlife.database.NotebookDbAdapter.cursorToNote;

public class NotebookDb {

    static final String NOTE_TABLE = "note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_MESSAGE = "message";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_DATE = "date";

    private final static String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_MESSAGE, COLUMN_CATEGORY, COLUMN_DATE};

    static final String CREATE_TABLE_NOTE = "create table " + NOTE_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_MESSAGE + " text not null, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    private NotebookDbHelper notebookDbHelper;

    public NotebookDb(Context ctx) {
        context = ctx;
    }

    public NotebookDb open() throws android.database.SQLException {
        notebookDbHelper = new NotebookDbHelper(context);
        sqlDB = notebookDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        notebookDbHelper.close();
    }

    public Note createNote(Note note) {
        //create content values
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getTitle());
        values.put(COLUMN_MESSAGE, note.getMessage());
        values.put(COLUMN_CATEGORY, note.getCategory().name());
        values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        //insert to db new note
        long insertId = sqlDB.insert(NOTE_TABLE, null, values);

        //query note
        Cursor cursor = sqlDB.query(NOTE_TABLE,
                allColumns, COLUMN_ID + " = " + insertId, null, null, null, null);

        //convert cursor
        cursor.moveToFirst();
        Note newNote = cursorToNote(cursor);
        cursor.close();

        //return note
        return newNote;
    }

    public long deleteNote(Note note) {
        return sqlDB.delete(NOTE_TABLE, COLUMN_ID + " = " + note.getId(), null);
    }

    public long updateNote(long idToUpdate, String newTitle, String newMessage, Note.Category newCategory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newTitle);
        values.put(COLUMN_MESSAGE, newMessage);
        values.put(COLUMN_CATEGORY, newCategory.name());
        values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        return sqlDB.update(NOTE_TABLE, values, COLUMN_ID + " = " + idToUpdate, null);
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();

        Cursor cursor = sqlDB.query(NOTE_TABLE, null, null, null, null, null, null);

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
        }

        cursor.close();

        return notes;
    }
}
