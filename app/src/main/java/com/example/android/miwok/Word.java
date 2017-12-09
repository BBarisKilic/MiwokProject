package com.example.android.miwok;

/**
 * Created by eopeace on 12/9/17.
 */

public class Word {
    private String mivokTranslation;
    private String defaultTranslation;
    private int imageID = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String mivok,String english){
        mivokTranslation=mivok;
        defaultTranslation=english;
    }

    public Word(String mivok,String english,int resourceID){
        mivokTranslation=mivok;
        defaultTranslation=english;
        imageID=resourceID;
    }
    public String getMivokTranslation(){
        return mivokTranslation;
    }
    public String getDefaultTranslation(){
        return defaultTranslation;
    }
    public int getImageSourcesID(){
        return imageID;
    }
    public boolean hasImage(){
        return imageID != NO_IMAGE_PROVIDED;
    }
}
