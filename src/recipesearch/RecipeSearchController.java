
package recipesearch;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import se.chalmers.ait.dat215.lab2.Ingredient;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;


public class RecipeSearchController implements Initializable {
    @FXML private ComboBox<String> comboBoxLand;
    @FXML private FlowPane recipeList;
    @FXML private ComboBox<String> comboBoxIngrediens;
    @FXML private RadioButton rButtonAlla;
    @FXML private RadioButton rbuttonLatt;
    @FXML private RadioButton rButtonMellan;
    @FXML private RadioButton rbuttonSvar;
    @FXML private Spinner spinnerPris;
    @FXML private Slider sliderTid;
    @FXML private Label labelTid;
    @FXML private Label titleDetail;
    @FXML private ImageView detailImage;
    @FXML private AnchorPane recipeDetailPane;
    @FXML private ImageView closeDetail;
    @FXML private ImageView detailIngredient;
    @FXML private ImageView detailDiff;
    @FXML private Label detailTime;
    @FXML private Label detailPrice;
    @FXML private TextArea detailDescription;
    @FXML private TextArea detailIngredientList ;
    @FXML private TextArea detailInstructions;
    @FXML private ImageView detailFlag;

    private RecipeBackendController recipeBackendController;
    private Map<String,RecipeListItem>recipeListItemMap = new HashMap<String, RecipeListItem>();
    private RecipeListItem recipeListItem;

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

