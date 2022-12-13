package com.example.ucodex;

import static android.icu.lang.UCharacter.toUpperCase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ucodex.interfaces.PokeDataService;
import com.example.ucodex.models.Pokemon;
import com.example.ucodex.models.ability;
import com.example.ucodex.models.baseStat;
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
                    float w = (float) (p.getWeight()*0.1);
                    PesoTextView.setText(w +" Kg");

                    TextView AlturaTextView;
                    AlturaTextView = (TextView) findViewById(R.id.pHeight);
                    float h = (float) (p.getHeight()*0.1);
                    AlturaTextView.setText(h + " m");

                    TextView IDTextView;
                    IDTextView = (TextView) findViewById(R.id.pId);
                    IDTextView.setText(String.valueOf(p.getId()));

                    TextView BaseExperienceTextView;
                    BaseExperienceTextView = (TextView) findViewById(R.id.pExpBase);
                    BaseExperienceTextView.setText(String.valueOf(p.getBase_experience()));

                    ArrayList<types> typeList = p.getTypes();
                    CardView card1 = (CardView) findViewById(R.id.cardType1);
                    CardView card2 = (CardView) findViewById(R.id.cardType2);
                    TextView type1TextView = (TextView) findViewById(R.id.pType1);
                    TextView type2TextView = (TextView) findViewById(R.id.pType2);

                    for(int i=0;i< typeList.size();i++){
                        types t = typeList.get(i);

                        if(i==0){

                            card2.setVisibility(card2.INVISIBLE);
                            type2TextView.setVisibility(type2TextView.INVISIBLE);
                            card2.setTranslationX(300);
                            card1.setTranslationX(300);
                            ImageView fondo;
                            String type1 = t.getPokemonType().getName();
                            String typeCopy = type1;
                            type1 = translateType(type1);
                            fondo = (ImageView) findViewById(R.id.fondoLayout);
                            type1TextView.setText(type1);
                            if(typeCopy.equals("grass")){ type1TextView.setBackgroundResource(R.color.grass); fondo.setBackgroundResource(R.color.grass);}
                            if(typeCopy.equals("fire")){ type1TextView.setBackgroundResource(R.color.fire); fondo.setBackgroundResource(R.color.fire);}
                            if(typeCopy.equals("bug")){ type1TextView.setBackgroundResource(R.color.bug); fondo.setBackgroundResource(R.color.bug);}
                            if(typeCopy.equals("dragon")){ type1TextView.setBackgroundResource(R.color.dragon); fondo.setBackgroundResource(R.color.dragon);}
                            if(typeCopy.equals("electric")){ type1TextView.setBackgroundResource(R.color.electric); fondo.setBackgroundResource(R.color.electric);}
                            if(typeCopy.equals("fairy")){type1TextView.setBackgroundResource(R.color.fairy); fondo.setBackgroundResource(R.color.fairy);}
                            if(typeCopy.equals("fighting")){ type1TextView.setBackgroundResource(R.color.fighting); fondo.setBackgroundResource(R.color.fighting);}
                            if(typeCopy.equals("flying")){ type1TextView.setBackgroundResource(R.color.flying); fondo.setBackgroundResource(R.color.flying);}
                            if(typeCopy.equals("ghost")){ type1TextView.setBackgroundResource(R.color.ghost); fondo.setBackgroundResource(R.color.ghost);}
                            if(typeCopy.equals("ground")){ type1TextView.setBackgroundResource(R.color.ground); fondo.setBackgroundResource(R.color.ground);}
                            if(typeCopy.equals("ice")){ type1TextView.setBackgroundResource(R.color.ice); fondo.setBackgroundResource(R.color.ice);}
                            if(typeCopy.equals("rock")){ type1TextView.setBackgroundResource(R.color.rock); fondo.setBackgroundResource(R.color.rock);}
                            if(typeCopy.equals("normal")){ type1TextView.setBackgroundResource(R.color.normal); fondo.setBackgroundResource(R.color.normal);}
                            if(typeCopy.equals("poison")){ type1TextView.setBackgroundResource(R.color.poison); fondo.setBackgroundResource(R.color.poison);}
                            if(typeCopy.equals("psychic")){ type1TextView.setBackgroundResource(R.color.psychic); fondo.setBackgroundResource(R.color.psychic);}
                            if(typeCopy.equals("steel")){ type1TextView.setBackgroundResource(R.color.steel); fondo.setBackgroundResource(R.color.steel);}
                            if(typeCopy.equals("water")){ type1TextView.setBackgroundResource(R.color.water); fondo.setBackgroundResource(R.color.water);}
                            if(typeCopy.equals("dark")){ type1TextView.setBackgroundResource(R.color.dark); fondo.setBackgroundResource(R.color.dark);}
                        }
                        if(i==1){

                            card2.setVisibility(card2.VISIBLE);
                            type2TextView.setVisibility(type2TextView.VISIBLE);
                            card2.setTranslationX(450);
                            card1.setTranslationX(160);
                            String type2 = t.getPokemonType().getName();
                            String typeCopy = type2;
                            type2 = translateType(type2);
                            if(typeCopy.equals("grass")) type2TextView.setBackgroundResource(R.color.grass);
                            if(typeCopy.equals("fire")) type2TextView.setBackgroundResource(R.color.fire);
                            if(typeCopy.equals("bug")) type2TextView.setBackgroundResource(R.color.bug);
                            if(typeCopy.equals("dragon")) type2TextView.setBackgroundResource(R.color.dragon);
                            if(typeCopy.equals("electric")) type2TextView.setBackgroundResource(R.color.electric);
                            if(typeCopy.equals("fairy")) type2TextView.setBackgroundResource(R.color.fairy);
                            if(typeCopy.equals("fighting")) type2TextView.setBackgroundResource(R.color.fighting);
                            if(typeCopy.equals("flying")) type2TextView.setBackgroundResource(R.color.flying);
                            if(typeCopy.equals("ghost")) type2TextView.setBackgroundResource(R.color.ghost);
                            if(typeCopy.equals("ground")) type2TextView.setBackgroundResource(R.color.ground);
                            if(typeCopy.equals("ice")) type2TextView.setBackgroundResource(R.color.ice);
                            if(typeCopy.equals("rock")) type2TextView.setBackgroundResource(R.color.rock);
                            if(typeCopy.equals("normal")) type2TextView.setBackgroundResource(R.color.normal);
                            if(typeCopy.equals("poison")) type2TextView.setBackgroundResource(R.color.poison);
                            if(typeCopy.equals("psychic")) type2TextView.setBackgroundResource(R.color.psychic);
                            if(typeCopy.equals("steel")) type2TextView.setBackgroundResource(R.color.steel);
                            if(typeCopy.equals("water")) type2TextView.setBackgroundResource(R.color.water);
                            if(typeCopy.equals("dark")) type2TextView.setBackgroundResource(R.color.dark);
                            type2TextView.setText(type2);
                        }
                    }

                    ArrayList<baseStat> statsList = p.getStats();
                    for(int i=0; i< statsList.size();i++){

                        baseStat bs = statsList.get(i);
                        if(i==0){
                            TextView statTextView;
                            statTextView = (TextView) findViewById(R.id.pHp);
                            statTextView.setText(String.valueOf(bs.getBase_stat())); //hp
                        }
                        if(i==1){
                            TextView statTextView;
                            statTextView = (TextView) findViewById(R.id.pAttack);
                            statTextView.setText(String.valueOf(bs.getBase_stat())); //attack
                        }
                        if(i==2){
                            TextView statTextView;
                            statTextView = (TextView) findViewById(R.id.pDefense);
                            statTextView.setText(String.valueOf(bs.getBase_stat())); //defense
                        }
                        if(i==3){
                            TextView statTextView;
                            statTextView = (TextView) findViewById(R.id.pSpecialA);
                            statTextView.setText(String.valueOf(bs.getBase_stat())); //special-attack
                        }
                        if(i==4){
                            TextView statTextView;
                            statTextView = (TextView) findViewById(R.id.pSpecialD);
                            statTextView.setText(String.valueOf(bs.getBase_stat())); //special-defense
                        }
                        if(i==5){
                            TextView statTextView;
                            statTextView = (TextView) findViewById(R.id.pSpeed);
                            statTextView.setText(String.valueOf(bs.getBase_stat())); //speed
                        }
                    }

                    ArrayList<ability> abilityList = p.getAbilities();
                    for(int i=0;i< abilityList.size();i++) {
                        ability a = abilityList.get(i);

                        TextView abilityTextView;
                        String ability = a.getAbility().getName();
                        ability = translateAbility(ability);
                        if(i==0){
                            abilityTextView = (TextView) findViewById(R.id.pAbility1);
                            abilityTextView.setText(ability);
                        }
                        if(i==1){
                            abilityTextView = (TextView) findViewById(R.id.pAbility3);
                            abilityTextView.setText(ability);
                        }
                        if(i==2){
                            abilityTextView = (TextView) findViewById(R.id.pAbility3);
                            abilityTextView.setText(ability);
                        }
                    }

                    ImageView spriteImageView;
                    spriteImageView = (ImageView) findViewById(R.id.spriteImage);
                    Glide.with(Description_screen.this)
                            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getId() + ".png")
                            .centerCrop()
                            .into(spriteImageView);

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

    private String translateType(String type) {
        if(type.equals("grass")) type = "Planta";
        if(type.equals("fire")) type = "Fuego";
        if(type.equals("bug")) type = "Bicho";
        if(type.equals("dragon")) type = "Dragón";
        if(type.equals("electric")) type = "Eléctrico";
        if(type.equals("fairy")) type = "Hada";
        if(type.equals("fighting")) type = "Lucha";
        if(type.equals("flying")) type = "Volador";
        if(type.equals("ghost")) type = "Fantasma";
        if(type.equals("ground")) type = "Tierra";
        if(type.equals("ice")) type = "Hielo";
        if(type.equals("rock")) type = "Roca";
        if(type.equals("normal")) type = "Normal";
        if(type.equals("poison")) type = "Veneno";
        if(type.equals("psychic")) type = "Psíquico";
        if(type.equals("steel")) type = "Acero";
        if(type.equals("water")) type = "Agua";
        if(type.equals("dark")) type = "Siniestro";
        return type;
    }

    private String translateAbility(String ability) {
        if(ability.equals("stench")) ability = "Hedor";
        if(ability.equals("drizzle")) ability = "Llovizna";
        if(ability.equals("speed-boost")) ability = "Impulso";
        if(ability.equals("battle-armor")) ability = "Armadura batalla";
        if(ability.equals("sturdy")) ability = "Robustez";
        if(ability.equals("damp")) ability = "Humedad";
        if(ability.equals("limber")) ability = "Flexibilidad";
        if(ability.equals("sand-veil")) ability = "Velo arena";
        if(ability.equals("static")) ability = "Electricidad estática";
        if(ability.equals("volt-absorb")) ability = "Absorb. electricidad";
        if(ability.equals("water-absorb")) ability = "Absorb. agua";
        if(ability.equals("oblivious")) ability = "Despiste";
        if(ability.equals("cloud-nine")) ability = "Aclimatación";
        if(ability.equals("compound-eyes")) ability = "Ojo compuesto";
        if(ability.equals("insomnia")) ability = "Imsomio";
        if(ability.equals("color-change")) ability = "Cambio color";
        if(ability.equals("immunity")) ability = "Inmunidad";
        if(ability.equals("flash-fire")) ability = "Absorb. fuego";
        if(ability.equals("shield-dust")) ability = "Polvo escudo";
        if(ability.equals("own-tempo")) ability = "Ritmo propio";
        if(ability.equals("suction-cups")) ability = "Ventosas";
        if(ability.equals("intimidate")) ability = "Intimidación";
        if(ability.equals("shadow-tag")) ability = "Sombra trampa";
        if(ability.equals("rough-skin")) ability = "Piel tosca";
        if(ability.equals("wonder-guard")) ability = "Superguarda";
        if(ability.equals("levitate")) ability = "Levitación";
        if(ability.equals("effect-spore")) ability = "Efecto espora";
        if(ability.equals("synchronize")) ability = "Sincronía";
        if(ability.equals("clear-body")) ability = "Cuerpo puro";
        if(ability.equals("natural-cure")) ability = "Cura natural";
        if(ability.equals("lightning-rod")) ability = "Pararrayos";
        if(ability.equals("serene-grace")) ability = "Dicha";
        if(ability.equals("swift-swim")) ability = "Nado rápido";
        if(ability.equals("chlorophyll")) ability = "Clorofila";
        if(ability.equals("illuminate")) ability = "Iluminación";
        if(ability.equals("trace")) ability = "Rastro";
        if(ability.equals("huge-power")) ability = "Potencia";
        if(ability.equals("poison-point")) ability = "Punto tóxico";
        if(ability.equals("inner-focus")) ability = "Foco interno";
        if(ability.equals("magma-armor")) ability = "Escudo magma";
        if(ability.equals("water-veil")) ability = "Velo agua";
        if(ability.equals("magnet-pull")) ability = "Imán";
        if(ability.equals("soundproof")) ability = "Insonorizar";
        if(ability.equals("rain-dish")) ability = "Cura lluvia";
        if(ability.equals("sand-stream")) ability = "Chorro arena";
        if(ability.equals("pressure")) ability = "Presión";
        if(ability.equals("thick-fat")) ability = "Sebo";
        if(ability.equals("early-bird")) ability = "Madrugar";
        if(ability.equals("flame-body")) ability = "Cuerpo llama";
        if(ability.equals("run-away")) ability = "Fuga";
        if(ability.equals("keen-eye")) ability = "Vista lince";
        if(ability.equals("hyper-cutter")) ability = "Corte fuerte";
        if(ability.equals("pickup")) ability = "Recogida";
        if(ability.equals("truant")) ability = "Ausente";
        if(ability.equals("hustle")) ability = "Entusiasmo";
        if(ability.equals("cute-charm")) ability = "Gran encanto";
        if(ability.equals("plus")) ability = "Más";
        if(ability.equals("minus")) ability = "Menos";
        if(ability.equals("forecast")) ability = "Predicción";
        if(ability.equals("sticky-hold")) ability = "Viscosidad";
        if(ability.equals("shed-skin")) ability = "Mudar";
        if(ability.equals("guts")) ability = "Agallas";
        if(ability.equals("marvel-scale")) ability = "Escama especial";
        if(ability.equals("liquid-ooze")) ability = "Lodo líquido";
        if(ability.equals("overgrow")) ability = "Espesura";
        if(ability.equals("blaze")) ability = "Mar llamas";
        if(ability.equals("torrent")) ability = "Torrente";
        if(ability.equals("swarm")) ability = "Enjambre";
        if(ability.equals("rock-head")) ability = "Cabeza roca";
        if(ability.equals("drought")) ability = "Sequía";
        if(ability.equals("arena-trap")) ability = "Trampa arena";
        if(ability.equals("vital-spirit")) ability = "Espirítu vital";
        if(ability.equals("white-smoke")) ability = "Humo blanco";
        if(ability.equals("pure-power")) ability = "Energía pura";
        if(ability.equals("shell-armor")) ability = "Caparazón";
        if(ability.equals("air-lock")) ability = "Bucle aire";
        if(ability.equals("tangled-feet")) ability = "Tumbos";
        if(ability.equals("motor-drive")) ability = "Electromotor";
        if(ability.equals("rivalry")) ability = "Rivalidad";
        if(ability.equals("steadfast")) ability = "Impasible";
        if(ability.equals("snow-cloak")) ability = "Manto níveo";
        if(ability.equals("gluttony")) ability = "Gula";
        if(ability.equals("anger-point")) ability = "Irascible";
        if(ability.equals("unburden")) ability = "Liviano";
        if(ability.equals("heatproof")) ability = "Ignífugo";
        if(ability.equals("simple")) ability = "Simple";
        if(ability.equals("dry-skin")) ability = "Piel seca";
        if(ability.equals("download")) ability = "Descarga";
        if(ability.equals("iron-fist")) ability = "Puño férreo";
        if(ability.equals("poison-heal")) ability = "Antídoto";
        if(ability.equals("adaptability")) ability = "Adaptable";
        if(ability.equals("skill-link")) ability = "Encadenado";
        if(ability.equals("hydration")) ability = "Hidratación";
        if(ability.equals("solar-power")) ability = "Poder solar";
        if(ability.equals("quick-feet")) ability = "Pies rápidos";
        if(ability.equals("normalize")) ability = "Normalidad";
        if(ability.equals("sniper")) ability = "Francotirador";
        if(ability.equals("magic-guard")) ability = "Muro mágico";
        if(ability.equals("no-guard")) ability = "Indefenso";
        if(ability.equals("stall")) ability = "Rezagado";
        if(ability.equals("technician")) ability = "Experto"; //100
        if(ability.equals("leaf-guard")) ability = "Defensa hoja";

        if(ability.equals("klutz")) ability = "Zoquete";
        if(ability.equals("mold-breaker")) ability = "Rompemoldes";
        if(ability.equals("super-luck")) ability = "Afortunado";
        if(ability.equals("aftermath")) ability = "Resquicio";
        if(ability.equals("anticipation")) ability = "Anticipación";
        if(ability.equals("forewarn")) ability = "Alerta";
        if(ability.equals("unaware")) ability = "Ignorante";
        if(ability.equals("tinted-lens")) ability = "Cromolente";
        if(ability.equals("filter")) ability = "Filtro";
        if(ability.equals("slow-start")) ability = "Inicio lento";
        if(ability.equals("scrappy")) ability = "Intrépido";
        if(ability.equals("storm-drain")) ability = "Colector";
        if(ability.equals("ice-body")) ability = "Gélido";
        if(ability.equals("solid-rock")) ability = "Roca solida";
        if(ability.equals("snow-warning")) ability = "Nevada";
        if(ability.equals("honey-gather")) ability = "Recogemiel";
        if(ability.equals("frisk")) ability = "Cacheo";
        if(ability.equals("reckless")) ability = "Audaz";
        if(ability.equals("multitype")) ability = "Multitipo";
        if(ability.equals("flower-gift")) ability = "Don floral";
        if(ability.equals("bad-dreams")) ability = "Mal sueño";
        if(ability.equals("pickpocket")) ability = "Hurto";
        if(ability.equals("sheer-force")) ability = "Potencia bruta";
        if(ability.equals("contrary")) ability = "Respondón";
        if(ability.equals("unnerve")) ability = "Nerviosismo";
        if(ability.equals("defiant")) ability = "Competitivo";
        if(ability.equals("defeatist")) ability = "Flaqueza";
        if(ability.equals("cursed-body")) ability = "Cuerpo maldito";
        if(ability.equals("healer")) ability = "Alma cura";
        if(ability.equals("friend-guard")) ability = "Compiescolta";
        if(ability.equals("weak-armor")) ability = "Armadura frágil";
        if(ability.equals("heavy-metal")) ability = "Metal pesado";
        if(ability.equals("light-metal")) ability = "Metal liviano";
        if(ability.equals("multiscale")) ability = "Compensación";
        if(ability.equals("toxic-boost")) ability = "Impetú tóxico";
        if(ability.equals("flare-boost")) ability = "Impetú ardiente";
        if(ability.equals("harvest")) ability = "Cosecha";
        if(ability.equals("telepathy")) ability = "Telepatía";
        if(ability.equals("moody")) ability = "Veleta";
        if(ability.equals("overcoat")) ability = "Funda";
        if(ability.equals("poison-touch")) ability = "Toque tóxico";
        if(ability.equals("regenerator")) ability = "Regeneración";
        if(ability.equals("big-pecks")) ability = "Sacapecho";
        if(ability.equals("sand-rush")) ability = "Impetú arena";
        if(ability.equals("wonder-skin")) ability = "Piel milagro";
        if(ability.equals("analytic")) ability = "Cálculo final";
        if(ability.equals("illusion")) ability = "Ilusión";
        if(ability.equals("imposter")) ability = "Impostor";
        if(ability.equals("infiltrator")) ability = "Allanamiento";
        if(ability.equals("mummy")) ability = "Momia";
        if(ability.equals("moxie")) ability = "Autoestima";
        if(ability.equals("justified")) ability = "Justiciero";
        if(ability.equals("rattled")) ability = "Cobardía";
        if(ability.equals("magic-bounce")) ability = "Espejo mágico";
        if(ability.equals("sap-sipper")) ability = "Hervíboro";
        if(ability.equals("prankster")) ability = "Bromista";
        if(ability.equals("sand-force")) ability = "Poder arena";
        if(ability.equals("iron-barbs")) ability = "Punta acero";
        if(ability.equals("zen-mode")) ability = "Modo daruma";
        if(ability.equals("victory-star")) ability = "Tinovictoria";
        if(ability.equals("turboblaze")) ability = "Turbollama";
        if(ability.equals("teravolt")) ability = "Terravoltaje";
        if(ability.equals("aroma-veil")) ability = "Velo aroma";
        if(ability.equals("flower-veil")) ability = "Velo flor";
        if(ability.equals("cheek-pouch")) ability = "Carrillo";
        if(ability.equals("protean")) ability = "Mutatipo";
        if(ability.equals("fur-coat")) ability = "Pelaje recio";
        if(ability.equals("magician")) ability = "Prestidigitador";
        if(ability.equals("bulletproof")) ability = "Antibalas";
        if(ability.equals("competitive")) ability = "Tenacidad";
        if(ability.equals("strong-jaw")) ability = "Mandíbula fuerte";
        if(ability.equals("refrigerate")) ability = "Piel helada";
        if(ability.equals("sweet-veil")) ability = "Velo dulce";
        if(ability.equals("stance-change")) ability = "Cambio táctico";
        if(ability.equals("gale-wings")) ability = "Alas vendaval";
        if(ability.equals("mega-launcher")) ability = "Megadisparador";
        if(ability.equals("grass-pelt")) ability = "Manto frondoso";
        if(ability.equals("symbiosis")) ability = "Simbiosis";
        if(ability.equals("tough-claws")) ability = "Garra dura";
        if(ability.equals("pixilate")) ability = "Piel feérica";
        if(ability.equals("gooey")) ability = "Baba";
        if(ability.equals("aerilate")) ability = "Piel celeste";
        if(ability.equals("parental-bond")) ability = "Amor filial";
        if(ability.equals("dark-aura")) ability = "Aura oscura";
        if(ability.equals("fairy-aura")) ability = "Aura feérica";
        if(ability.equals("aura-break")) ability = "Rompeaura";
        if(ability.equals("primordial-sea")) ability = "Mar del albor";
        if(ability.equals("desolate-land")) ability = "Tierra del ocaso";
        if(ability.equals("delta-stream")) ability = "Ráfaga delta";
        if(ability.equals("stamina")) ability = "Firmeza";
        if(ability.equals("wimp-out")) ability = "Huida";
        if(ability.equals("emergency-exit")) ability = "Retirada";
        if(ability.equals("water-compaction")) ability = "Hidrorefuerzo";
        if(ability.equals("merciless")) ability = "Ensañamiento";
        if(ability.equals("shields-down")) ability = "Escudo limitado";
        if(ability.equals("stakeout")) ability = "Vigilante";
        if(ability.equals("water-bubble")) ability = "Pompa";
        if(ability.equals("steelworker")) ability = "Acero templado"; //200
        if(ability.equals("berserk")) ability = "Cólera";
        if(ability.equals("slush-rush")) ability = "Quitanieves";
        if(ability.equals("long-reach")) ability = "Remoto";
        if(ability.equals("liquid-voice")) ability = "Voz fluida";
        if(ability.equals("triage")) ability = "Primer auxilio";
        if(ability.equals("galvanize")) ability = "Piel eléctrica";
        if(ability.equals("surge-surfer")) ability = "Cola surf";
        if(ability.equals("schooling")) ability = "Banco";
        if(ability.equals("disguise")) ability = "Disfraz";
        if(ability.equals("battle-bond")) ability = "Fuerte afecto";
        if(ability.equals("power-construct")) ability = "Agrupamiento";
        if(ability.equals("corrosion")) ability = "Corrosión";
        if(ability.equals("comatose")) ability = "Letargo perenne";
        if(ability.equals("queenly-majesty")) ability = "Regia presencia";
        if(ability.equals("innards-out")) ability = "Réves";
        if(ability.equals("dancer")) ability = "Pareja de baile";
        if(ability.equals("battery")) ability = "batería";
        if(ability.equals("fluffy")) ability = "Peluche";
        if(ability.equals("dazzling")) ability = "Cuerpo vivido";
        if(ability.equals("soul-heart")) ability = "Coránima";
        if(ability.equals("tangling-hair")) ability = "Rizos rebeldes";
        if(ability.equals("receiver")) ability = "Receptor";
        if(ability.equals("power-of-alchemy")) ability = "Reacción química";
        if(ability.equals("beast-boost")) ability = "Ultraimpulso";
        if(ability.equals("rks-system")) ability = "Sistema alpha";
        if(ability.equals("electric-surge")) ability = "Electrogénesis";
        if(ability.equals("psychic-surge")) ability = "Psicogénesis";
        if(ability.equals("misty-surge")) ability = "Nebulogénesis";
        if(ability.equals("grassy-surge")) ability = "Hervogénesis";
        if(ability.equals("full-metal-body")) ability = "Guardia metálica";
        if(ability.equals("shadow-shield")) ability = "Guardia espectro";
        if(ability.equals("prism-armor")) ability = "Armadura prisma";
        if(ability.equals("neuroforce")) ability = "Fuerza cerebral";
        if(ability.equals("intrepid-sword")) ability = "Espada indómita";
        if(ability.equals("dauntless-shield")) ability = "Escudo recio";
        if(ability.equals("libero")) ability = "Líbero";
        if(ability.equals("ball-fetch")) ability = "Recogebolas";
        if(ability.equals("cotton-down")) ability = "Pelusa";
        if(ability.equals("propeller-tail")) ability = "Hélice caudal";
        if(ability.equals("mirror-armor")) ability = "Coraza reflejo";
        if(ability.equals("gulp-missile")) ability = "Tragamisil";
        if(ability.equals("stalwart")) ability = "Acérrimo";
        if(ability.equals("steam-engine")) ability = "Combustible";
        if(ability.equals("punk-rock")) ability = "Punk rock";
        if(ability.equals("sand-spit")) ability = "Espulsaarena";
        if(ability.equals("ice-scales")) ability = "Escama de hielo";
        if(ability.equals("ripen")) ability = "Maduración";
        if(ability.equals("ice-face")) ability = "Cara de hielo";
        if(ability.equals("power-spot")) ability = "Fuente energía";
        if(ability.equals("mimicry")) ability = "Mimetismo";
        if(ability.equals("screen-cleaner")) ability = "Antibarrera";
        if(ability.equals("steely-spirit")) ability = "Alma acerada";
        if(ability.equals("perish-body")) ability = "Cuerpo mortal";
        if(ability.equals("wandering-spirit")) ability = "Alma errante";
        if(ability.equals("gorilla-tactics")) ability = "Monotema";
        if(ability.equals("neutralizing-gas")) ability = "Gas reactivo";
        if(ability.equals("pastel-veil")) ability = "Velo pastel";
        if(ability.equals("hunger-switch")) ability = "Mutaapetito";
        if(ability.equals("quick-draw")) ability = "Mano rápida";
        if(ability.equals("unseen-fist")) ability = "Puño invisible";
        if(ability.equals("curious-medicine")) ability = "Medicina extraña";
        if(ability.equals("transistor")) ability = "Transistor";
        if(ability.equals("dragons-maw")) ability = "Mandíbula dragon";
        if(ability.equals("chilling-neigh")) ability = "Relincho blanco";
        if(ability.equals("grim-neigh")) ability = "Relincho negro";
        if(ability.equals("wave-rider")) ability = "Surcavientos";
        if(ability.equals("thrust")) ability = "Empujón";
        if(ability.equals("Parry")) ability = "Parar";

        return ability;
    }


}