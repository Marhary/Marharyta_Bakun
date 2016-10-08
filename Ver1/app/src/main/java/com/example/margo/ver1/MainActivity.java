package com.example.margo.ver1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mKmPerHourEditText;
    private EditText mMeterPerSecEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKmPerHourEditText = (EditText) findViewById(R.id.editTextKmPerHour);
        mMeterPerSecEditText = (EditText) findViewById(R.id.editTextMetersPerSec);

        mKmPerHourEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    try {
                        double kmPerHour = Double.parseDouble(mKmPerHourEditText
                                .getText().toString());
                        double meterPerSec = kmPerHour * 0.2777777777777778;
                        mMeterPerSecEditText.setText(Double.valueOf(meterPerSec)
                                .toString());
                    } catch (NumberFormatException e) {
                        mMeterPerSecEditText.setText(R.string.errorMsg);
                        Log.d("Test", "e:" + e);
                    }
                    return true;
                }
                return false;
            }
        });


        mMeterPerSecEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    try {
                        double meterPerSec = Double.parseDouble(mMeterPerSecEditText
                                .getText().toString());
                        double kmPerHour = meterPerSec * 3.6;
                        mKmPerHourEditText.setText(Double.valueOf(kmPerHour)
                                .toString());
                    } catch (NumberFormatException e) {
                        mKmPerHourEditText.setText(R.string.errorMsg);
                        Log.d("Test", "e:" + e);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}







