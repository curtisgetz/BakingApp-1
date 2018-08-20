package com.example.android.bakingapp.utils;

import com.example.android.bakingapp.model.Cake;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Step;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import timber.log.Timber;

public class ExecuteJSONUtils {
    //the fetch do all the work and return ArrayList of Ingredients
    public static ArrayList<Ingredient> fetchTheIngredients(String requestURL) {
        //Create url object from the string
        URL url = Utility.createURL(requestURL);

        //jsonResponse is the Json response from the string
        //the long json string is the jsonString
        String jsonResponse = null;
        try {
            jsonResponse = Utility.makeHttpRequest(url);
        } catch (IOException e) {
            Timber.i("Error closing input stream");
        }

        // we pass the long String and then will get the extracted JSONArray list.
        return Utility.extractFeatureFromJson(jsonResponse);
    }

    //the fetch do all the work and return ArrayList of Steps
    public static ArrayList<Step> fetchTheSteps(String requestURL) {
        //Create url object from the string
        URL url = Utility.createURL(requestURL);

        //jsonResponse is the Json response from the string
        //the long json string is the jsonString
        String jsonResponse = null;
        try {
            jsonResponse = Utility.makeHttpRequest(url);
        } catch (IOException e) {
            Timber.i("Error closing input stream");
        }

        // we pass the long String and then will get the extracted JSONArray list.
        return Utility.extractStepsFromJson(jsonResponse);
    }

    //the fetch do all the work and return ArrayList of Steps
    public static ArrayList<Cake> fetchTheCakes(String requestURL) {
        //Create url object from the string
        URL url = Utility.createURL(requestURL);

        //jsonResponse is the Json response from the string
        //the long json string is the jsonString
        String jsonResponse = null;
        try {
            jsonResponse = Utility.makeHttpRequest(url);
        } catch (IOException e) {
            Timber.i("Error closing input stream");
        }

        // we pass the long String and then will get the extracted JSONArray list.
        return Utility.extractCakesFromJson(jsonResponse);
    }

}
