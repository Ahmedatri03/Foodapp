package app.foodapp;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static app.foodapp.model.ReadJson.URLReader;

public class FoodAppCLI {




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

    public static void main(String[] args) throws IOException {

        System.out.println("Bienvenue dans Food App");
        Scanner scanner = new Scanner(System.in);
        System.out.println("saisir votre choix ");
        System.out.println("1.recherche par ingrédient");
        System.out.println("2.liste favoris");
        System.out.println("3.ajouter à la lite des favoris");
        System.out.println("4.retirer de la liste des favoris");
        System.out.println("5.quitter");


        int choix = scanner.nextInt();
        switch (choix) {
            case 1:

                System.out.println("1.recherche par ingrédient");
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("saisissez vos ingredients");
                String input = scanner1.nextLine();
                String[] strings = input.split(" ");
                ArrayList<String> liste = new ArrayList<String>();
                for (String s : strings) {
                    liste.add(s);
                }
                searchByIngredient(liste);

                break;
            case 2:
                System.out.println("2.Ma liste favoris");

                break;
            case 3:
                System.out.println("3.ajouter à la lite des favoris");

                break;
            case 4:
                System.out.println("4.retirer de la liste des favoris");

                break;
            case 5:
                System.out.println("5.quitter");

                break;
            default:
                System.out.println("votre choix est invalide");

        }
        scanner.close();
    }
}