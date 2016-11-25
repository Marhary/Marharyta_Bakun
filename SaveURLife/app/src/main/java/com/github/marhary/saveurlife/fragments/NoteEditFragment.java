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

import com.github.marhary.saveurlife.ListOfNotesActivity;
import com.github.marhary.saveurlife.models.Note;
import com.github.marhary.saveurlife.NoteDetailActivity;
import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.database.NotebookDb;



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
            newNote = bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA, false);
        }

        if (savedInstanceState != null) {
            savedButtonCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);
        }

        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);
        Button savedButton = (Button) fragmentLayout.findViewById(R.id.saveNote);

        // TODO: 11/21/2016 get from arguments
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(ListOfNotesActivity.NOTE_TITLE_EXTRA, ""));
        message.setText(intent.getExtras().getString(ListOfNotesActivity.NOTE_MESSAGE_EXTRA, ""));
        noteId = intent.getExtras().getLong(ListOfNotesActivity.NOTE_ID_EXTRA, 0);

        //when i change orientation category is saved
        if (savedButtonCategory != null) {
            noteCatButton.setImageResource(Note.categoryToDrawable(savedButtonCategory));
            //came from list fragment so just do everything noormaly
        } else if (!newNote) {
            Note.Category noteCat = (Note.Category) intent.getSerializableExtra(ListOfNotesActivity.NOTE_CATEGORY_EXTRA);
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
        final String[] categories = new String[]{"No", "Important", "Business", "Personal", "Todo", "Shopping"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose Note Type");

        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //dismisses dialog window
                // TODO: 11/21/2016 you can use dialog from params
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
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Are you sure you want to save the note?");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save Note", "Note title: " + title.getText() + "Note message: " +
                        message.getText() + "Note category: " + savedButtonCategory);

                NotebookDb dbAdapter = new NotebookDb(getActivity().getBaseContext());
                dbAdapter.open();

                if (newNote) {
                    //if its a new note create in database
                    Note note = new Note(title.getText().toString(),message.getText().toString(),(savedButtonCategory == null) ? Note.Category.PERSONAL : savedButtonCategory);
                    dbAdapter.createNote(note);
                } else {
                    //if old update
                    dbAdapter.updateNote(noteId, title.getText() + "", message.getText() + "", savedButtonCategory);
                }

                dbAdapter.close();

                Intent intent = new Intent(getActivity(), ListOfNotesActivity.class);
                startActivity(intent);
            }
        });

        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });

        confirmDialogObject = confirmBuilder.create();
    }

    public static Fragment newInstance() {
        // TODO: 11/21/2016 set arguments and return
        return null;
    }
}
