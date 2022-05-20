package com.example.cs210project.View;

import com.example.cs210project.Contoller.Controller;
import com.example.cs210project.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class AddRecipeScene extends Scene {
    //properties
    private TextField recipeTitleTF = new TextField();
    private TextArea directionsTA = new TextArea();
    private TextField produceTF = new TextField();
    private TextField ingredientTF = new TextField();
    private TextField sideIngredientTF = new TextField();

    private Button addButton = new Button("Add Recipe");
    private Button removeButton = new Button("Remove Recipe");
    private Button returnButton = new Button("Return");
    private Label titleLabel = new Label("Recipe Title");
  //  private Label ingredientLabel = new Label("List Ingredients");
  //  private Label recipeLabel = new Label("Recipes");

    private Controller controller = Controller.getInstance();
    private ObservableList<Recipe> recipeList = controller.getAllRecipes();

  //  private ListView<Recipe> recipeLV = new ListView<>();

    private ComboBox<String> prepCB = new ComboBox<>();
    private ComboBox<String> dishCB = new ComboBox<>();
    private ComboBox<String> mainIngredCB = new ComboBox<>();
    private ComboBox<String> meatCB = new ComboBox<>();
    private ComboBox<String> produceCB = new ComboBox<>();
    private ComboBox<String> ingredientCB = new ComboBox<>();
    private Button addMeatButton = new Button("Add");
    private Button addProduceButton = new Button("Add");
    private Button addIngredientButton = new Button("Add");
    private ArrayList<Meat> mMeatArrayList = new ArrayList<>();
    private ArrayList<Produce> mProduceArrayList = new ArrayList<>();
    private ArrayList<Ingredient> mIngredientArrayList = new ArrayList<>();
    private boolean mainDish;
    //TODO, not sure if we'll need or want
    //private TextArea processTA = new TextArea();

    //mainMethod
    public AddRecipeScene(){
        super(new GridPane(), MainScene.WIDTH, MainScene.HEIGHT);
        GridPane pane = new GridPane();

        RowConstraints row1 = new RowConstraints();
        row1.setPrefHeight(25);
        pane.getRowConstraints().add(row1);
        ColumnConstraints colC = new ColumnConstraints();
        colC.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(colC);
        pane.setAlignment(Pos.CENTER);

       // recipeList = controller.getAllRecipes();
       // recipeLV.setItems(recipeList);

        VBox recipeVB = new VBox();
        recipeVB.setSpacing(15);

        //recipe title and text field
        HBox recipeTitleHB = new HBox();
        recipeTitleHB.setSpacing(15);
        recipeTitleHB.setPrefWidth(120);
        recipeTitleHB.getChildren().add(titleLabel);
        recipeTitleHB.getChildren().add(recipeTitleTF);
        recipeVB.getChildren().add(recipeTitleHB);

        //dish type Combobox and label
        HBox dishTypeHB = new HBox();
        dishTypeHB.setSpacing(25);
        dishTypeHB.getChildren().add(new Label("Dish Type"));
        dishTypeHB.getChildren().add(dishCB);
        dishCB.getItems().addAll("Select","Main Dish", "Side Dish");
        dishCB.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) -> dishDisplay(newValue)));
        recipeVB.getChildren().add(dishTypeHB);

        //Prep type label and combo box
        HBox prepHB = new HBox();
        prepHB.setSpacing(15);
        prepHB.getChildren().add(new Label("Preparation"));
        prepHB.getChildren().add(prepCB);
        prepCB.getItems().addAll("Select","Baked", "Grilled", "Fried", "Boiled");
       // prepCB.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) -> selectedPrepType(newValue)));
        recipeVB.getChildren().add(prepHB);

        //Main ingredient combobox and label
        HBox mainIngredientHB = new HBox();
        mainIngredientHB.setSpacing(15);
        mainIngredientHB.getChildren().add(new Label("Main Ingredient"));
        mainIngredientHB.getChildren().add(mainIngredCB);
        mainIngredientHB.getChildren().add(sideIngredientTF);
        sideIngredientTF.setVisible(false);
     //   mainIngredCB.getItems().addAll("Select", "Poultry", "Beef", "Pork", "Fish");
        recipeVB.getChildren().add(mainIngredientHB);
        //depending on the selection here what we display is effected

        //meat label and combobox
        HBox meatHB = new HBox();
        meatHB.setSpacing(50);
        meatHB.getChildren().add(new Label("Meat"));
        meatHB.getChildren().add(meatCB);
        meatCB.getItems().addAll("Select", "Poultry", "Beef", "Pork", "Fish");
        addMeatButton.setOnAction(event -> addMeat());
        meatHB.getChildren().add(addMeatButton);
        recipeVB.getChildren().add(meatHB);

        //produce label and combobox and textField
        HBox produceHB = new HBox();
        produceHB.setSpacing(35);
        produceHB.getChildren().add(new Label("Produce"));
        produceHB.getChildren().add(produceTF);
        produceHB.getChildren().add(new Label("Type:"));
        produceHB.getChildren().add(produceCB);
        produceCB.getItems().addAll("Select", "Vegetable", "Fruit", "Herb", "Starch");
        addProduceButton.setOnAction(event -> addProduce());
        produceHB.getChildren().add(addProduceButton);
        recipeVB.getChildren().add(produceHB);

        //ingredient label, textField and combo box
        HBox ingredientHB = new HBox();
        ingredientHB.setSpacing(26);
        ingredientHB.getChildren().add(new Label("Ingredient"));
        ingredientHB.getChildren().add(ingredientTF);
        ingredientHB.getChildren().add(new Label("  Type:   "));
        ingredientHB.getChildren().add(ingredientCB);
        ingredientCB.getItems().addAll("Select", "Seasoning", "Dairy", "Pantry");
        addIngredientButton.setOnAction(event -> addIngredient());
        ingredientHB.getChildren().add(addIngredientButton);
        recipeVB.getChildren().add(ingredientHB);


        directionsTA.setPrefWidth(MainScene.WIDTH/2);
        directionsTA.setPrefHeight((MainScene.HEIGHT)/3.0);
        // recipeLV.setPrefWidth(MainScene.WIDTH);
        // recipeVB.getChildren().add(ingredientLabel);
        HBox directionHB = new HBox();
        directionHB.setSpacing(15);
        directionHB.getChildren().add(new Label("Directions"));
        directionHB.getChildren().add(directionsTA);
        recipeVB.getChildren().add(directionHB);

       // recipeVB.getChildren().add(recipeLabel);
       // recipeVB.getChildren().add(recipeLV);

        HBox buttonHB = new HBox();
        buttonHB.setSpacing(15);
        buttonHB.getChildren().add(addButton);
        buttonHB.getChildren().add(removeButton);
        buttonHB.getChildren().add(returnButton);
        recipeVB.getChildren().add(buttonHB);

        addButton.setOnAction(event -> addRecipe());
        removeButton.setOnAction((event -> removeRecipe()));
        returnButton.setOnAction(event -> returnToMain());

        pane.add(recipeVB,1,1);

        this.setRoot(pane);

    }

    private void addIngredient() {
        Ingredient obj = new Ingredient(ingredientCB.getSelectionModel().getSelectedItem(), ingredientTF.getText());
        mIngredientArrayList.add(obj);

        ingredientCB.getSelectionModel().selectFirst();
        ingredientTF.clear();
    }

    private void addProduce() {
        Produce obj = new Produce(produceCB.getSelectionModel().getSelectedItem(), produceTF.getText());
        mProduceArrayList.add(obj);

        produceCB.getSelectionModel().selectFirst();
        produceTF.clear();
    }

    private void addMeat() {
        Meat obj = new Meat(meatCB.getSelectionModel().getSelectedItem());
        mMeatArrayList.add(obj);

        meatCB.getSelectionModel().selectFirst();
    }

    private void dishDisplay(String newValue) {
        ObservableList<String> mainIngredients = FXCollections.observableArrayList();

        switch(dishCB.getSelectionModel().getSelectedItem())
        {
            case "Main Dish":
                mainDish = true;
                mainIngredients = FXCollections.observableArrayList("Select", "Poultry", "Beef", "Pork", "Fish");
                break;
            case "Side Dish":
                mainDish = false;
                sideIngredientTF.setVisible(true);
                mainIngredients = FXCollections.observableArrayList("Select", "Vegetable", "Fruit", "Starch");
                break;
        }

        mainIngredCB.setItems(mainIngredients);
    }



    private void returnToMain() {
        ViewNavigator.loadScene("Nothing To Eat", new MainScene());
    }

    private void removeRecipe() {
    }

    private void addRecipe() {
        String title, direction, prep;
        title = recipeTitleTF.getText();
        direction = directionsTA.getText();
        prep = prepCB.getSelectionModel().getSelectedItem();
        if (mainDish){
            MainDish dish = new MainDish(title, mIngredientArrayList,
                    new Meat(mainIngredCB.getSelectionModel().getSelectedItem()));
            recipeList.add(new Recipe(dish, title, mMeatArrayList, mProduceArrayList,
                    direction, prep ));
        } else {
            SideDish dish = new SideDish(title, mIngredientArrayList,
                    new Produce(mainIngredCB.getSelectionModel().getSelectedItem(), sideIngredientTF.getText()));
            recipeList.add(new Recipe(dish, title, mMeatArrayList, mProduceArrayList,
                    direction, prep ));
        }
        updateDisplay();
    }

    private void updateDisplay() {
        recipeTitleTF.clear();
        dishCB.getSelectionModel().selectFirst();
        prepCB.getSelectionModel().selectFirst();
        mainIngredCB.getSelectionModel().selectFirst();
        meatCB.getSelectionModel().selectFirst();
        produceTF.clear();
        produceCB.getSelectionModel().selectFirst();
        ingredientTF.clear();
        ingredientCB.getSelectionModel().selectFirst();
        directionsTA.clear();

    }


}
