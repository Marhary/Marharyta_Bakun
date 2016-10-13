package com.example.margo.google_cloud_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import servlets.backend.MyEndpoint;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    MyEndpoint endpoint = new MyEndpoint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(endpoint.getAnswer().getData());

    }
}
