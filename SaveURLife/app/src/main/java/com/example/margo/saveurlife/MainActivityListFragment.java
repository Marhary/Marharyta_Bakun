package com.example.margo.saveurlife;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;


    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        notes = new ArrayList<Note>();
        notes.add(new Note("This is a new note title", "This is the body of note",
                Note.Category.PERSONAL));
        notes.add(new Note("Blablabla", "This is the body of note jhfyrtr",
                Note.Category.JOB));
        notes.add(new Note("Cat", "fast dog",
                Note.Category.INTERESTING));
        notes.add(new Note("Happy", "This is happy",
                Note.Category.IDEAS));

        noteAdapter = new NoteAdapter(getActivity(), notes);

        setListAdapter(noteAdapter);

        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.black));
        getListView().setDividerHeight(1);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchNoteDetailActivity(position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

    switch (item.getItemId()){
        case R.id.edit:
            Log.d("Menu Clicks", "We pressed edit");
            return true;
    }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(int position){

        //note information
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent that launches noteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //pass information of the note which click on nDA
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getId());

        startActivity(intent);
    }
}
