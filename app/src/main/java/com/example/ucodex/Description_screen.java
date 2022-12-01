package com.example.ucodex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ucodex.interfaces.PokeDataService;
import com.example.ucodex.models.Pokemon;
import com.example.ucodex.models.PokemonType;
import com.example.ucodex.models.types;

import java.util.ArrayList;

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

                    TextView nameTextView;
                    nameTextView = (TextView) findViewById(R.id.pName);
                    nameTextView.setText(p.getName());

                    TextView PesoTextView;
                    PesoTextView = (TextView) findViewById(R.id.pWeight);
                    PesoTextView.setText(String.valueOf(p.getWeight()));

                    TextView AlturaTextView;
                    AlturaTextView = (TextView) findViewById(R.id.pHeight);
                    AlturaTextView.setText(String.valueOf(p.getHeight()));

                    TextView IDTextView;
                    IDTextView = (TextView) findViewById(R.id.pId);
                    IDTextView.setText(String.valueOf(p.getId()));


                    ArrayList<types> typeList = p.getTypes();
/*
                    TextView type1TextView;
                    type1TextView = (TextView) findViewById(R.id.pType1);
                    type1TextView.setText(typeList.get(0).getPokemonType().getName());
                    Log.e(TAG,"tipo1: "+ typeList.get(0).getPokemonType().getName());

                    if(typeList.get(1) != null){
                        Log.e(TAG,"tipo2: "+ typeList.get(1).getPokemonType().getName());
                        TextView type2TextView;
                        type2TextView = (TextView) findViewById(R.id.pType2);
                        type2TextView.setText(typeList.get(1).getPokemonType().getName());

                    }
                    TextView type2TextView;
                    type2TextView = (TextView) findViewById(R.id.pType2);
                    type2TextView.setText(typeList.get(1).getPokemonType().getName());

*/

                    for(int i=0;i< typeList.size();i++){
                        types t = typeList.get(i);

                        if(i==0){
                            TextView typeTextView;
                            typeTextView = (TextView) findViewById(R.id.pType1);
                            typeTextView.setText(t.getPokemonType().getName());
                            Log.e(TAG,"tipo1: "+ t.getPokemonType().getName());
                        }
                        if(i==1){
                            TextView typeTextView;
                            typeTextView = (TextView) findViewById(R.id.pType2);
                            typeTextView.setText(t.getPokemonType().getName());
                            Log.e(TAG,"tipo2: "+ t.getPokemonType().getName());
                        }
                    }


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