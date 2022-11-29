package com.example.ucodex;

import retrofit2.Call;
import com.example.ucodex.models.PokemonAnswer;

import retrofit2.http.GET;

public interface PokemonAPIService {
    @GET("pokemon/?limit=50")
    Call<PokemonAnswer> obtenerListaPokemon();
}
