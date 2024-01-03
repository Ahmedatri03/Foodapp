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

public class FoodAppFevController implements Initializable {


    @FXML
    public ListView<Recipe> ingredientListViewId;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        // init set number of items
        loadRecipesInListView();
    }

    private void loadRecipesInListView() {
        ingredientListViewId.setCellFactory(param -> new RecipeListCell());

        ObservableList<Recipe> items = FXCollections.observableArrayList(Favorite.getFavorites());
        ingredientListViewId.setItems(items);
    }

    public void backButtonAction(ActionEvent event) {
        Stage stage = (Stage) ingredientListViewId.getScene().getWindow();
        stage.close();
    }


    public static class RecipeListCell extends ListCell<Recipe> {
        private final Label title = new Label();
        private final Button viewDetails = new Button();

        public RecipeListCell() {
            HBox hbox = new HBox(title, viewDetails);
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

            Image image = new Image("image/heart_out.png");
            viewDetails.setText("View Details");

            setGraphic(getHBox(item));
        }

        private HBox getHBox(Recipe recipe) {
            HBox hbox = new HBox(title, viewDetails);
            hbox.setSpacing(10);

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
