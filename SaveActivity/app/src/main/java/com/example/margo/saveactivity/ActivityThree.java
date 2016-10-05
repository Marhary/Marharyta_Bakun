package com.example.margo.saveactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Margo on 10/4/2016.
 */

public class ActivityThree extends Activity implements View.OnClickListener {

    Button btnYes;
    Button btnNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);

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
                intent = new Intent(this, ActivityFour.class);
                startActivity(intent);
                break;
            case R.id.btn_no:
                intent = new Intent(this, ActivityTwo.class);
                startActivity(intent);
                break;

        }
    }
}
