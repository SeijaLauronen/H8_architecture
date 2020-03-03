package com.example.ssl_h8_arkkitehtuuri;

import android.app.Application;
import android.os.AsyncTask;
import android.service.voice.VoiceInteractionService;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

// https://www.youtube.com/watch?v=HhmA9S53XV8 OSA 4

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    public NoteRepository(Application application){//Application on Contekstin aliluokka
        NoteDatabase database = NoteDatabase.getInstance(application); //KAATUU TÄHÄN
        noteDao = database.noteDao(); //noteDao on abstrakti metodi. Normaalisti abst metodia ei voi kutsua, mutta... Room tekee jotain automaattisesti...
        allNotes =noteDao.getAllNotes();
    }
//KTS mitä DAOs:ssa on:
    //Koska MainThreadissa ei sallita DB-operaatioita, pitää backgoundThrediin tehdä koodi itse
    public void insert (Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void delete (Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void update (Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes () {
        new DeleteAllNotesAsyncTask(noteDao).execute();

    }

    //Room tekee tämän haut automaattisesti, tekeen ne BackGroundThreadissa
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //jotta ei tule muistivuotoja, pitää tehdä tästä static. Tämä on noit 4:ää ekaa varten
    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        //konstruktori
        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note...notes){ //kun alkoi kirjoittaa doing tuli nuo automatic
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        //konstruktori
        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note...notes){ //kun alkoi kirjoittaa doing tuli nuo automatic
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        //konstruktori
        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note...notes){ //kun alkoi kirjoittaa doing tuli nuo automatic
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        //konstruktori
        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void...voids){ //kun alkoi kirjoittaa doing tuli nuo automatic
            noteDao.deleteAllNotes();
            return null;
        }
    }






}
