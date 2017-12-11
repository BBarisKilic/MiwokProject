package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    //global mediaplayer hem itemclick dinlerken hemnde hafıza boşaltırken çağirmak için.
    private MediaPlayer mediaPlayer;

    //bu bizim sesimizin arama geldiğinde,mesaj geldiğinde nasıl davranması gerektiğini ayarlamak için yarat-
    //tığımız global veri.
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    //bu durumlarda çalmayı durdur.(pause)
                    if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);

                        //bu durumda çalmaya devam et.
                    }else if (focusChange== AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();

                        //çalmayı tamamen durdur.(stop)
                    }else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaSources();
                    }
                }
            };



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

        //audio focus yapmamızı sağlayacak şekilde servisi çağırıyoruz.
        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("lutti","one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko","two",R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu","tree",R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa","four",R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka","five",R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka","six",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku","seven",R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta","eight",R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo’e","nine",R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na’aacha", "ten", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter Adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //hangi sekmede bastığımızı kayıt ediyor böylece onun sesini çağiriyoruz.
                Word word = words.get(position);
                //kullanıcı hızlıca sekmelere basiyorsa önceki sesi boşaltiyor böylece kaynak açılıyor setoncompletion beklenmeden.
                releaseMediaSources();

                //çalmak için audiofocus iste
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        //müzik streami kullan
                        AudioManager.STREAM_MUSIC,
                        //tam focus iste.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //audiofocus hazır şuan(yani hazırsa kod çalışabilir)
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //yeni kaynak çağiriliyor, oluşturuluyor.
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getMusicID());
                    //ses çalınıyor.
                    mediaPlayer.start();
                    //ses çalması bitince hafızadan atiliyor. yukarıda global olarak tanımlandı method...böylece her seferinde oluşturulup boşuna hafıza yenilmeyecek.
                    mediaPlayer.setOnCompletionListener(mcompletionListener);
                }
            }
        });
    }

    //onStop lifecycle parçalarından biri, eğer kullanıcı uygulamayı ana ekran tuşuna basıp başka bir uygulamaya geçerse yada ana menüye dönerse çalışır.
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaSources();
    }

    //hafızadan ses kaynağini boşaltan fonksiyon
    private void releaseMediaSources(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
