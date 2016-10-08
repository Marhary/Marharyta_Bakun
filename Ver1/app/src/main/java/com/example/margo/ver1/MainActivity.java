package com.example.margo.ver1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.TextureView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView textView;
    CheckBox checkBoxBold;
    CheckBox checkBoxItelic;
    Switch showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        textView = (TextView) findViewById(R.id.textView);
        checkBoxBold = (CheckBox) findViewById(R.id.bold);
        checkBoxItelic = (CheckBox) findViewById(R.id.itelic);
        showText = (Switch) findViewById(R.id.switchtext);


        checkBoxBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxBold.isChecked()) {
                    textView.setTypeface(null, Typeface.BOLD);
                } else textView.setTypeface(null, Typeface.NORMAL);
            }
        });

        checkBoxItelic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxItelic.isChecked()) {
                    textView.setTypeface(null, Typeface.ITALIC);
                } else textView.setTypeface(null, Typeface.NORMAL);
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case (R.id.radioButton):
                        textView.setTextColor(Color.MAGENTA);
                        break;
                    case (R.id.radioButton1):
                        textView.setTextColor(Color.CYAN);
                        break;
                    case (R.id.fish):
                        textView.setTextColor(Color.DKGRAY);
                        break;

                }
            }
        });
    }


}


