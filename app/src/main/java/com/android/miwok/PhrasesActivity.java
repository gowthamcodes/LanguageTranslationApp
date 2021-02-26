package com.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?", "chalitti", R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.", "әәnәs'aa?", R.raw.phrase_yes_im_coming));
        phrases.add(new Word("I’m coming.","hәә’әәnәm", R.raw.phrase_im_coming));
        phrases.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phrases.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        ListView listView = findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, phrases, R.color.category_phrases);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Word phrase = phrases.get(position);
            releaseMediaPlayer();
            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(this, phrase.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(m -> {
                    releaseMediaPlayer();
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}