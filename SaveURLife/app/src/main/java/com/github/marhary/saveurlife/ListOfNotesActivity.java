package com.github.marhary.saveurlife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.github.marhary.saveurlife.auth.VkAuthorizer;
import com.github.marhary.saveurlife.constants.IConstantActivities;
import com.github.marhary.saveurlife.constants.IConstantFragments;
import com.github.marhary.saveurlife.fragments.ListOfNotesFragment;
import com.github.marhary.saveurlife.imageLoader.LoaderActivity;
import com.github.marhary.saveurlife.settings.AppPreferences;
import com.github.marhary.saveurlife.settings.UtilPreferences;

public class ListOfNotesActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onResume() {
        super.onStart();
        if ((id = new VkAuthorizer().getUserId()) > 0) {
            UtilPreferences.readPreference(this);
        }
        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        id = getPreferences(MODE_PRIVATE).getInt(IConstantActivities.ID, -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AppPreferences.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.authorization) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivityForResult(intent, 1);
            return true;
        } else if (id == R.id.loader) {
            Intent intent = new Intent(this, LoaderActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.calendar) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.notification){
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean(IConstantActivities.BACKGROUND_COLOR, false);
        if (isBackgroundDark) {
            FrameLayout mainLayout = (FrameLayout) findViewById(R.id.content_main);
            mainLayout.setBackgroundColor(Color.parseColor(getString(R.string.settings_colour)));
        }
        String notebookTitle = sharedPreferences.getString(IConstantActivities.TITLE, IConstantActivities.SAVEURLIFE);
        setTitle(notebookTitle);
    }

    public void floatAdd(View view) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra(IConstantFragments.NOTE_FRAGMENT_TO_LOAD_EXTRA, ListOfNotesFragment.FragmentToLaunch.CREATE);
        startActivity(intent);
    }
}
