package com.example.ucodex;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.example.ucodex.models.Pokemon;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private ArrayList<Pokemon> originalList;
    private Context context;

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
        this.originalList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageButton);

        holder.fotoImageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(context, Description_screen.class);
                intent.putExtra("name",p.getName());
                context.startActivity(intent);
            }
        });;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String strSearch){

        if(strSearch.length()==0){
            dataset.clear();
            dataset.addAll(originalList);
        }
        else{
            dataset.clear();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Pokemon> collect = originalList.stream()
                        .filter(i->i.getName().contains(strSearch))
                        .collect(Collectors.toList());

                dataset.addAll(collect);
            }
            else{
                dataset.clear();
                for(Pokemon i : originalList){
                    if(i.getName().contains(strSearch)){
                        dataset.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
        originalList.addAll(dataset);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton fotoImageButton;
        private TextView nombreTextView;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            fotoImageButton = (ImageButton) itemView.findViewById(R.id.pokemon_button);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
        }
    }

}
