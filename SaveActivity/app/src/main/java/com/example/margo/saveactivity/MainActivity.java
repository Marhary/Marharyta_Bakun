package com.example.margo.saveactivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences prefer = getPreferences(MODE_PRIVATE);
        final Intent intent;
        if (checkFirstLaunch(prefer)) {
            saveInstance(prefer);
            intent = new Intent(this, ActivityTwo.class);
        }
        else
            intent=new Intent(this,ActivitySeven.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void saveInstance(SharedPreferences pref){
       SharedPreferences.Editor prefEdit=pref.edit();
       prefEdit.putBoolean("first_launch",false);
       prefEdit.apply();
   }

    private boolean checkFirstLaunch(SharedPreferences pref){
        return pref.getBoolean("first_launch",true);
    }
}