    @FXML
    public void closeButtonMouseEntered(){
        closeDetail.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "RecipeSearch/resources/icon_close_hover.png")));
    }

    @FXML
    public void closeButtonMousePressed(){
        closeDetail.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "RecipeSearch/resources/icon_close_pressed.png")));

        recipeDetailPane.toBack();
    }

    @FXML
    public void closeButtonMouseExited(){
        closeDetail.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "RecipeSearch/resources/icon_close.png")));
    }

    private void populateMainIngredientComboBox() {
        Callback<ListView<String>, ListCell<String>> cellFactory = new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> p) {

                return new ListCell<String>() {

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        setText(item);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon = null;
                            String iconPath;
                            try {
                                switch (item) {

                                    case "Kött":
                                        iconPath = "RecipeSearch/resources/icon_main_meat.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Fisk":
                                        iconPath = "RecipeSearch/resources/icon_main_fish.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Kyckling":
                                        iconPath = "RecipeSearch/resources/icon_main_chicken.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Vegtarisk":
                                        iconPath = "RecipeSearch/resources/icon_main_veg.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                }
                            } catch (NullPointerException ex) {
                                //This should never happen in this lab but could load a default image in case of a NullPointer
                            }
                            ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(12);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        };
        comboBoxIngrediens.setButtonCell(cellFactory.call(null));
        comboBoxIngrediens.setCellFactory(cellFactory);

    }

    private void populateMainCountryComboBox() {
        Callback<ListView<String>, ListCell<String>> cellFact = new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> p) {

                return new ListCell<String>() {

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        setText(item);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon = null;
                            String iconPath;
                            try {
                                switch (item) {

                                    case "Grekland":
                                        iconPath = "RecipeSearch/resources/icon_flag_greece.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Sverige":
                                        iconPath = "RecipeSearch/resources/icon_flag_sweden.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Indien":
                                        iconPath = "RecipeSearch/resources/icon_flag_india.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Asien":
                                        iconPath = "RecipeSearch/resources/icon_flag_asia.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Afrika":
                                        iconPath = "RecipeSearch/resources/icon_flag_africa.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;
                                    case "Frankrike":
                                        iconPath = "RecipeSearch/resources/icon_flag_france.png";
                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                        break;

                                }
                            } catch (NullPointerException ex) {
                                //This should never happen in this lab but could load a default image in case of a NullPointer
                            }
                            ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(12);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        };
        comboBoxLand.setButtonCell(cellFact.call(null));
        comboBoxLand.setCellFactory(cellFact);

    }

    private void updateRecipeList(){
        recipeList.getChildren().clear();
        List<Recipe> recipes = recipeBackendController.getRecipes();
        for (Recipe r:recipes) {
            recipeList.getChildren().add(recipeListItemMap.get(r.getName()));
        }
    }

    private void populateRecipeDetailView(Recipe recipe){
        titleDetail.setText(recipe.getName());
        detailImage.setImage(recipe.getFXImage());
        detailDiff.setImage(recipeListItem.getDiff(recipe.getDifficulty()));
        detailIngredient.setImage(recipeListItem.getIngredience(recipe.getMainIngredient()));
        detailPrice.setText(recipe.getPrice() + " kr");
        detailTime.setText(recipe.getTime() + " minuter");

        detailDescription.setText(recipe.getDescription());
        detailInstructions.setText(recipe.getInstruction());
        detailFlag.setImage(recipeListItem.getCuisineImage(recipe.getCuisine()));

        List<Ingredient> ingredients = recipe.getIngredients();
        StringBuilder sb = new StringBuilder();

        for (Ingredient i : ingredients) {
            sb.append(i).append("\n");
        }

        detailIngredientList.setText(sb.toString().replaceAll("\\\\n", System.getProperty("line.seperator")));

    }

    void openRecipeView(Recipe recipe){
        populateRecipeDetailView(recipe);
        recipeDetailPane.toFront();
    }

    private void populateHashmMap(){
        for (Recipe recipe: recipeBackendController.getRecipes()){
            recipeListItem = new RecipeListItem(recipe,this);
            recipeListItemMap.put(recipe.getName(),recipeListItem);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipeBackendController = new RecipeBackendController();
        populateHashmMap();
        updateRecipeList();

        comboBoxIngrediens.getItems().addAll("Visa alla", "Kött","Fisk","Kyckling","Vegtarisk");
        comboBoxIngrediens.getSelectionModel().select("Visa alla");
        comboBoxIngrediens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            recipeBackendController.setMainIngredient(newValue);
            updateRecipeList();
        });
        comboBoxLand.getItems().addAll("Land","Sverige","Grekland","Indien","Asien","Afrika","Frankrike");
        comboBoxLand.getSelectionModel().select("Land");
        comboBoxLand.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            recipeBackendController.setCuisine(newValue);
            updateRecipeList();
        });

       ToggleGroup difficultyToggleGroup = new ToggleGroup();
       rButtonAlla.setToggleGroup(difficultyToggleGroup);
       rbuttonLatt.setToggleGroup(difficultyToggleGroup);
       rButtonMellan.setToggleGroup(difficultyToggleGroup);
       rbuttonSvar.setToggleGroup(difficultyToggleGroup);
       rButtonAlla.setSelected(true);
       difficultyToggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
           if (difficultyToggleGroup.getSelectedToggle() != null) {
               RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
               recipeBackendController.setDifficulty(selected.getText());
               updateRecipeList();
           }
       });

       SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000,0,10);
       spinnerPris.setValueFactory(valueFactory);
       spinnerPris.valueProperty().addListener((ChangeListener<Integer>) (observableValue, integer, t1) -> {
           recipeBackendController.setMaxPrice(t1);
           updateRecipeList();
       });

        spinnerPris.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                //focusgained - do nothing
            }
            else{
                Integer value = Integer.valueOf(spinnerPris.getEditor().getText());
                recipeBackendController.setMaxPrice(value);
                updateRecipeList();
            }
        });
        sliderTid.valueProperty().addListener((observableValue, number, t1) -> {
            System.out.println("111");
            System.out.println(t1 !=null);
            System.out.println(!t1.equals(number));
            System.out.println(!sliderTid.isValueChanging());
            if (t1 !=null && !t1.equals(number) && !sliderTid.isValueChanging() || t1.intValue() == 150 || t1.intValue() == 0){
                int temp = t1.intValue()/10;
                temp *=10;
                recipeBackendController.setMaxTime(temp);
                labelTid.setText(temp +" Minuter");
                updateRecipeList();
            }
        });

        populateMainIngredientComboBox();
        populateMainCountryComboBox();
    }

}