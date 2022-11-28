package com.example.ucodex;

import android.content.ClipData;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.DragEvent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.example.ucodex.placeholder.PlaceholderContent;
import com.google.gson.GsonBuilder;



import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondScreen extends AppCompatActivity {
     /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private PlaceholderContent.PlaceholderItem mItem;
    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mTextView;
    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            ClipData.Item clipDataItem = event.getClipData().getItemAt(0);
            mItem = PlaceholderContent.ITEM_MAP.get(clipDataItem.getText().toString());
            updateContent();
        }
        return true;
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the placeholder content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = PlaceholderContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        PokemonAPIService pokemonApiService = retrofit.create(PokemonAPIService.class);
        Call call = pokemonApiService.getPokemon();
    }
    call.enqueue(new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful()) {
                ArrayList pokemonList = response.body().getResults();
                View recyclerView = findViewById(R.id.item_list);
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView, pokemonList);
            } else {
                Log.d("Error", "Something happened");
                return;
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            Log.d("Error", t.toString());
        }
    });


    private void updateContent() {
        if (mItem != null) {
            mTextView.setText(mItem.details);
            if (mToolbarLayout != null) {
                mToolbarLayout.setTitle(mItem.content);
            }
        }
    }

}
