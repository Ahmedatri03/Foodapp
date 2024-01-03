package app.foodapp.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Favorite {

    private static final Set<Recipe> favorites = new HashSet<>();

    public static Set<Recipe> getFavorites() {
        return favorites;
    }

    public static void addToFavorites(Recipe recipe) {
        if (!isFavorite(recipe))
            favorites.add(recipe);
        refresh();
    }

    public static boolean isFavorite(Recipe Recipe) {
        return favorites.contains(Recipe);
    }

    public static void removeFromFavorite(Recipe recipe) {
        if (isFavorite(recipe))
            favorites.remove(recipe);
        refresh();
    }

    public static void refresh() {
        JSONObject favorisJson = new JSONObject();
        for (Recipe recipe : favorites) {
            recipe.toString();
            favorisJson.put("id", recipe.getId());
            favorisJson.put("title", recipe.getTitle());
        }
    }
}
