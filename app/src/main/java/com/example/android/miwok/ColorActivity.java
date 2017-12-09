package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> colors = new ArrayList<Word>();

        colors.add(new Word("weṭeṭṭi","red"));
        colors.add(new Word("chokokki","green"));
        colors.add(new Word("ṭakaakki","brown"));
        colors.add(new Word("ṭopoppi","gray"));
        colors.add(new Word("kululli","black"));
        colors.add(new Word("kelelli","white"));
        colors.add(new Word("ṭopiisә","dusty yellow"));
        colors.add(new Word("chiwiiṭә","mustard yellow"));

        WordAdapter Adapter3 = new WordAdapter(this, colors);

        ListView listView3 = (ListView) findViewById(R.id.list);

        listView3.setAdapter(Adapter3);

    }
}
