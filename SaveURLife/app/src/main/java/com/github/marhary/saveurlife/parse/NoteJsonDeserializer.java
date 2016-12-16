package com.github.marhary.saveurlife.parse;

import com.github.marhary.saveurlife.asyncOperation.AsyncOperation;
import com.github.marhary.saveurlife.constants.IConstantActivities;
import com.github.marhary.saveurlife.constants.IConstantParse;
import com.github.marhary.saveurlife.models.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoteJsonDeserializer extends AsyncOperation<String, ArrayList<Note>> {
    @Override
    protected ArrayList<Note> doInBackground(String s) {
        try {
            JSONArray array = new JSONObject(s).getJSONArray(IConstantParse.NOTES);
            ArrayList<Note> result = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject currentNote = array.getJSONObject(i);
                result.add(new Note(currentNote.getString(IConstantActivities.TITLE), currentNote.getString(IConstantParse.MESSAGE), Note.Category.valueOf(currentNote.getString("category")), currentNote.getLong("id"), currentNote.getLong("date")));
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
