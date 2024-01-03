package app.foodapp.controller;

import app.foodapp.FoodAppGUI;
import app.foodapp.model.Favorite;
import app.foodapp.model.Ingredient;
import app.foodapp.model.ReadJson;
import app.foodapp.model.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodAppController implements Initializable {

    @FXML
    public TextField ingredientsTxtFld;
    @FXML
    public TextArea showIngredientsTxtArea;
    @FXML
    public TextField numberOfInTxtFld;

    @FXML
    public ListView<Recipe> ingredientListViewId;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        numberOfInTxtFld.setText("3");
        numberOfInTxtFld.setEditable(false);

    }

    public void plusButtonAction(ActionEvent actionEvent) {
        int num = Integer.parseInt(numberOfInTxtFld.getText()) + 1;
        numberOfInTxtFld.setText(num + "");

        loadRecipesInListView();
    }

    public void minusBtnAction(ActionEvent actionEvent) {
        int num = Integer.parseInt(numberOfInTxtFld.getText()) - 1;
        numberOfInTxtFld.setText(num + "");

        loadRecipesInListView();
    }

    public void addIngredients(ActionEvent actionEvent) {
        String name = ingredientsTxtFld.getText();
        ingredients.add(new Ingredient(0, "", name, ""));

        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.getName()).append("\n");
        }
        showIngredientsTxtArea.setText(sb.toString());

        ingredientsTxtFld.clear();

        loadRecipesInListView();
    }

    private void loadRecipesInListView() {
        try {
            ReadJson readJson = new ReadJson();
            Recipe[] recipes = readJson.getRecipes(ingredients, Integer.parseInt(numberOfInTxtFld.getText()));

            ingredientListViewId.setCellFactory(param -> new RecipeListCell());

            ObservableList<Recipe> items = FXCollections.observableArrayList(recipes);
            ingredientListViewId.setItems(items);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLovedItemsButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(FoodAppGUI.class.getResource("foodappfev.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Fev Recipes");
        stage.show();
    }

    /**
     * Custom designed cell for recipe list
     */
    public static class RecipeListCell extends ListCell<Recipe> {
        private final Label title = new Label();
        private final Label ingredients = new Label();
        private final Button loveButton = new Button();
        private final Button viewDetails = new Button();

        public RecipeListCell() {
            HBox hbox = new HBox(title, ingredients, loveButton, viewDetails);
            hbox.setSpacing(10);
            setGraphic(hbox);
        }

        @Override
        protected void updateItem(Recipe item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
                return;
            }

            title.setText("Title: \n" + item.getTitle());

            StringBuilder sb = new StringBuilder();
            for (Ingredient ingredient : item.getUsedIngredients()) {
                sb.append(ingredient.getName()).append("\n");
            }
            ingredients.setText("Ingredients: \n" + sb.toString());

            Image image = new Image("image/heart_out.png");
            ImageView loveIcon = new ImageView(image);
            loveIcon.setFitWidth(16);
            loveIcon.setFitHeight(16);
            loveButton.setGraphic(loveIcon);
            viewDetails.setText("View Details");

            setGraphic(getHBox(item));
        }

        private HBox getHBox(Recipe recipe) {
            HBox hbox = new HBox(title, ingredients, loveButton, viewDetails);
            hbox.setSpacing(10);
            loveButton.setOnAction(event -> {
                System.out.println("Love button clicked for recipe: " + recipe.getTitle());
                Favorite.addToFavorites(recipe);
            });

            viewDetails.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(FoodAppGUI.class.getResource("details.fxml"));
                    Parent parent = loader.load();

                    DetailsPageController dto = loader.getController();
                    dto.setRecipe(recipe);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.setTitle(recipe.getTitle());
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return hbox;
        }
    }

}
