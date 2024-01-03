package app.foodapp.controller;

import app.foodapp.FoodAppGUI;
import app.foodapp.model.Favorite;
import app.foodapp.model.Ingredient;
import app.foodapp.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailsPageController implements Initializable {

    @FXML
    public TextArea ingredientsTxtAreaId;
    @FXML
    public ImageView recipeImageViewId;
    @FXML
    public Label titleLabelId;
    private Recipe recipe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addInFevButtonAction(ActionEvent actionEvent) {
        Favorite.addToFavorites(recipe);
    }

    public void accessFevButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(FoodAppGUI.class.getResource("foodappfev.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Fev Recipes");
        stage.show();
    }

    public void backButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) recipeImageViewId.getScene().getWindow();
        stage.close();
    }

    public void setRecipe(Recipe recipe) throws IOException {
        System.out.println(recipe);
        this.recipe = recipe;

        titleLabelId.setText(recipe.getTitle());

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.addAll(recipe.getUsedIngredients());
        ingredients.addAll(recipe.getMissedIngredients());

        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.getName()).append("\n");
        }

        ingredientsTxtAreaId.setText(sb.toString());


        URL url = new URL(recipe.getImage());

        InputStream is = url.openStream();

        Image image = new Image(is);
        recipeImageViewId.setImage(image);
    }
}
