package com.example.ucodex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Description_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_screen);
        //Boton de atras
        ImageButton buttonClick = (ImageButton) findViewById(R.id.backButton);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Description_screen.this, SecondScreen.class));
            }
        });

        //Recuperación de información
        String pokemonName = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            pokemonName = extras.getString("name");
        }


    }
}