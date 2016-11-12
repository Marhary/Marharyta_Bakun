package com.example.margo.saveurlife.parce;

import com.example.margo.saveurlife.asyncOperation.AsyncOperation;
import com.example.margo.saveurlife.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NoteJsonDeserializer extends AsyncOperation<String, ArrayList<Note>> {
    @Override
    protected ArrayList<Note> doInBackground(String s) {
        try {
            JSONArray array = new JSONObject(s).getJSONArray("notes");
            ArrayList<Note> result = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject currentNote = array.getJSONObject(i);
                result.add(new Note(currentNote.getString("title"), currentNote.getString("message"), Note.Category.valueOf(currentNote.getString("category")), currentNote.getLong("id"), currentNote.getLong("date")));
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
