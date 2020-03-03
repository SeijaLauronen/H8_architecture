package com.example.ssl_h8_arkkitehtuuri;

//YOUTUBE: https://www.youtube.com/watch?v=Jwdty9jQN0E

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private  String title;
    private  String description;
    private int priority;
    //Paina Alt+ins tai oikea hiiren nappi ja generate

//Jos olis määritelty publikiksi, ei tarvittaisi getereitä ja settereitä
    public Note(String title, String description, int priority) {
        //tähän ei mukaan tuota id:tä
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //Setteri tarvitaan vain tälle
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
