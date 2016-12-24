package com.github.marhary.saveurlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        TextView yLabel = (TextView)findViewById(R.id.yLabel);
        TextView mLabel = (TextView)findViewById(R.id.mLabel);
        TextView dLabel = (TextView)findViewById(R.id.dLabel);
        TextView eLabel = (TextView)findViewById(R.id.eLabel);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.UK);
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/",0);


        yLabel.setText(values[0]);
        mLabel.setText(values[1]);
        dLabel.setText(values[2]);
        eLabel.setText(values[3]);

    }
}
