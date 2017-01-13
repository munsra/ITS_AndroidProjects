package com.example.piero.es04112016intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        TextView vText = (TextView) findViewById(R.id.txt_data);

        Intent vIntent = getIntent();
        Uri vData = vIntent.getData();
        if(vData != null){
            vText.setText(vData.toString());
        }

    }
}
