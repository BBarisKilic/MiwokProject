package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PharasesActivity extends AppCompatActivity {

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

        final ArrayList<Word> phrases = new ArrayList<Word>();

        phrases.add(new Word("minto wuksus","Where are you going?",R.raw.phrase_where_are_you_going));
        phrases.add(new Word("tinnә oyaase'nә","What is your name?",R.raw.phrase_what_is_your_name));
        phrases.add(new Word("oyaaset...","My name is...",R.raw.phrase_my_name_is));
        phrases.add(new Word("michәksәs?","How are you feeling?",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("kuchi achit","I’m feeling good.",R.raw.phrase_im_feeling_good));
        phrases.add(new Word("әәnәs'aa?","Are you coming?",R.raw.phrase_are_you_coming));
        phrases.add(new Word("hәә’ әәnәm","Yes, I’m coming.",R.raw.phrase_yes_im_coming));
        phrases.add(new Word("әәnәm","I’m coming.",R.raw.phrase_im_coming));
        phrases.add(new Word("yoowutis","Let’s go.",R.raw.phrase_lets_go));
        phrases.add(new Word("әnni'nem","Come here.",R.raw.phrase_come_here));

        WordAdapter Adapter4 = new WordAdapter(this, phrases, R.color.category_phrases);

        ListView listView4 = (ListView) findViewById(R.id.list);

        listView4.setAdapter(Adapter4);

        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //hangi sekmede bastığımızı kayıt ediyor böylece onun sesini çağiriyoruz.
                Word word = phrases.get(position);
                //kullanıcı hızlıca sekmelere basiyorsa önceki sesi boşaltiyor böylece kaynak açılıyor setoncompletion beklenmeden.
                releaseMediaSources();
                //yeni kaynak çağiriliyor, oluşturuluyor.
                mediaPlayer=MediaPlayer.create(PharasesActivity.this, word.getMusicID());
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
