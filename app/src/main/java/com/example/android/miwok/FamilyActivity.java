package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    //global mediaplayer hem itemclick dinlerken hemnde hafıza boşaltırken çağirmak için.
    private MediaPlayer mediaPlayer;

    //kaynak boşaltıcak fonksiyonu çağirmak için sesin bitmesini bekleyen method.
    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaSources();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> family = new ArrayList<Word>();

        family.add(new Word("әpә","father",R.drawable.family_father,R.raw.family_father));
        family.add(new Word("әṭa","mother",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("angsi","son",R.drawable.family_son,R.raw.family_son));
        family.add(new Word("tune","daughter",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("taachi","older brother",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("chalitti","younger brother",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("teṭe","older sister",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("kolliti","younger sister",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word("ama","grandmother",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word("paapa","grandfather",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter Adapter2 = new WordAdapter(this, family, R.color.category_family);

        ListView listView2 = (ListView) findViewById(R.id.list);

        listView2.setAdapter(Adapter2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //hangi sekmede bastığımızı kayıt ediyor böylece onun sesini çağiriyoruz.
                Word word = family.get(position);
                //kullanıcı hızlıca sekmelere basiyorsa önceki sesi boşaltiyor böylece kaynak açılıyor setoncompletion beklenmeden.
                releaseMediaSources();
                //yeni kaynak çağiriliyor, oluşturuluyor.
                mediaPlayer=MediaPlayer.create(FamilyActivity.this, word.getMusicID());
                //ses çalınıyor.
                mediaPlayer.start();
                //ses çalması bitince hafızadan atiliyor. yukarıda global olarak tanımlandı method...böylece her seferinde oluşturulup boşuna hafıza yenilmeyecek.
                mediaPlayer.setOnCompletionListener(mcompletionListener);
            }
        });

    }

    //hafızadan ses kaynağini boşaltan fonksiyon
    private void releaseMediaSources(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
