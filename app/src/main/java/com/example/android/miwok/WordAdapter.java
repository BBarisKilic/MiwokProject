package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eopeace on 12/9/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter(Context context,  List<Word> objects) {
        super(context, 0, objects);
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

        return listView;
    }
}
