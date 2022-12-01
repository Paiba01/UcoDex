package com.example.ucodex.interfaces;

import com.example.ucodex.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeDataService {
    @GET("{name}/")
    Call<Pokemon> ObtenerPokemon(@Path("name") String pokemonName);
}
