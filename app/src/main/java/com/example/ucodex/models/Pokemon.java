package com.example.ucodex.models;

import java.util.ArrayList;

public class Pokemon {

    private int number;
    private String name;
    private String url;

    //private ArrayList<String> types;
    private int weight;
    private int height;
    private int baseExperience;
    private int id;
 //   private ArrayList<String> abilities;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlPartes= url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length -1]);
    }
    public void setNumber(int number) {
        this.number = number;
    }
/*
    public ArrayList<String> getTypes() {return types;}
    public void setTypes(ArrayList<String> types) {this.types = types;}
*/
    public int getWeight() {return weight;}
    public void setWeight(int weight) {this.weight = weight;}

    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    public int getBaseExperience() {return baseExperience;}
    public void setBaseExperience(int baseExperience) {this.baseExperience = baseExperience;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    /*
    public ArrayList<String> getAbilities() {return abilities;}
    public void setAbilities(ArrayList<String> abilities) {this.abilities = abilities;}
    */
}
