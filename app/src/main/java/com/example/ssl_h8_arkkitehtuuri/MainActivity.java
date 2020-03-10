package com.example.ssl_h8_arkkitehtuuri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST =1;  //10.3.2010 7 videon loppu, kirjoita psfi

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ViewModelProviders --> ViewModelProvider
        //annotationProcessor "androidx.lifecycle:lifecycle-compiler:2.0.0"
        //annotationProcessor "androidx.lifecycle:lifecycle-compiler:2.2.0"
        /* //Vanha tapa:
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        */

        // 10.3.2020 7 videon lopussa
        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        //6.s videon lopusta, sen jälkeen kun jo tuo Toasti on jo tehty
        //Mihinkähän tämä tulee siinä omassa Frag-harjoiteuksessa? -onActivityCreated
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);



        /* MALLI moodle aineistosta:
        ViewModelProvider viewModelProvider = new ViewModelProvider(getActivity());
        this.OmaFragmentViewModelInstance = viewModelProvider.get(OmaViewModel.class);
         */
        //ViewModelProvider viewModelProvider = new ViewModelProvider(getActivity()); // ei toimi tämä
        //Malli täältä:
        //https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#13
        //mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        //no lopultakin, näin onnistu:
        //onko nyt jotenkin eroa siinä muistivuotojutussa, kun tuolla Providers:lla ei pitänyt käyttää new:ta,
        // että se new hanskataan automaattisesti?
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        //observe on livedatan metodi. Kirjota this:n jälkeen new Obs, ja valitse listalta, niin tulee automaattisesti loput
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //TODO: update recylerView
                //Kirjoita Toa ja valitse alin rivi:
                //Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setNotes(notes);
            }
        });

    }

    //10.3.2020 7 videon loppu 20:50
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1);
            Note note = new Note(title,description,priority);
            noteViewModel.insert(note);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else { //canceled
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }//Tämän jälkeen manifestiin pitää lisätä AddnoteActivitylle parentactivityksi Mainactivity ja MainActivitylle launchMode="singleTop
}
