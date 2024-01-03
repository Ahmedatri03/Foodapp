package app.foodapp.model;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadJson {

    public static void readUser() {
        String userJson = "[{'name': 'Alex','id': 1}, " + "{'name': 'Brian','id':2}, " + "{'name': 'Charles','id': 3}]";
        Gson gson = new Gson();
        Recipe[] userArray = gson.fromJson(userJson, Recipe[].class);
        for (Recipe user : userArray) {
            System.out.println(user);
        }
    }

    public static Recipe[] readJson(String jsonStr) {
        Gson gson = new Gson();
        Recipe[] recipes = gson.fromJson(jsonStr, Recipe[].class);

        return recipes;
    }

    public static String fileToString(File file) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = r.readLine()) != null) {
            builder.append(line + "\n");
        }
        r.close();
        return builder.toString();
    }

    public static String URLReader(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        URL url1 = new URL(url);
        InputStream in = url1.openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } finally {
            in.close();
        }

        return sb.toString();
    }



    public Recipe[] getRecipes(ArrayList<Ingredient> ingredients, int numberOfItems) throws IOException {

        StringBuilder ingredientsStr = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            ingredientsStr.append(ingredient.getName() + ",");
        }
        String url = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" +
                ingredientsStr.substring(0, ingredientsStr.length() - 1) + "&number=" + numberOfItems + "&apiKey=32092cfcdeba4c4096c74042a16361d0";
        String recipesSpoonacular = URLReader(url);
        Gson gson = new Gson();
        Recipe[] userArray = gson.fromJson(recipesSpoonacular, Recipe[].class);

        return userArray;
    }

    public static void main(String[] args) throws IOException {

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1L, "", "banana", ""));
        ingredients.add(new Ingredient(1L, "", "+flour", ""));
        ingredients.add(new Ingredient(1L, "", "+sugar", ""));

        for (Recipe recipe : new ReadJson().getRecipes(ingredients, 5)) {
            System.out.println(recipe.toString());
        }
    }

}

