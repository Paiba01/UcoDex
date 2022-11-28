package com.example.ucodex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonFetchResults {
    @SerializedName("results")
    @Expose
    private ArrayList results;

    public ArrayList getResults() {
        return results;
    }
}
