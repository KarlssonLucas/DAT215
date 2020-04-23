package recipesearch;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import se.chalmers.ait.dat215.lab2.Recipe;

import java.io.IOException;

public class RecipeListItem extends AnchorPane {
    private RecipeSearchController parentController;
    private Recipe recipe;

    @FXML
    private ImageView matBild;
    @FXML
    private Label matNamn;
    @FXML
    private ImageView landItem;
    @FXML
    private ImageView ingrediensItem;
    @FXML
    private ImageView difficultyItem;
    @FXML
    private Label maxPrisItem;
    @FXML
    private Label tidItem;
    @FXML
    private TextArea descItem;
    @FXML
    private AnchorPane paneItem;

    @FXML
    public void onClick(Event event) {
        parentController.openRecipeView(recipe);
    }

    public RecipeListItem(Recipe recipe, RecipeSearchController recipeSearchController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.recipe = recipe;
        this.parentController = recipeSearchController;

        matBild.setImage(this.recipe.getFXImage());
        matNamn.setText(this.recipe.getName());
        landItem.setImage(getCuisineImage(this.recipe.getCuisine()));
        ingrediensItem.setImage(getIngredience(this.recipe.getMainIngredient()));
        maxPrisItem.setText(String.valueOf(this.recipe.getPrice()) + " kr");
        tidItem.setText(String.valueOf(this.recipe.getTime()) + " minuter");
        difficultyItem.setImage(getDiff(this.recipe.getDifficulty()));

        this.recipe.setDescription(this.recipe.getDescription());
        descItem.setText(this.recipe.getDescription());
    }

    public Image getCuisineImage(String cuisine) {
        String iconPath;
        switch (cuisine) {
            case "Grekland":
                iconPath = "RecipeSearch/resources/icon_flag_greece.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Sverige":
                iconPath = "RecipeSearch/resources/icon_flag_sweden.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Indien":
                iconPath = "RecipeSearch/resources/icon_flag_india.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Asien":
                iconPath = "RecipeSearch/resources/icon_flag_asia.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Afrika":
                iconPath = "RecipeSearch/resources/icon_flag_africa.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Frankrike":
                iconPath = "RecipeSearch/resources/icon_flag_france.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));


        }
        return null;
    }

    public Image getIngredience(String ingredient) {
        String iconPath;
            switch (ingredient) {
                case "Kött":
                    iconPath = "RecipeSearch/resources/icon_main_meat.png";
                    return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

                case "Fisk":
                    iconPath = "RecipeSearch/resources/icon_main_fish.png";
                    return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

                case "Kyckling":
                    iconPath = "RecipeSearch/resources/icon_main_chicken.png";
                    return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

                case "Vegetarisk":
                    iconPath = "RecipeSearch/resources/icon_main_veg.png";
                    return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            }
            return null;
    }

    public Image getDiff(String dif) {
        String iconPath;
        switch (dif) {
            case "Lätt":
                iconPath = "RecipeSearch/resources/icon_difficulty_easy.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Mellan":
                iconPath = "RecipeSearch/resources/icon_difficulty_medium.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));

            case "Svår":
                iconPath = "RecipeSearch/resources/icon_difficulty_hard.png";
                return new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
        }
        return null;
    }
}
