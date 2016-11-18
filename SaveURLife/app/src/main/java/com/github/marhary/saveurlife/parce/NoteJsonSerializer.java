package com.github.marhary.saveurlife.parce;


import com.github.marhary.saveurlife.asyncOperation.AsyncOperation;
import com.github.marhary.saveurlife.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoteJsonSerializer extends AsyncOperation<ArrayList<Note>, String> {

    @Override
    protected String doInBackground(ArrayList<Note> notes) {
        try {
            JSONObject basicJsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            for (Note note : notes) {
                JSONObject jsonNote = new JSONObject();
                jsonNote.put("id", note.getId());
                jsonNote.put("title", note.getTitle());
                jsonNote.put("message", note.getMessage());
                jsonNote.put("category", note.getCategory());
                jsonNote.put("date", note.getDate());
                array.put(jsonNote);
            }
            basicJsonObject.put("notes", array);
            return basicJsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
