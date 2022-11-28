package com.example.ucodex;

import android.telecom.Call;
import retrofit2.http.GET;

public interface PokemonAPIService {
    @GET("pokemon/?limit=50")
    Call getPokemon();
}
