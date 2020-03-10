package com.example.ssl_h8_arkkitehtuuri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    //Video 7, 15:00 min kun kirjottaa psfs, saa tämän automaattisesti
    //tämä on kai amatööritapa välittää dataa, ei suositeltava...?
    public static final String EXTRA_TITLE = "com.example.ssl_h8_arkkitehtuuri.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.ssl_h8_arkkitehtuuri.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.ssl_h8_arkkitehtuuri.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);//miksi ei ota tätä, vaikka tuli automaattisesti?Ahaa, piti vaan ootella tosi pitkään...
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_title);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add note");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    //9.3.2020 pitääköhän tämä sitten tehdä mainiin vai fragementtiin...
    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
        if (title.trim().isEmpty()||description.trim().isEmpty()){
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        //9.3.2020 video 7. Mikäs tämä Intentti olikaan...
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        setResult(RESULT_OK,data);
        finish();//9.3.2020 Video 7 19:00 TODO tähän jäätiin.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item); //ALT + CTRL + L muotoilee
        }
    }


}
