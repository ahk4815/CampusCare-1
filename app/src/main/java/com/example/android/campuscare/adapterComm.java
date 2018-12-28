package com.example.android.campuscare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user1 on 11/14/2018.
 */

public class adapterComm extends ArrayAdapter<comments> {
    private Activity context;
    List<comments> artists;

    public adapterComm(Activity context, List<comments> artists) {
        super(context, R.layout.coms, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.coms, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.id4);
       // TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        comments artist = artists.get(position);
        textViewName.setText(artist.getcom());
      //  textViewGenre.setText(artist.getArtistGenre());

        return listViewItem;
    }
}