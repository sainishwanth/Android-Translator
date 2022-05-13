package com.example.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private EditText from;
    private EditText to;
    private Translate_main obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        from = (EditText) findViewById(R.id.FromLang);
        to = (EditText) findViewById(R.id.ToLang);
        MaterialButton submitButton = (MaterialButton) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from.getText().toString().equals("") || to.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    obj = new Translate_main(from.getText().toString(), to.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), Translate_main.class);
                    startActivity(intent);
                }
            }
        });
    }
}