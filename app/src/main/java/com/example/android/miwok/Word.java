package com.example.android.miwok;

/**
 * Created by eopeace on 12/9/17.
 */

public class Word {
    private String mivokTranslation;
    private String defaultTranslation;
    private int imageID = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int musicID;

    //phareses'ta imaj yok onun için bu ona özel bir constructor.
    public Word(String mivok,String english, int musicResoucesID){
        mivokTranslation=mivok;
        defaultTranslation=english;
        musicID=musicResoucesID;
    }

    public Word(String mivok,String english,int resourceID,int musicResourcesID){
        mivokTranslation=mivok;
        defaultTranslation=english;
        imageID=resourceID;
        musicID=musicResourcesID;
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
    public int getMusicID(){
        return musicID;
    }
}
