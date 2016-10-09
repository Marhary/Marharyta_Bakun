package com.example.margo.ver1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.margo.ver1.logic.Visible;

public class ButnActivity extends AppCompatActivity {
    Visible visible = new Visible();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butn);
        button = ((Button) findViewById(R.id.you_can_see_me));
        if (visible.isVisibility()) {
            button.setVisibility(View.VISIBLE);
        }
        else
            button.setVisibility(View.GONE);

    }
}
