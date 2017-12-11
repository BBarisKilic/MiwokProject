package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

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

        final ArrayList<Word> colors = new ArrayList<Word>();

        colors.add(new Word("weṭeṭṭi","red",R.drawable.color_red,R.raw.color_red));
        colors.add(new Word("chokokki","green",R.drawable.color_green,R.raw.color_green));
        colors.add(new Word("ṭakaakki","brown",R.drawable.color_brown,R.raw.color_brown));
        colors.add(new Word("ṭopoppi","gray",R.drawable.color_gray,R.raw.color_gray));
        colors.add(new Word("kululli","black",R.drawable.color_black,R.raw.color_black));
        colors.add(new Word("kelelli","white",R.drawable.color_white,R.raw.color_white));
        colors.add(new Word("ṭopiisә","dusty yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colors.add(new Word("chiwiiṭә","mustard yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter Adapter3 = new WordAdapter(this, colors,R.color.category_colors);

        ListView listView3 = (ListView) findViewById(R.id.list);

        listView3.setAdapter(Adapter3);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //hangi sekmede bastığımızı kayıt ediyor böylece onun sesini çağiriyoruz.
                Word word = colors.get(position);
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
                    mediaPlayer = MediaPlayer.create(ColorActivity.this, word.getMusicID());
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
