package recipesearch;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import se.chalmers.ait.dat215.lab2.*;

import java.util.List;

public class RecipeBackendController {

    @FXML
    private ComboBox comboBoxLand;

    String cuisine = null;
    String mainIngredient = null;
    String difficulty = null;
    int maxPrice = 0;
    int maxTime = 0;
    RecipeDatabase db = RecipeDatabase.getSharedInstance();

    public List<Recipe>getRecipes(){
        return db.search(new SearchFilter(difficulty,maxTime,cuisine,maxPrice,mainIngredient));
    }
    public void setCuisine(String cuisine){
        if (cuisine.equals("Land")){
            this.cuisine = null;
        } else {
            this.cuisine = cuisine;
        }
    }
    public void setMainIngredient(String mainIngredient){
        if (mainIngredient.equals("Visa alla")){
            this.mainIngredient = null;
        } else {
            this.mainIngredient = mainIngredient;
        }
    }
    public void setDifficulty(String difficulty){
        if (difficulty.equals("Alla")){
            this.difficulty = null;
        } else {
            this.difficulty = difficulty;
        }
    }
    public void setMaxPrice(int maxPrice){
        this.maxPrice = maxPrice;
    }
    public void setMaxTime(int maxTime){
        this.maxTime = maxTime;
        System.out.println(maxTime);
        System.out.println(this.maxTime);
    }


}
