package com.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> Words, int colorResourceId) {
        super(context, 0, Words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
              R.layout.list_item, parent, false
            );
        }
        Word currentWord = getItem(position);
        TextView defaultTextView = listItemView.findViewById(R.id.default_translation);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView = listItemView.findViewById(R.id.miwok_translation);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        ImageView iconView = listItemView.findViewById(R.id.list_item_icon);
        if (currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.layout_background);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
