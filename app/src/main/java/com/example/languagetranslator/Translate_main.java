package com.example.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Translate_main extends AppCompatActivity {
    private EditText EditView;
    private MaterialButton TranslateButton;
    private TextView TextField;
    private static String fromText;
    private static String toText;
    private String response;
    public Translate_main(){}

    public Translate_main(String from, String to){
        fromText = from;
        toText = to;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EditView = findViewById(R.id.Edit);
        TextField = findViewById(R.id.Translated);
        TranslateButton = findViewById(R.id.TranslateButton);
        TranslateButton.setOnClickListener(view -> {
            if(EditView.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    response = translate(fromText,toText,EditView.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TextField.setText(response);
            }
        });
    }
    public String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbzoj2X4Xav3CawDQYo57pY-lEUH20aU6r8JxtiIGl6H4BrEzjw/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        for (String line; (line = in.readLine()) != null; ) {
            response.append(line).append('\n');
        }
        return response.toString();
    }
}