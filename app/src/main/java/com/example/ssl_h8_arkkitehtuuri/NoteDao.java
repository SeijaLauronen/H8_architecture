package com.example.ssl_h8_arkkitehtuuri;
//https://www.youtube.com/watch?v=0cg09tlAAQ0  OSA 3,alku
//Tämän jälkeen tee database

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert //Tämän päällä kun painaa CTRL + B, näkee koodin
    void insert(Note note);
    @Update
    void update (Note note);
    @Delete
    void delete (Note note);
    @Query("DELETE FROM note_table")
    void deleteAllNotes();
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes(); //heti kun note_table:n sisältö muutuu, LiveData päivitää List<Note>:n sisällön, ja aktiviteetille tulee huomautus
}
