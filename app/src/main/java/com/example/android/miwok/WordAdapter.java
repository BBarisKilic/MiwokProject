package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eopeace on 12/9/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int backGroundColor;

    public WordAdapter(Context context,  List<Word> objects, int background) {
        super(context, 0, objects);
        backGroundColor=background;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word currentWord= getItem(position);
        View listView= convertView;
        if(listView==null){
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.items_list, parent, false);
        }


        TextView mivok=(TextView) listView.findViewById(R.id.miwok_textView);
        mivok.setText(currentWord.getMivokTranslation());


        TextView english=(TextView) listView.findViewById(R.id.english_textView);
        english.setText(currentWord.getDefaultTranslation());

        ImageView image = (ImageView) listView.findViewById(R.id.images_i);

        if(currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageSourcesID());
            image.setVisibility(View.VISIBLE);
        }else{
            image.setVisibility(View.GONE);
        }


        View backColor=listView.findViewById(R.id.textBackGround);
        int color= ContextCompat.getColor(getContext(),backGroundColor);
        backColor.setBackgroundColor(color);

        return listView;
    }
}
