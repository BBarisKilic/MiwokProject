package com.example.android.miwok;

/**
 * Created by eopeace on 12/9/17.
 */

public class Word {
    private String mivokTranslation;
    private String defaultTranslation;

    public Word(String mivok,String english){
        mivokTranslation=mivok;
        defaultTranslation=english;
    }
    public String getMivokTranslation(){
        return mivokTranslation;
    }
    public String getDefaultTranslation(){
        return defaultTranslation;
    }
}
