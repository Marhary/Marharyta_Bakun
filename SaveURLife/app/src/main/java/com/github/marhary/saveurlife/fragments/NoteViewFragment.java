package com.github.marhary.saveurlife.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.constants.IConstantFragments;
import com.github.marhary.saveurlife.models.Note;


public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_view, container, false);

        TextView title = (TextView) fragmentLayout.findViewById(R.id.viewNoteTitle);
        TextView message = (TextView) fragmentLayout.findViewById(R.id.viewNoteMessage);
        ImageView icon = (ImageView) fragmentLayout.findViewById(R.id.viewNoteIcon);

        Intent intent = getActivity().getIntent();


        title.setText(intent.getExtras().getString(IConstantFragments.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(IConstantFragments.NOTE_MESSAGE_EXTRA));

        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(IConstantFragments.NOTE_CATEGORY_EXTRA);
        icon.setImageResource(Note.categoryToDrawable(noteCat));

        return fragmentLayout;
    }

}
