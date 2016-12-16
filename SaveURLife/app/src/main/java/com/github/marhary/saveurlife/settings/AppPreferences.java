package com.github.marhary.saveurlife.settings;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.constants.IConstantSettings;
import com.github.marhary.saveurlife.fragments.SettingsFragment;

public class AppPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_detail);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentTransaction.add(android.R.id.content, settingsFragment, IConstantSettings.SETTINGS_FRAGMENT);
        fragmentTransaction.commit();

    }


}
