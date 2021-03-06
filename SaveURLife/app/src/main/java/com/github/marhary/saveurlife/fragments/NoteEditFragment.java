package com.github.marhary.saveurlife.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.constants.IConstantActivities;
import com.github.marhary.saveurlife.constants.IConstantFragments;
import com.github.marhary.saveurlife.database.NotebookDb;
import com.github.marhary.saveurlife.models.Note;


public class NoteEditFragment extends Fragment {

    private ImageButton noteCatButton;
    private EditText title, message;
    private Note.Category savedButtonCategory;
    private AlertDialog categoryDialogObject, confirmDialogObject;

    private static final String MODIFIED_CATEGORY = "Modified Category";

    private boolean newNote = false;
    private long noteId = 0;

    public NoteEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //grab the bundle that sends along whether creating a new note
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            newNote = bundle.getBoolean(IConstantActivities.NEW_NOTE_EXTRA, false);
        }

        if (savedInstanceState != null) {
            savedButtonCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);
        }

        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);
        Button savedButton = (Button) fragmentLayout.findViewById(R.id.saveNote);

        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(IConstantFragments.NOTE_TITLE_EXTRA, ""));
        message.setText(intent.getExtras().getString(IConstantFragments.NOTE_MESSAGE_EXTRA, ""));
        noteId = intent.getExtras().getLong(IConstantFragments.NOTE_ID_EXTRA, 0);

        //when i change orientation category is saved
        if (savedButtonCategory != null) {
            noteCatButton.setImageResource(Note.categoryToDrawable(savedButtonCategory));
            //came from list fragment so just do everything noormaly
        } else if (!newNote) {
            Note.Category noteCat = (Note.Category) intent.getSerializableExtra(IConstantFragments.NOTE_CATEGORY_EXTRA);
            savedButtonCategory = noteCat;
            noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));
        }

        buildCategoryDialog();
        buildConfirmDialog();

        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialogObject.show();
            }
        });

        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });

        return fragmentLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(MODIFIED_CATEGORY, savedButtonCategory);
    }

    private void buildCategoryDialog() {
        final String[] categories = new String[]{IConstantFragments.NO, IConstantFragments.IMPORTANT, IConstantFragments.BUSINESS, IConstantFragments.PERSONAL, IConstantFragments.TODO, IConstantFragments.SHOPPING};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle(IConstantFragments.CHOOSE_NOTE_TYPE);

        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //dismisses dialog window
                categoryDialogObject.cancel();

                switch (item) {
                    case 0:
                        savedButtonCategory = Note.Category.NO;
                        noteCatButton.setImageResource(R.drawable.nocategory);
                        break;
                    case 1:
                        savedButtonCategory = Note.Category.IMPORTANT;
                        noteCatButton.setImageResource(R.drawable.important);
                        break;
                    case 2:
                        savedButtonCategory = Note.Category.BUSINESS;
                        noteCatButton.setImageResource(R.drawable.business);
                        break;
                    case 3:
                        savedButtonCategory = Note.Category.PERSONAL;
                        noteCatButton.setImageResource(R.drawable.personal);
                        break;
                    case 4:
                        savedButtonCategory = Note.Category.TODO;
                        noteCatButton.setImageResource(R.drawable.todo);
                        break;
                    case 5:
                        savedButtonCategory = Note.Category.SHOPPING;
                        noteCatButton.setImageResource(R.drawable.shopping);
                        break;
                }
            }
        });

        categoryDialogObject = categoryBuilder.create();
    }

    private void buildConfirmDialog() {
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle(getString(R.string.are_you_sure));
        confirmBuilder.setMessage(getString(R.string.sure_save));

        confirmBuilder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(IConstantFragments.SAVE_NOTE, IConstantFragments.NOTE_TITLE + title.getText() + IConstantFragments.NOTE_MESSAGE +
                        message.getText() + IConstantFragments.NOTE_CATEGORY + savedButtonCategory);

                NotebookDb dbAdapter = new NotebookDb(getActivity().getBaseContext());
                dbAdapter.open();

                if (newNote) {
                    //if its a new note create in database
                    Note note = new Note(title.getText().toString(), message.getText().toString(), (savedButtonCategory == null) ? Note.Category.PERSONAL : savedButtonCategory);
                    dbAdapter.createNote(note);
                } else {
                    Note note = new Note(title.getText() + "", message.getText() + "", savedButtonCategory, noteId, 0);
                    //if old update
                    dbAdapter.updateNote(noteId, title.getText() + "", message.getText() + "", savedButtonCategory);
                }

                dbAdapter.close();
                getActivity().onBackPressed();

            }
        });


        confirmBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
           public void onClick(DialogInterface dialog, int which) {
              //do nothing
          }
        });

        confirmDialogObject = confirmBuilder.create();
    }
}
