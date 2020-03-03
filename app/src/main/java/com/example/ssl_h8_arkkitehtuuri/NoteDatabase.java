package com.example.ssl_h8_arkkitehtuuri;
// https://www.youtube.com/watch?v=0cg09tlAAQ0 OSA 3 loppuosa

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//@Database(entities = {Note.class, Jokumuukin.class}, version =1)
@Database(entities = Note.class, version =1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();
    //create singleton = use synchronized
    // Kaatuu buildissa: throw new RuntimeException("Cannot create an instance of " + modelClass, e);
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            //ei käytetä new:ta, koska abstrakti:
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database") //Tämän luokka
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() //auttaisko tämmönen, ettei kaadu?
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void,Void> {
        private NoteDao noteDao;
        private PopulateDBAsyncTask(NoteDatabase db){
            noteDao=db.noteDao();
        }
        //Ctrl + O tai ala kirjoittaa doing
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","Description 1",1));
            noteDao.insert(new Note("Title 2","Description 2",2));
            noteDao.insert(new Note("Title 3","Description 3",3));

            return null;
        }
    }


}
