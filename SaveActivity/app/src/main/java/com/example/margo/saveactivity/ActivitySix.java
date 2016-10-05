package com.example.margo.saveactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitySix extends AppCompatActivity implements View.OnClickListener {

    Button btnYes;
    Button btnNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.six);

        btnYes = (Button)findViewById(R.id.btn_yes);
        btnNo = (Button)findViewById(R.id.btn_no);

        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_yes:
                intent = new Intent(this, ActivitySeven.class);
                startActivity(intent);
                break;
            case R.id.btn_no:
                intent = new Intent(this, ActivityFive.class);
                startActivity(intent);
                break;

        }
    }
}

