package com.example.ssl_h8_arkkitehtuuri;
//https://www.youtube.com/watch?v=reSPN7mgshI

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//Tokana vasta tuonne hakasten sisään nuo Adapter ja Holder, ja sitten ALT+Enter, niin saa metodit
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>(); //alustetaan, ettei käytetä nullina
    //Nämä tuli 3:n aautmatic -->
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDecription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    // <--

    // 4.Tämä tehdään itse
    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged(); //tämä muutetaan vielä myöhemmin (nyt videon 6 mukaan)
    }

// Tämä ekana: -->
    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private  TextView textViewDecription;
        private TextView textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.text_view_title);
            textViewDecription=itemView.findViewById(R.id.text_view_description);
            textViewPriority=itemView.findViewById(R.id.text_view_priority);
        }
    }
    // <--
}
