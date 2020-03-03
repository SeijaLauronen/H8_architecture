package com.example.ssl_h8_arkkitehtuuri;
// https://www.youtube.com/watch?v=JLwW5HivZg4&t=322s OSA 5

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    //application on contextin aliluokka. Muistivuodon takia ei saa täällä viitata contextiin...
    //jos on viittaus toiseen aktiviteettiin ja jompikumpi suljetaan, voi tulla muistivuoteooo tms...
    //mutta conteksti pitää kuitenkin pystyä välittämään DB:lle
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note  note){
        repository.insert(note);
    }
    public void update(Note  note){
        repository.update(note);
    }
    public void delete(Note  note){
        repository.delete(note);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
