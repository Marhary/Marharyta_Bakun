
package com.github.marhary.saveurlife.newDataBase;


import android.content.ContentValues;
import android.database.Cursor;

import com.github.marhary.saveurlife.R;

import java.util.Calendar;

public class Note extends Model {
    private String title, message;
    private long noteId, dateCreatedMilli;
    private Category category;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    @Override
    public ContentValues convertToCV() {
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_TITLE, this.getTitle());
        values.put(Note.COLUMN_MESSAGE, this.getMessage());
        values.put(Note.COLUMN_CATEGORY, this.getCategory().name());
        values.put(Note.COLUMN_DATE, String.valueOf(Calendar.getInstance().getTimeInMillis()));
        return values;
    }

    @Override
    public Model convertFromCursor(Cursor cursor) {
        return null;
    }

    public enum Category {
        NO, IMPORTANT, BUSINESS, PERSONAL, TODO, SHOPPING;

    }

    public Note(String title, String message, Category category) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
    }

    public Note(String title, String message, Category category, long noteId, long dateCreatedMilli) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public Note(Cursor cursor) {
        this.title = cursor.getString(1);

    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Category getCategory() {
        return category;
    }

    public long getDate() {
        return dateCreatedMilli;
    }


    public long getId() {
        return noteId;
    }

    public String toString() {
        return "ID: " + noteId + " Title: " + title + " Message: " + message + " IconID: " + category.name()
                + " Data: ";
    }

    public int getAssociatedDrawable() {
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory) {

        switch (noteCategory) {
            case NO:
                return R.drawable.nocategory;
            case IMPORTANT:
                return R.drawable.important;
            case BUSINESS:
                return R.drawable.business;
            case PERSONAL:
                return R.drawable.personal;
            case TODO:
                return R.drawable.todo;
            case SHOPPING:
                return R.drawable.shopping;
        }
        return R.drawable.nocategory;
    }
}


