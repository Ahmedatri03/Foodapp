package app.foodapp;

import app.foodapp.model.Ingredient;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.*;

import static app.foodapp.model.ReadJson.URLReader;


public class SearchRecipe {
    public static URL RecipeByIngredient;
    public static URL AllRecipie;
    private int numberOfResearches;

    private List<Ingredient> ownedIngredients;

    public SearchRecipe(int numberOfResearches, List<Ingredient> ingredients) {

        this.numberOfResearches = numberOfResearches;
        this.ownedIngredients = ingredients;
    }

    public static List<String> searchByIngredient(List<String> ingredients) throws IOException {
        String RecipeIngredients;
        RecipeIngredients = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=";
        for (int i = 0; i < ingredients.size() - 1; i++) {
            RecipeIngredients = RecipeIngredients + ingredients.get(i) + ",+";
        }
        RecipeIngredients = RecipeIngredients + "&number=2&apiKey=32092cfcdeba4c4096c74042a16361d0";
        try {
            String foundRecipies = URLReader(RecipeIngredients);
            Gson gson = new Gson();


            String[] foundRecipes = gson.fromJson(foundRecipies, String[].class);
            List<String> list = new ArrayList<>();
            list = Arrays.asList(foundRecipes);
            return list;


        } catch (MalformedParametersException e) {
            throw new RuntimeException(e);
        }
    }
}








