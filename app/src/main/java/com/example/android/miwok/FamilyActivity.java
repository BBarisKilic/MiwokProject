package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> family = new ArrayList<Word>();

        family.add(new Word("әpә","father"));
        family.add(new Word("әṭa","mother"));
        family.add(new Word("angsi","son"));
        family.add(new Word("tune","daughter"));
        family.add(new Word("taachi","older brother"));
        family.add(new Word("chalitti","younger brother"));
        family.add(new Word("teṭe","older sister"));
        family.add(new Word("kolliti","younger sister"));
        family.add(new Word("ama","grandmother"));
        family.add(new Word("paapa","grandfather"));

        WordAdapter Adapter2 = new WordAdapter(this, family);

        ListView listView2 = (ListView) findViewById(R.id.list);

        listView2.setAdapter(Adapter2);
    }
}
