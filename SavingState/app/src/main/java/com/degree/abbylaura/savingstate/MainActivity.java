package com.degree.abbylaura.savingstate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    EditText notesEditText;
    Button btnSettings;
    private static final int SETTINGS_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // BUNDLE = bunch of key, value pairs
        // If we want to be able to ensure data is saved if orientation of device changes
        // or OS shuts down activity, we save data in savedInstanceState

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesEditText = (EditText) findViewById(R.id.notesEditText);


        if(savedInstanceState != null){
            //there is data

            String notes = savedInstanceState.getString("NOTES");

            notesEditText.setText(notes);
        }

        //retrieve saved data
        //first parameter = NOTES = key we are looking for
        //second parameter = EMPTY = value to return if this preference does not exisits
        //returns preference value if it exists or defvalue if not
        String spNotes = getPreferences(Context.MODE_PRIVATE).getString("NOTES", "EMPTY");

        if(!spNotes.equals("EMPTY")){
            notesEditText.setText(spNotes);
        }

        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intentPreferences = new Intent(getApplicationContext(),
                        SettingsActivity.class);

                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SETTINGS_INFO){
            updateNoteText();
        }
    }

    private void updateNoteText(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //check if bold checkbox clicked
        if(sharedPreferences.getBoolean("pref_text_bold", false)){
            notesEditText.setTypeface(null, Typeface.BOLD);
        }else{
            notesEditText.setTypeface(null, Typeface.NORMAL);

        }

        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");

        float textSizeFloat = Float.parseFloat(textSizeStr);

        notesEditText.setTextSize(textSizeFloat);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //called whenever OS kills application (not user)

        //save value to a key
        outState.putString("NOTES", notesEditText.getText().toString());

        super.onSaveInstanceState(outState);


    }


    //how do you save data if user shuts down application?
    //used shared preferences (key value pairs once again)
    private void saveSettings(){

        SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        //mode_private means only our app can read our data
        //mode_world_ readable or writable means any can read or write respectively

        spEditor.putString("NOTES", notesEditText.getText().toString());

        spEditor.commit();

    }

    //but how do you know WHEN app has been forced closed by user?
    @Override
    protected void onStop() {
        saveSettings();

        super.onStop();
    }
}
