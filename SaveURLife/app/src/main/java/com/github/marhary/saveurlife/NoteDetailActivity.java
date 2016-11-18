package com.github.marhary.saveurlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.marhary.saveurlife.fragments.NoteEditFragment;
import com.github.marhary.saveurlife.fragments.NoteViewFragment;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA = "New Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        createAndAddFragment();
    }

    private void createAndAddFragment() {

        //grab intent and fragment to launch from mainAct list fragment
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch =
                (MainActivity.FragmentToLaunch) intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA);

        //grabbing fragment manager and fragment transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //choose the correct fragment to load
        switch (fragmentToLaunch) {
            case EDIT:
                //create and add note edit fragment to note detail activity
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                setTitle(getString(R.string.edit_note_title));
                fragmentTransaction.add(R.id.note_container, noteEditFragment, "NOTE_EDIT_FRAGMENT");
                break;

            case VIEW:
                //create and add note view fragment to note detail activity
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                setTitle(getString(R.string.all_note_title));
                fragmentTransaction.add(R.id.note_container, noteViewFragment, "NOTE_VIEW_FRAGMENT");
                break;

            case CREATE:
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                setTitle(getString(R.string.create_fragment_title));

                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA, true);
                noteCreateFragment.setArguments(bundle);

                fragmentTransaction.add(R.id.note_container, noteCreateFragment, "NOTE_CREATE_FRAGMENT");
                break;
        }
        //commit changes to that everything works
        fragmentTransaction.commit();
    }
}
