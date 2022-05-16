package com.example.cs210project.View;

import com.example.cs210project.Contoller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.FontWeight;

public class MainScene extends Scene {
    //TODO all our private variables whihc include the size of the frame, any image, textField,
    // label, box ListView, Button...
    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;
    public static final double BUTTON_WIDTH = 155;
    public static final double BUTTON_HEIGHT = 55;
    public static final FontWeight BOLD_FONT = FontWeight.BOLD;

    private Label title = new Label("\tNothing to Eat!");

    //main page button selection
    private Button addToStockButton = new Button("Add to Stock");
    private Button removeFromStockButton = new Button("Remove from Stock");
    private Button generateFromStockButton = new Button("Generate from Stock");
    private Button generateShoppingListButton = new Button("Generate Shopping List");
    private Button addRecipeButton = new Button("Add Recipe");
    private Button viewMyStockButton = new Button("View My Stock");



    //note, when asked to import, imported ours not Java's Controller class
    private Controller mController = Controller.getInstance();

    public MainScene(){
        super(new GridPane(), WIDTH, HEIGHT);
        GridPane pane = new GridPane();

        //center what appears on the grid with Row and Column Constraints
        RowConstraints row1 = new RowConstraints();
        row1.setPrefHeight(25);
        pane.getRowConstraints().add(row1);
        ColumnConstraints colC = new ColumnConstraints();
        colC.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(colC);
        pane.setAlignment(Pos.CENTER);

        //6 buttons - addToStockButton, removeFromStockButton, generateFromStockButton, generateShoppingListButton, addRecipeButton, myStock
        //stored in a VBox all with the same WIDTH and HEIGHT
        //TODO: find way to make title bold, larger and centered


        addToStockButton.setPrefWidth(BUTTON_WIDTH);
        addToStockButton.setPrefHeight(BUTTON_HEIGHT);
        addToStockButton.setStyle("-fx-background-color: #fdf0ca; -fx-border-color: BLACK ");

        removeFromStockButton.setPrefWidth(BUTTON_WIDTH);
        removeFromStockButton.setPrefHeight(BUTTON_HEIGHT);
        removeFromStockButton.setStyle("-fx-background-color: #f6ccca; -fx-border-color: BLACK");

        generateFromStockButton.setPrefWidth(BUTTON_WIDTH);
        generateFromStockButton.setPrefHeight(BUTTON_HEIGHT);
        generateFromStockButton.setStyle("-fx-background-color: #d8e6fa; -fx-border-color: BLACK");

        generateShoppingListButton.setPrefWidth(BUTTON_WIDTH);
        generateShoppingListButton.setPrefHeight(BUTTON_HEIGHT);
        generateShoppingListButton.setStyle("-fx-background-color: #d3e6d2; -fx-border-color: BLACK");

        addRecipeButton.setPrefHeight(BUTTON_HEIGHT);
        addRecipeButton.setPrefWidth(BUTTON_WIDTH);
        addRecipeButton.setStyle("-fx-background-color: #dfd3e5; -fx-border-color: BLACK");

        viewMyStockButton.setPrefWidth(BUTTON_WIDTH);
        viewMyStockButton.setPrefHeight(BUTTON_HEIGHT);
        viewMyStockButton.setStyle("-fx-background-color: #fde4ca; -fx-border-color: BLACK");

        VBox mainButtonSelectionHB = new VBox();
        mainButtonSelectionHB.setSpacing(15);
        mainButtonSelectionHB.getChildren().add(title);
        mainButtonSelectionHB.getChildren().add(addToStockButton);
        mainButtonSelectionHB.getChildren().add(removeFromStockButton);
        mainButtonSelectionHB.getChildren().add(generateFromStockButton);
        mainButtonSelectionHB.getChildren().add(generateShoppingListButton);
        mainButtonSelectionHB.getChildren().add(addRecipeButton);
        mainButtonSelectionHB.getChildren().add(viewMyStockButton);
        //oddly if we change the 1,1 to 0,0 is removes our button sizes
        pane.add(mainButtonSelectionHB, 1,1);

        //addToStockButton setOnAction to call addToStock()
        addToStockButton.setOnAction(event->addToStock());
        removeFromStockButton.setOnAction(event-> removeScene());

        //dont remove
        this.setRoot(pane);
    }

    private void addToStock()
    {
        ViewNavigator.loadScene("Add To Stock", new addToStockView());


    }
    private void removeScene()
    {
        ViewNavigator.loadScene("Remove From Stock", new removeScene());


    }
}
