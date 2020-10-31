package com.example.androidlab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import name.ank.lab4.BibDatabase;
import name.ank.lab4.BibEntry;
import name.ank.lab4.Keys;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    BibDatabase database;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView Author;
        TextView JournalAndYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            Author = itemView.findViewById(R.id.author);
            JournalAndYear = itemView.findViewById(R.id.JournalAndYear);
        }
    }

    Adapter(InputStream articles) throws IOException {
        InputStreamReader reader = new InputStreamReader(articles);
        database = new BibDatabase(reader);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.biblib, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BibEntry entry = database.getEntry(position % database.size());
        holder.title.setText(entry.getField(Keys.TITLE));
        holder.Author.setText("Authors : " + entry.getField(Keys.AUTHOR));
        holder.JournalAndYear.setText("Journal : " + entry.getField(Keys.JOURNAL) + " , " + entry.getField(Keys.YEAR));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
