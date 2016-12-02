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
import android.widget.LinearLayout;

import com.github.marhary.saveurlife.auth.IConstant;
import com.github.marhary.saveurlife.auth.VkAuthorizer;
import com.github.marhary.saveurlife.fragments.ListOfNotesFragment;
import com.github.marhary.saveurlife.imageLoader.LoaderActivity;
import com.github.marhary.saveurlife.settings.AppPreferences;
import com.github.marhary.saveurlife.settings.UtilPreferences;

public class ListOfNotesActivity extends AppCompatActivity {

    public static final String NOTE_ID_EXTRA = "com.example.margo.saveurlife.Identifier";
    public static final String NOTE_TITLE_EXTRA = "com.example.margo.saveurlife.Title";
    public static final String NOTE_MESSAGE_EXTRA = "com.example.margo.saveurlife.Message";
    public static final String NOTE_CATEGORY_EXTRA = "com.example.margo.saveurlife.Category";

    private int id;

    @Override
    protected void onResume() {
        super.onStart();
        if ((id = new VkAuthorizer().getUserId()) > 0) {
            UtilPreferences.readPreference(this);
        }
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        id = getPreferences(MODE_PRIVATE).getInt(IConstant.ID, -1);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AppPreferences.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_add_note) {
            Intent intent = new Intent(this, NoteDetailActivity.class);
            intent.putExtra(ListOfNotesFragment.NOTE_FRAGMENT_TO_LOAD_EXTRA, ListOfNotesFragment.FragmentToLaunch.CREATE);
            startActivity(intent);
            return true;
        } else if (id == R.id.authorization) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivityForResult(intent, 1);
            return true;
        } else if (id == R.id.loader){
            Intent intent = new Intent(this, LoaderActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean(IConstant.BACKGROUND_COLOR, false);
        if (isBackgroundDark) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.content_main);
            mainLayout.setBackgroundColor(Color.parseColor(getString(R.string.settings_colour)));
        }
        String notebookTitle = sharedPreferences.getString(IConstant.TITLE, IConstant.SAVEURLIFE);
        setTitle(notebookTitle);
    }
}
