package com.example.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner fromspinner;
    private Spinner tospinner;
    private String fromString;
    private String toString;
    private Translate_main obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fromspinner = findViewById(R.id.fromspinner);
        tospinner =  findViewById(R.id.tospinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try{
            fromspinner.setAdapter(adapter);
            tospinner.setAdapter(adapter);
        }catch(NullPointerException e){}
        fromspinner.setOnItemSelectedListener(this);
        tospinner.setOnItemSelectedListener(this);
        MaterialButton submitButton = (MaterialButton) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fromString.equals("Select") || toString.equals("Select")) {
                    Toast.makeText(MainActivity.this, "Required Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
                else if (fromString.equals(toString)){
                    Toast.makeText(MainActivity.this, "Please select unique languages.", Toast.LENGTH_SHORT).show();
                }
                else {
                    switch (fromString){
                        case "English":
                            fromString = "en";
                            break;
                        case "Hindi":
                            fromString = "hi";
                            break;
                        case "French":
                            fromString = "fr";
                            break;
                        case "Japanese":
                            fromString = "ja";
                            break;
                        case "German":
                            fromString = "de";
                            break;
                        case "Spanish":
                            fromString = "es";
                            break;
                        case "Kannada":
                            fromString = "kn";
                            break;
                    }
                    switch (toString) {
                        case "English":
                            toString = "en";
                            break;
                        case "Hindi":
                            toString = "hi";
                            break;
                        case "French":
                            toString = "fr";
                            break;
                        case "Japanese":
                            toString = "ja";
                            break;
                        case "German":
                            toString = "de";
                            break;
                        case "Spanish":
                            toString = "es";
                            break;
                        case "Kannada":
                            toString = "kn";
                            break;
                    }
                    obj = new Translate_main(fromString, toString);
                    Intent intent = new Intent(getApplicationContext(), Translate_main.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         switch (parent.getId()){
             case R.id.fromspinner:
                 fromString = parent.getItemAtPosition(position).toString();
                 break;
             case R.id.tospinner:
                 toString = parent.getItemAtPosition(position).toString();
                 break;
         }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}