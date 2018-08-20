package com.example.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Cake implements Parcelable {
    private int id;
    private String name;
    private ArrayList <Ingredient> ingredients;
    private ArrayList <Step> steps;


    protected Cake(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();

        ingredients = new ArrayList<>();
        in.readList(ingredients, Ingredient.class.getClassLoader());

        steps = new ArrayList<>();
        in.readList(steps, Step.class.getClassLoader());
    }

    public static final Creator<Cake> CREATOR = new Creator<Cake>() {
        @Override
        public Cake createFromParcel(Parcel in) {
            return new Cake(in);
        }

        @Override
        public Cake[] newArray(int size) {
            return new Cake[size];
        }
    };

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }


    public int getServings() {
        return servings;
    }

    private int servings;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }


    public Cake(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeList(ingredients);
        parcel.writeList(steps);

    }
}
