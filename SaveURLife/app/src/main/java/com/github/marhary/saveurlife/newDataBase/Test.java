package com.github.marhary.saveurlife.newDataBase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class Test extends AppCompatActivity {

    private DbHelperForModel<Note> helperForNote = new DbHelperForModel<>(new DbHelper(this));
    private DbHelperForModel<Note2> helperForNote2 = new DbHelperForModel<>(new DbHelper(this));


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helperForNote.put(new Note("tite", "message", Note.Category.BUSINESS));
        helperForNote2.put(new Note2());
    }
}
