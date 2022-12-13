package com.example.ucodex.interfaces;

import retrofit2.Call;
import com.example.ucodex.models.PokemonAnswer;

import retrofit2.http.GET;

public interface PokemonAPIService {
    @GET("pokemon/?limit=1154")
    Call<PokemonAnswer> obtenerListaPokemon();
}
