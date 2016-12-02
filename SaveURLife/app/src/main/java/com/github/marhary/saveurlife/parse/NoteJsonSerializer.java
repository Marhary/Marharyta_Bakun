package com.github.marhary.saveurlife.parse;


import com.github.marhary.saveurlife.asyncOperation.AsyncOperation;
import com.github.marhary.saveurlife.auth.IConstant;
import com.github.marhary.saveurlife.models.Note;

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
                jsonNote.put(IConstant.ID, note.getId());
                jsonNote.put(IConstant.TITLE, note.getTitle());
                jsonNote.put(IConstant.MESSAGE, note.getMessage());
                jsonNote.put(IConstant.CATEGORY, note.getCategory());
                jsonNote.put(IConstant.DATE, note.getDate());
                array.put(jsonNote);
            }
            basicJsonObject.put(IConstant.NOTES, array);
            return basicJsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
