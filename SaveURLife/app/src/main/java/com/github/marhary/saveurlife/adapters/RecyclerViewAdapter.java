package com.github.marhary.saveurlife.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.models.Note;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Note> m_arrData = null;

    public RecyclerViewAdapter(ArrayList<Note> arrData){
        m_arrData = arrData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder newHolder = null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        newHolder = new RecordInNotepadHolder(v);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Note note = m_arrData.get(position);
        RecordInNotepadHolder recordHolder = (RecordInNotepadHolder) holder;
        recordHolder.title.setText(note.getTitle());
        recordHolder.note.setText(note.getMessage());
        recordHolder.noteIcon.setImageResource(note.getAssociatedDrawable());

    }

    @Override
    public int getItemCount() {
        return m_arrData.size();
    }

    public class RecordInNotepadHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView note;
        ImageView noteIcon;

        public RecordInNotepadHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listItemNoteTitle);
            note = (TextView) itemView.findViewById(R.id.listItemNoteBody);
            noteIcon = (ImageView) itemView.findViewById(R.id.listItemNoteImg);
        }
    }
}

