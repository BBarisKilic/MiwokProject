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

        family.add(new Word("әpә","father",R.drawable.family_father));
        family.add(new Word("әṭa","mother",R.drawable.family_mother));
        family.add(new Word("angsi","son",R.drawable.family_son));
        family.add(new Word("tune","daughter",R.drawable.family_daughter));
        family.add(new Word("taachi","older brother",R.drawable.family_older_brother));
        family.add(new Word("chalitti","younger brother",R.drawable.family_younger_brother));
        family.add(new Word("teṭe","older sister",R.drawable.family_older_sister));
        family.add(new Word("kolliti","younger sister",R.drawable.family_younger_sister));
        family.add(new Word("ama","grandmother",R.drawable.family_grandmother));
        family.add(new Word("paapa","grandfather",R.drawable.family_grandfather));

        WordAdapter Adapter2 = new WordAdapter(this, family, R.color.category_family);

        ListView listView2 = (ListView) findViewById(R.id.list);

        listView2.setAdapter(Adapter2);
    }
}
