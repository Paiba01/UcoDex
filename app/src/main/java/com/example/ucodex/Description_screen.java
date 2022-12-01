package com.example.ucodex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ucodex.interfaces.PokeDataService;
import com.example.ucodex.models.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Description_screen extends AppCompatActivity {

    private static final String TAG = "UcoDex";
    private Retrofit retrofit;
    String pokemonName = "";

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

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            pokemonName = extras.getString("name");
        }

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerPokemon(pokemonName);


    }

    private void obtenerPokemon(String pokemonName) {
        PokeDataService service = retrofit.create(PokeDataService.class);
        Call<Pokemon> pokemonCall = service.ObtenerPokemon(this.pokemonName);

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(response.isSuccessful()) {

                    Pokemon p = response.body();
                    String pName = p.getName();
                    int pHeight = p.getHeight();
                    int pId = p.getId();

                    Log.e(TAG, "Nombre del pokemon: " + pName);
                    Log.e(TAG, "Altura del pokemon: " + pHeight);
                    Log.e(TAG, "ID del pokemon: " + pId);

                } else{
                    Log.e(TAG," onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG," onFailure:" + t.getMessage());
            }
        });

    }
}