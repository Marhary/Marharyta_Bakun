package com.github.marhary.saveurlife.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.marhary.saveurlife.NoteDetailActivity;
import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.adapters.NoteAdapter;
import com.github.marhary.saveurlife.constants.IConstant;
import com.github.marhary.saveurlife.constants.IConstantFragments;
import com.github.marhary.saveurlife.database.NotebookDb;
import com.github.marhary.saveurlife.models.Note;

import java.util.ArrayList;

public class ListOfNotesFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;

    public enum FragmentToLaunch {VIEW, EDIT, CREATE}


    @Override
    public void onResume() {
        super.onResume();
        getNotes();
        noteAdapter = new NoteAdapter(getActivity(), notes);

        setListAdapter(noteAdapter);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.holo_blue_dark));
        getListView().setDividerHeight(1);

        registerForContextMenu(getListView());

    }

    public void getNotes(){
        NotebookDb dbAdapter = new NotebookDb(getActivity().getBaseContext());
        dbAdapter.open();
        notes = dbAdapter.getAllNotes();
        dbAdapter.close();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchNoteDetailActivity(ListOfNotesFragment.FragmentToLaunch.VIEW, position);
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

                launchNoteDetailActivity(FragmentToLaunch.EDIT, rowPosition);
                Log.d(IConstant.MENU_CLICK, IConstant.WE_PRESSED_EDIT);
                return true;

            case R.id.delete:
                NotebookDb dbAdapter = new NotebookDb(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteNote(note);

                notes.clear();
                notes.addAll(dbAdapter.getAllNotes());
                noteAdapter.notifyDataSetChanged();

                dbAdapter.close();
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(ListOfNotesFragment.FragmentToLaunch ftl, int position) {

        //note information
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent that launches noteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //pass information of the note which click on nDA
        intent.putExtra(IConstantFragments.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(IConstantFragments.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(IConstantFragments.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(IConstantFragments.NOTE_ID_EXTRA, note.getId());

        switch (ftl) {
            case VIEW:
                intent.putExtra(IConstantFragments.NOTE_FRAGMENT_TO_LOAD_EXTRA, FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(IConstantFragments.NOTE_FRAGMENT_TO_LOAD_EXTRA, FragmentToLaunch.EDIT);
                break;
        }

        startActivity(intent);
    }
}
