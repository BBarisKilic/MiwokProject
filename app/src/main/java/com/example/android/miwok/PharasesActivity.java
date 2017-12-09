package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PharasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> phrases = new ArrayList<Word>();

        phrases.add(new Word("minto wuksus","Where are you going?"));
        phrases.add(new Word("tinnә oyaase'nә","What is your name?"));
        phrases.add(new Word("oyaaset...","My name is..."));
        phrases.add(new Word("michәksәs?","How are you feeling?"));
        phrases.add(new Word("kuchi achit","I’m feeling good."));
        phrases.add(new Word("әәnәs'aa?","Are you coming?"));
        phrases.add(new Word("hәә’ әәnәm","Yes, I’m coming."));
        phrases.add(new Word("әәnәm","I’m coming."));
        phrases.add(new Word("yoowutis","Let’s go."));
        phrases.add(new Word("әnni'nem","Come here."));

        WordAdapter Adapter4 = new WordAdapter(this, phrases, R.color.category_phrases);

        ListView listView4 = (ListView) findViewById(R.id.list);

        listView4.setAdapter(Adapter4);
    }
}
