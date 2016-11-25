package com.github.marhary.saveurlife.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.github.marhary.saveurlife.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.app_preferences);
    }
}
