package com.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mAudioResourceId;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String vDefaultTranslation, String vMiwokTranslation, int vImageResourseId, int vAudioResourceId) {
        mDefaultTranslation = vDefaultTranslation;
        mMiwokTranslation = vMiwokTranslation;
        mImageResourceId = vImageResourseId;
        mAudioResourceId = vAudioResourceId;
    }

    public Word(String vDefaultTranslation, String vMiwokTranslation, int vAudioResourceId) {
        mDefaultTranslation = vDefaultTranslation;
        mMiwokTranslation = vMiwokTranslation;
        mAudioResourceId = vAudioResourceId;
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

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mAudioResourceId=" + mAudioResourceId +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
}
