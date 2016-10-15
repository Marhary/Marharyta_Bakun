package com.example.margo.flavours;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.margo.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int num = BuildConfig.FOO + 1;
        boolean bul = !BuildConfig.LOG;
        String str = BuildConfig.FOO_STRING.concat(BuildConfig.FOO_STRING);

        if (Constants.type == Constants.Type.FREE) {
            Log.i("TAG", "FREE VERSION");
        } else if (Constants.type == Constants.Type.PAID) {
            Log.i("TAG", "PAID VERSION");
        }
    }
}
