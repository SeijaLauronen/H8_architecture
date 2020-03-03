package com.example.ssl_h8_arkkitehtuuri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
