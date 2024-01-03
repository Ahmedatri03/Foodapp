package app.foodapp.model;

import app.foodapp.controller.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private List<Ingredient> usedIngredients;
    private List<Ingredient> missedIngredients;
    private long id;
    private String title;
    private String image;

    public Recipe(long id, String title, List<Ingredient> usedIngredients
            , List<Ingredient> missedIngredients, String image) {
        this.title = title;
        this.usedIngredients = usedIngredients;
        this.missedIngredients = missedIngredients;
        this.image = image;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public long getId() {
        return id;
    }

    public List<Ingredient> getUsedIngredients() {
        return usedIngredients;
    }

    public List<Ingredient> getMissedIngredients() {
        return missedIngredients;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "usedIngredients=" + usedIngredients +
                ", missedIngredients=" + missedIngredients +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
