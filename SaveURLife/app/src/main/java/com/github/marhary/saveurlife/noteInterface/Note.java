// TODO: 11/21/2016 rename package
package com.github.marhary.saveurlife.noteInterface;


import com.github.marhary.saveurlife.R;

public class Note {
    private String title, message;
    private long noteId, dateCreatedMilli;
    private Category category;

    // TODO: 11/21/2016 allow set user's category
    public enum Category {PERSONAL, IDEAS, INTERESTING, JOB}

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

    // TODO: 11/21/2016 move to category
    public static int categoryToDrawable(Category noteCategory) {

        switch (noteCategory) {
            case PERSONAL:
                // TODO: 11/21/2016 p?? etc.
                return R.drawable.p;
            case IDEAS:
                return R.drawable.t;
            case JOB:
                return R.drawable.f;
            case INTERESTING:
                return R.drawable.q;
        }
        return R.drawable.p;
    }
}


