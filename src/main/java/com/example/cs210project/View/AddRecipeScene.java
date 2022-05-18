package com.example.cs210project.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.function.BiConsumer;

public class AddRecipeScene extends Scene {
    //properties
    private TextField recipeTitleTF = new TextField();
    private TextArea ingredientTA = new TextArea();
    private Button addButton = new Button("Add Recipe");
    private Button removeButton = new Button("Remove Recipe");
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

        VBox recipeVB = new VBox();
        recipeVB.setSpacing(15);
        recipeVB.getChildren().add(recipeTitleTF);
        ingredientTA.setPrefWidth(MainScene.WIDTH);
        ingredientTA.setPrefHeight((MainScene.HEIGHT)/1.5);
        recipeVB.getChildren().add(ingredientTA);

        HBox buttonHB = new HBox();
        buttonHB.setSpacing(15);
        buttonHB.getChildren().add(addButton);
        buttonHB.getChildren().add(removeButton);

        recipeVB.getChildren().add(buttonHB);

        pane.add(recipeVB,0,1);




        this.setRoot(pane);

    }
}
