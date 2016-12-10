package com.github.marhary.saveurlife.newDataBase;

/**
 * Created by Margo on 12/5/2016.
 */

public class TableuerysGenerator {

    static final String NOTE_TABLE = "note";

    public static String createQuert(Class<? extends Model> clazz) {
//        if (clazz.isAssignableFrom(Note.class)) {
//            final Field[] fields = clazz.getDeclaredFields();
//            for (Field field : fields) {
//                final Annotation[] annotations = field.getAnnotations();
//                if (annotations instanceof )
//            }
//            return "create table " + NOTE_TABLE + " ( "
//                    + Note.COLUMN_ID + " integer primary key autoincrement, "
//                    + Note.COLUMN_TITLE + " text not null, "
//                    + Note.COLUMN_MESSAGE + " text not null, "
//                    + Note.COLUMN_CATEGORY + " text not null, "
//                    + Note.COLUMN_DATE + ");";
//        }
        return null;
    }

    public static String deleteQuey(Class<? extends Model> clazz) {
        if (clazz.isAssignableFrom(Note.class)) {
            return "DROP TABLE IF EXISTS " + NOTE_TABLE;
        }
        return null;
    };
}

