package com.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String vDefaultTranslation, String vMiwokTranslation, int vImageResourseId) {
        mDefaultTranslation = vDefaultTranslation;
        mMiwokTranslation = vMiwokTranslation;
        mImageResourceId = vImageResourseId;
    }

    public Word(String vDefaultTranslation, String vMiwokTranslation) {
        mDefaultTranslation = vDefaultTranslation;
        mMiwokTranslation = vMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
