package com.github.marhary.saveurlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.github.marhary.saveurlife.auth.VkAuthorizer;
import com.github.marhary.saveurlife.settings.AppPreferences;

import cloud_controller.ServletPostAsyncTask;

// TODO: 11/21/2016 rename
public class MainActivity extends AppCompatActivity {

    public static final String NOTE_ID_EXTRA = "com.example.margo.saveurlife.Identifier";
    public static final String NOTE_TITLE_EXTRA = "com.example.margo.saveurlife.Title";
    public static final String NOTE_MESSAGE_EXTRA = "com.example.margo.saveurlife.Message";
    public static final String NOTE_CATEGORY_EXTRA = "com.example.margo.saveurlife.Category";
    // TODO: 11/21/2016 does not belongs to this class
    public static final String NOTE_FRAGMENT_TO_LOAD_EXTRA = "com.example.margo.saveurlife.Fragment_To_Load";

    // TODO: 11/21/2016 does not belongs to this class
    public enum FragmentToLaunch {VIEW, EDIT, CREATE}

    @Override
    protected void onResume() {
        super.onStart();
        int id;// TODO: 11/21/2016 useless??
        if ((id = VkAuthorizer.getUserId()) > 0) {
            // TODO: 11/21/2016 move to util class work with preferences
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            // TODO: 11/21/2016 to const
            editor.putInt("Id", id);
            editor.apply();
        }
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        id = getPreferences(MODE_PRIVATE).getInt("Id", -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadPreferences();

        new ServletPostAsyncTask().execute(new Pair<Context, String>(this, "Marhary"));

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
            intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, FragmentToLaunch.CREATE);
            startActivity(intent);
            return true;
        } else if (id == R.id.authorization) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean("background_color", false);
        if (isBackgroundDark) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.content_main);
            mainLayout.setBackgroundColor(Color.parseColor("#3c3f41"));// rank = top
        }
        String notebookTitle = sharedPreferences.getString("title", "SaveURLife");
        setTitle(notebookTitle);
    }
}
