package com.example.cs210project.View;

import com.example.cs210project.Contoller.Controller;
import com.example.cs210project.Model.Recipe;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.function.BiConsumer;

public class AddRecipeScene extends Scene {
    //properties
    private TextField recipeTitleTF = new TextField();
    private TextArea ingredientTA = new TextArea();

    private Button addButton = new Button("Add Recipe");
    private Button removeButton = new Button("Remove Recipe");
    private Button returnButton = new Button("Return");

    private Label titleLabel = new Label("Recipe Title");
    private Label ingredientLabel = new Label("List Ingredients");
    private Label recipeLabel = new Label("Recipes");

    private Controller controller = Controller.getInstance();
    private ObservableList<Recipe> recipeList;

    private ListView<Recipe> recipeLV = new ListView<>();

    private ComboBox<String> prepCB = new ComboBox<>();
    //TODO, not sure if we'll need or want
    //private TextArea processTA = new TextArea();

    //mainMethod
    public AddRecipeScene(){
        super(new GridPane(), MainScene.WIDTH, MainScene.HEIGHT);
        GridPane pane = new GridPane();

//        RowConstraints row1 = new RowConstraints();
//        row1.setPrefHeight(25);
//        pane.getRowConstraints().add(row1);
//        ColumnConstraints colC = new ColumnConstraints();
//        colC.setHalignment(HPos.CENTER);
//        pane.getColumnConstraints().add(colC);
//        pane.setAlignment(Pos.CENTER);

        recipeList = controller.getAllRecipes();
        recipeLV.setItems(recipeList);

        VBox recipeVB = new VBox();
        //recipeVB.setSpacing(15);
        recipeVB.getChildren().add(titleLabel);
        recipeVB.getChildren().add(recipeTitleTF);
        ingredientTA.setPrefWidth(MainScene.WIDTH);
        ingredientTA.setPrefHeight((MainScene.HEIGHT)/2.0);
        recipeLV.setPrefWidth(MainScene.WIDTH);

        recipeVB.getChildren().add(ingredientLabel);
        recipeVB.getChildren().add(ingredientTA);

        recipeVB.getChildren().add(recipeLabel);
        recipeVB.getChildren().add(recipeLV);

        HBox buttonHB = new HBox();
        buttonHB.setSpacing(15);
        buttonHB.getChildren().add(addButton);
        buttonHB.getChildren().add(removeButton);
        buttonHB.getChildren().add(returnButton);
        recipeVB.getChildren().add(buttonHB);

        addButton.setOnAction(event -> addRecipe());
        removeButton.setOnAction((event -> removeRecipe()));
        returnButton.setOnAction(event -> returnToMain());



        pane.add(recipeVB,0,1);

        pane.add(prepCB, 0, 0);
        prepCB.getItems().addAll("Baked", "Grilled", "Fried", "Boiled");
        prepCB.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) -> selectedPrepType(newValue)));


        this.setRoot(pane);

    }

    private void selectedPrepType(String newValue) {
        //TODO : what to do with prep type
    }

    private void returnToMain() {
        ViewNavigator.loadScene("Nothing To Eat", new MainScene());
    }

    private void removeRecipe() {
    }

    private void addRecipe() {
        String title, ingredients;
        title = recipeTitleTF.getText();
        ingredients = ingredientTA.getText();
//TODO need to add new recipe object but how do we deconstruct user input
        recipeList.add(new Recipe());

        updateDisplay();
    }

    private void updateDisplay() {
        recipeLV.refresh();
    }


}
