package com.example.piero.es11112016listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> mDatabase;

    private void initDB(){
        mDatabase = new ArrayList<Item>();
        for(int vIndex = 0; vIndex < 1000; vIndex++){
            Item vItem = new Item(vIndex);
            mDatabase.add(vItem);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Qui per esempio!!!
        initDB();

        CustomAdapter vAdapter = new CustomAdapter(this, mDatabase);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(vAdapter);
    }
}
