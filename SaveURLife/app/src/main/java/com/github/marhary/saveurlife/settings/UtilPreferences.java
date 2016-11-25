package com.github.marhary.saveurlife.settings;

import android.app.Activity;
import android.content.SharedPreferences;

import com.github.marhary.saveurlife.auth.Constant;

import static android.R.attr.id;
import static android.content.Context.MODE_PRIVATE;

public class UtilPreferences {

    public static void readPreference(Activity activity){
        SharedPreferences.Editor editor = activity.getPreferences(MODE_PRIVATE).edit();
        editor.putInt(Constant.ID, id);
        editor.apply();
    }
}
