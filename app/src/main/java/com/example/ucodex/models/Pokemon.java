package com.example.ucodex.models;

import java.util.ArrayList;
import com.example.ucodex.models.types;

public class Pokemon {

    private int number;
    private String name;
    private String url;

    private ArrayList<types> types;
    private int weight;
    private int height;
    private int base_experience;
    private int id;
    private ArrayList<baseStat> stats;
    private ArrayList<ability> abilities;

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

    public ArrayList<types> getTypes() {return types;}
    public void setTypes(ArrayList<types> types) {this.types = types;}

    public int getWeight() {return weight;}
    public void setWeight(int weight) {this.weight = weight;}

    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    public int getBase_experience() {return base_experience;}
    public void setBase_experience(int base_experience) {this.base_experience = base_experience;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public ArrayList<baseStat> getStats() {return stats;}
    public void setStats(ArrayList<baseStat> stats) {this.stats = stats;}

    public ArrayList<ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<ability> abilities) {
        this.abilities = abilities;
    }
}
