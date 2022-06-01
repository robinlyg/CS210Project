package com.example.cs210project.View;

import com.example.cs210project.Contoller.Controller;
import com.example.cs210project.Model.Food;
import com.example.cs210project.View.MainScene;
import com.example.cs210project.View.ViewNavigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class removeScene extends Scene {

    Controller controller = Controller.getInstance();
    private Label titleLabel = new Label("Stock");
    private ObservableList<Food> stockList;
    private ListView<Food> mStockListView = new ListView<>();
    private Food selectedFood;
    private Button removeButton = new Button("Remove");
    private Button returnButton = new Button("Return");

    public removeScene() {

        super(new GridPane(), MainScene.WIDTH, MainScene.HEIGHT);
        GridPane pane = new GridPane();

        RowConstraints row1 = new RowConstraints();
        row1.setPrefHeight(25);
        pane.getRowConstraints().add(row1);
        ColumnConstraints colC = new ColumnConstraints();
        colC.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(colC);
        pane.setAlignment(Pos.CENTER);

        VBox removeVB = new VBox();
        removeVB.setSpacing(15);
        removeVB.getChildren().add(titleLabel);

        //get the current selected item and pass to updateSelectedFood()
        mStockListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue,
                                                                               newValue) -> updateSelectedFood(newValue));
        mStockListView.setPrefWidth(MainScene.WIDTH);
        stockList = controller.getAllInStock();
        mStockListView.setItems(stockList);

        removeVB.getChildren().add(mStockListView);

        HBox buttonsHB = new HBox();
        buttonsHB.setSpacing(15);
        buttonsHB.getChildren().add(removeButton);
        buttonsHB.getChildren().add(returnButton);

        removeButton.setOnAction(event -> removeStockItem());
        returnButton.setOnAction(event -> returnToMain());

        pane.add(removeVB, 1, 1);
        pane.add(buttonsHB, 1, 2);

        this.setRoot(pane);
    }

    private void returnToMain() {
        ViewNavigator.loadScene("Nothing To Eat", new MainScene());
    }
    //set the selectedFood to the new value
    private void updateSelectedFood(Object newValue) {
        selectedFood = (Food) newValue;
    }

    //use the selectedFood value, if not null, to call the remove(selectedFood)
    private void removeStockItem() {

        if (selectedFood == null)
            return;
        else
            stockList.remove(selectedFood);

        updateDisplay();
    }

    private void updateDisplay() {
        mStockListView.refresh();
    }
}
