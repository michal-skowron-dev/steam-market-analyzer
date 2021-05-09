package com.am.steammarketanalyzer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.koushikdutta.ion.Ion;

public class ListAdapter extends ArrayAdapter<String> {

    private final Context context;

    private final String[] imageLinks, names, links;
    private final boolean[] states;

    private final boolean mainState;

    ListAdapter(Context context,
                String[] imageLinks, String[] names, String[] links, boolean[] states,
                boolean mainState) {

        super(context, R.layout.list_row, R.id.image, imageLinks);

        this.context = context;

        this.imageLinks = imageLinks;
        this.names = names;
        this.links = links;
        this.states = states;

        this.mainState = mainState;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View listRow = layoutInflater.
                inflate(R.layout.list_row, parent, false);

        ImageView imageView = listRow.findViewById(R.id.image);
        TextView titleView = listRow.findViewById(R.id.title);
        TextView subtitleView = listRow.findViewById(R.id.subtitle);

        Ion.with(imageView).load(imageLinks[position]);
        titleView.setText(names[position]);
        subtitleView.setText(links[position]);

        if (states[position]) {
            titleView.setTextColor(ContextCompat.getColor(context, R.color.white));
            subtitleView.setTextColor(ContextCompat.getColor(context, R.color.white));
        }

        listRow.setEnabled(!mainState);
        return listRow;
    }

    @Override
    public boolean isEnabled(int position) { return !mainState; }
}