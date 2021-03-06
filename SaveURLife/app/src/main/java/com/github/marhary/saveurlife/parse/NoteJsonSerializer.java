package com.github.marhary.saveurlife.parse;


import com.github.marhary.saveurlife.asyncOperation.AsyncOperation;
import com.github.marhary.saveurlife.constants.IConstantActivities;
import com.github.marhary.saveurlife.constants.IConstantParse;
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
                jsonNote.put(IConstantActivities.ID, note.getId());
                jsonNote.put(IConstantActivities.TITLE, note.getTitle());
                jsonNote.put(IConstantParse.MESSAGE, note.getMessage());
                jsonNote.put(IConstantParse.CATEGORY, note.getCategory());
                jsonNote.put(IConstantParse.DATE, note.getDate());
                array.put(jsonNote);
            }
            basicJsonObject.put(IConstantParse.NOTES, array);
            return basicJsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
