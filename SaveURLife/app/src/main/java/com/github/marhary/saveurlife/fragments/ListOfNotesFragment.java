package com.github.marhary.saveurlife.fragments;


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
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.marhary.saveurlife.ListOfNotesActivity;
import com.github.marhary.saveurlife.NoteDetailActivity;
import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.adapters.NoteAdapter;
import com.github.marhary.saveurlife.database.NotebookDbAdapter;
import com.github.marhary.saveurlife.models.Note;
import com.github.marhary.saveurlife.parse.NoteJsonDeserializer;
import com.github.marhary.saveurlife.parse.NoteJsonSerializer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListOfNotesFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;


    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        NotebookDbAdapter dbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
        dbAdapter.open();
        notes = dbAdapter.getAllNotes();
        dbAdapter.close();
        try {
            final String execute = new NoteJsonSerializer().execute(notes);
            ArrayList<Note> list = new NoteJsonDeserializer().execute(execute);
            final StringBuilder stringBuilder = new StringBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        noteAdapter = new NoteAdapter(getActivity(), notes);

        setListAdapter(noteAdapter);

        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.black));
        getListView().setDividerHeight(1);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchNoteDetailActivity(ListOfNotesActivity.FragmentToLaunch.VIEW, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //give position which i pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;
        Note note = (Note) getListAdapter().getItem(rowPosition);

        switch (item.getItemId()) {
            case R.id.edit:

                launchNoteDetailActivity(ListOfNotesActivity.FragmentToLaunch.EDIT, rowPosition);
                Log.d("Menu Clicks", "We pressed edit");
                return true;

            case R.id.delete:
                NotebookDbAdapter dbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteNote(note.getId());

                notes.clear();
                notes.addAll(dbAdapter.getAllNotes());
                noteAdapter.notifyDataSetChanged();

                dbAdapter.close();
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(ListOfNotesActivity.FragmentToLaunch ftl, int position) {

        //note information
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent that launches noteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //pass information of the note which click on nDA
        intent.putExtra(ListOfNotesActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(ListOfNotesActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(ListOfNotesActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(ListOfNotesActivity.NOTE_ID_EXTRA, note.getId());

        switch (ftl) {
            case VIEW:
                intent.putExtra(ListOfNotesActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, ListOfNotesActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(ListOfNotesActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, ListOfNotesActivity.FragmentToLaunch.EDIT);
                break;
        }

        startActivity(intent);
    }
}