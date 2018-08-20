package com.example.android.bakingapp.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.bakingapp.model.Cake;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import timber.log.Timber;

public class Utility {
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_MEASURE = "measure";
    private static final String KEY_INGREDIENT = "ingredients";
    private static final String KEY_SINGLE_INGREDIENT = "ingredient";

    private static final String KEY_STEPS = "steps";
    private static final String KEY_ID = "id";
    private static final String KEY_SHORT_DESCRIPTION = "shortDescription";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_VIDEO_URL = "videoURL";
    private static final String KEY_THUMB = "thumbnailURL";

    private static final String KEY_NAME = "name";
    private static final String KEY_SERVINGS = "servings";

    public static int ing_size_id_one;
    public static int ing_size_id_two;
    public static int ing_size_id_three;
    public static int ing_size_id_four;

    public static int step_size_id_one;
    public static int step_size_id_two;
    public static int step_size_id_three;
    public static int step_size_id_four;


    public static String prepareString() {
        return "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    }

    public static ArrayList<Ingredient> extractFeatureFromJson(String ingredientsJSON) {
        Timber.i("extractFeatureFromJson: extract has begun");
        if ( TextUtils.isEmpty(ingredientsJSON) ) {
            Timber.i("is Empty");
            return null;
        }

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        try {
            JSONArray reader = new JSONArray(ingredientsJSON);//getting a JSON reader object
            for (int y = 0; y < reader.length(); y++) {
                JSONObject reader_object = reader.getJSONObject(0);//getting a JSON reader object
                JSONArray json_ingredients = reader_object.getJSONArray(KEY_INGREDIENT);

                for (int i = 0; i < json_ingredients.length(); i++) {
                    JSONObject jsonObject = json_ingredients.getJSONObject(i); //This cannot be 0, this will only check the first element of the array
                    String ingredient = jsonObject.getString(KEY_SINGLE_INGREDIENT);
                    String measure = jsonObject.getString(KEY_MEASURE);
                    int quantity = jsonObject.getInt(KEY_QUANTITY);

                    // Create a new {@link Ingredient} object with the ingredient, measure and quantity
                    // and url from the JSON response.
                    Ingredient ingredient_parsed = new Ingredient(quantity, measure, ingredient);

                    // Add the new {@link Earthquake} to the list of earthquakes.
                    ingredients.add(ingredient_parsed);

                }

            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return ingredients;
    }

    public static ArrayList<Step> extractStepsFromJson(String stespJSON) {
        Timber.i("extractFeatureFromJson: extract has begun");
        if ( TextUtils.isEmpty(stespJSON) ) {
            Timber.i("is Empty");
            return null;
        }
        ArrayList<Cake> cakes = new ArrayList<>();
        ArrayList<Step> steps = new ArrayList<>();
        try {
            JSONArray reader = new JSONArray(stespJSON);//getting a JSON reader object
            for (int y = 0; y < reader.length(); y++) {
                JSONObject reader_object = reader.getJSONObject(y);//getting a JSON reader object
                JSONArray json_steps = reader_object.getJSONArray(KEY_STEPS);

                for (int i = 0; i < json_steps.length(); i++) {
                    JSONObject jsonObject = json_steps.getJSONObject(i); //This cannot be 0, this will only check the first element of the array
                    int id = jsonObject.getInt(KEY_ID);
                    String short_description = jsonObject.getString(KEY_SHORT_DESCRIPTION);
                    String description = jsonObject.getString(KEY_DESCRIPTION);
                    String video_URL = jsonObject.getString(KEY_VIDEO_URL);
                    String thumb_URL = jsonObject.getString(KEY_THUMB);

                    // Create a new {@link Ingredient} object with the ingredient, measure and quantity
                    // and url from the JSON response.
                    Step step_parsed = new Step(id, short_description, description, video_URL, thumb_URL);

                    // Add the new {@link Earthquake} to the list of earthquakes.
                    steps.add(step_parsed);
                    Log.i("TAG", cakes.toString());
                }
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return steps;
    }

    public static ArrayList<Cake> extractCakesFromJson(String cakeJSON) {
        Timber.i("extractFeatureFromJson: extract has begun");
        if ( TextUtils.isEmpty(cakeJSON) ) {
            Timber.i("is Empty");
            return null;
        }
        ArrayList<Cake> cakes = new ArrayList<>();
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Ingredient> ingredients_two = new ArrayList<>();
        Ingredient ingredient_parsed = null;
        ingredients_two = null;

        try {
            JSONArray reader = new JSONArray(cakeJSON);//getting a JSON reader object
            int len =  reader.length();
            for (int y = 0; y < reader.length(); y++) {

                JSONObject cake_object = reader.getJSONObject(y);//getting a JSON reader object
                String name = cake_object.getString(KEY_NAME);
                int id = cake_object.getInt(KEY_ID);
                JSONArray json_ingredients = cake_object.getJSONArray(KEY_INGREDIENT);

                for ( int i = 0; i < json_ingredients.length(); i++) {


                    JSONObject jsonObject = json_ingredients.getJSONObject(i); //This cannot be 0, this will only check the first element of the array
                    String ingredient = jsonObject.getString(KEY_SINGLE_INGREDIENT);
                    String measure = jsonObject.getString(KEY_MEASURE);
                    int quantity = jsonObject.getInt(KEY_QUANTITY);

                    // Create a new {@link Ingredient} object with the ingredient, measure and quantity
                    // and url from the JSON response.
                    ingredient_parsed = new Ingredient(quantity, measure, ingredient);
                    ingredients.add(ingredient_parsed);
                }


                JSONArray json_steps = cake_object.getJSONArray(KEY_STEPS);
                for (int j = 0; j < json_steps.length(); j++) {
                    JSONObject jsonObject = json_steps.getJSONObject(j); //This cannot be 0, this will only check the first element of the array
                    int id_steps = jsonObject.getInt(KEY_ID);
                    String short_description = jsonObject.getString(KEY_SHORT_DESCRIPTION);
                    String description = jsonObject.getString(KEY_DESCRIPTION);
                    String video_URL = jsonObject.getString(KEY_VIDEO_URL);
                    String thumb_URL = jsonObject.getString(KEY_THUMB);

                    // Create a new {@link Ingredient} object with the ingredient, measure and quantity
                    // and url from the JSON response.
                    Step step_parsed = new Step(id_steps, short_description, description, video_URL, thumb_URL);

                    // Add the new {@link Earthquake} to the list of earthquakes.
                    steps.add(step_parsed);
                }


                switch (id){
                    case 1:
                        ing_size_id_one = json_ingredients.length();
                        step_size_id_one = json_steps.length();
                        break;
                    case 2:
                        ing_size_id_two = json_ingredients.length();
                        step_size_id_two = json_steps.length();
                        break;
                    case 3:
                        ing_size_id_three = json_ingredients.length();
                        step_size_id_three = json_steps.length();
                        break;
                    case 4:
                        ing_size_id_four = json_ingredients.length();
                        step_size_id_four = json_steps.length();
                        break;
                }
                int serving = cake_object.getInt(KEY_SERVINGS);
                Cake cake = new Cake(id, name, ingredients, steps, serving);
                cakes.add(cake);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cakes;

    }


    /* This method will make the actual HttpRequest.
     * Make an HTTP request from the given URL and returns the string as the response
     * the json response will be returned, a long text
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //If the URL is null
        if ( url == null ) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            final int READ_TIMEOUT = 10000;
            final int CONNECT_TIMEOUT = 15000;
            final int SUCCESS = 200;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT /* millisecond*/);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT /* millisecond*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int code = urlConnection.getResponseCode();
            Timber.i("makeHttpRequest: " + code);

            /**
             * If the request was successful (response code 200)
             * then read the input stream and parse the response.
             */
            if ( urlConnection.getResponseCode() == 200 ) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Timber.e("Error in response code");

            }
        } catch (IOException e) {
            Timber.e(e, "Problem downloading the movies");
        } finally {
            if ( urlConnection != null ) {
                urlConnection.disconnect(); //disconnect when done
            }
            if ( inputStream != null ) {
                inputStream.close();   //close
            }
            Timber.i("makeHttpRequest: jsonResponse has been made " + urlConnection.getResponseCode());
            return jsonResponse;
        }
    }

    /**
     * the Stream will just be zeros and ones as it downloaded directly from the server
     * We need to encode that to something we and understand, this will do that for us
     * This will read the given input stream and make it a sensible string
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output; /* String builder are like string but they can be modified internally
         we need this since we dont know how long the string that  is going to be*/
        output = new StringBuilder();
        if ( inputStream != null ) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        Timber.i("readFromStream: the stream has been converted");
        return output.toString();
    }

    /*
  This method will create a url from the string we pass to it.
   */
    public static URL createURL(String requestURL) {
        Timber.i("URL: url has been created ");
        URL url = null;
        try {
            url = new URL(requestURL);
        } catch (MalformedURLException e) {
            Timber.e(e, "Error with creating URL ");
        }
        return url;
    }

}
