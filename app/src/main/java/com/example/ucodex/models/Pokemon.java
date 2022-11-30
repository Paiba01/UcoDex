package com.example.ucodex.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    private int number;
    private String name;
    private String url;

    private String type1;
    private String type2;
    private int weight;
    private int height;
    private int baseExperience;
    private int id;



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

    public String getType1() {return type1;}
    public void setType1(String type1) {this.type1 = type1;}

    public String getType2() {return type2;}
    public void setType2(String type2) {this.type2 = type2;}

    public int getWeight() {return weight;}
    public void setWeight(int weight) {this.weight = weight;}

    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    public int getBaseExperience() {return baseExperience;}
    public void setBaseExperience(int baseExperience) {this.baseExperience = baseExperience;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}
