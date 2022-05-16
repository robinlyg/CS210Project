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

//unable to do one LV becuase a LV of Stock would also include the recipes
public class removeScene extends Scene {

    Controller controller = Controller.getInstance();


//    private ObservableList<Ingredient> ingredientList;
//    private ObservableList<Meat> meatList;
//    private ObservableList<Produce> produceList;
//
//    private Ingredient selectedIngredient;
//    private Meat selectedMeat;
//    private Produce selectedProduce;
//
//    private ListView<Ingredient> mIngredientListView = new ListView<>();
//    private ListView<Meat> mMeatListView = new ListView<>();
//    private ListView<Produce> mProduceListView = new ListView<>();

    private ObservableList<Food> stockList;
    private ListView<Food> mStockListView = new ListView<>();

    private Food selectedFood;

    Button removeButton = new Button("Remove");
    Button returnButton = new Button("Return");

    //scene method
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

//        mIngredientListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> updateSelectedFood(newValue));
//        mMeatListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> updateSelectedFood(newValue));
//        mProduceListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> updateSelectedFood(newValue));
//
//        mIngredientListView.setPrefWidth(MainScene.WIDTH);
//        mProduceListView.setPrefWidth(MainScene.WIDTH);
//        mMeatListView.setPrefWidth(MainScene.WIDTH);
//
//
//        ingredientList = Stock.getInstance().getAllIngredients();
//        mIngredientListView.setItems(ingredientList);
//        produceList = Stock.getInstance().getAllProduce();
//        mProduceListView.setItems(produceList);
//        meatList = Stock.getInstance().getAllMeat();
//        mMeatListView.setItems(meatList);
//
//        removeVB.getChildren().add(mIngredientListView);
//        removeVB.getChildren().add(mMeatListView);
//        removeVB.getChildren().add(mProduceListView);


        mStockListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> updateSelectedFood(newValue));
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
        pane.add(buttonsHB,1,2);



        this.setRoot(pane);
    }

    private void returnToMain() {
        //TODO need to clear LV's

        ViewNavigator.loadScene("Nothing To Eat", new MainScene());
    }

    private void updateSelectedFood(Object newValue) {
//        if (newValue instanceof Ingredient) {
//            Ingredient o = (Ingredient) newValue;
//            selectedIngredient = o;
//
//        } else if (newValue instanceof Produce) {
//            Produce o = (Produce) newValue;
//            selectedProduce = o;
//        } else if (newValue instanceof Meat) {
//            Meat o = (Meat) newValue;
//            selectedMeat = o;
//        }
        selectedFood = (Food) newValue;

    }

//    private void typeDisplay() {
//        String list = typeCB.getSelectionModel().getSelectedItem();
//        Stock.getInstance().populateStock();
//        //TODO...each time they select it is shows the full list again...
//        //TODO...add method for all foods are doubling up, can enter one of each category but when displaying it shows several
//
//        if (list.equals("Meat")) {
//            mMeatListView.setItems(Stock.getInstance().getAllMeat());
//        } else if (list.equals("Produce")) {
//            mProduceListView.setItems(Stock.getInstance().getAllProduce());
//        } else {
//            mIngredientListView.setItems(Stock.getInstance().getAllIngredients());
//        }
//    }

    private void removeStockItem() {
        //should be based on selected
//        if (selectedProduce == null && selectedIngredient == null && selectedMeat == null)
//            return;
//
//            //if selected type is produce
//        else if (selectedProduce != null) {
//            //removeFromStock(produce)
//            Stock.getInstance().removeFromStock(selectedProduce);
//        }
//        //if selected type is meat
//        else if (selectedMeat != null) {
//            //removeFromStock(meat)
//            Stock.getInstance().removeFromStock(selectedMeat);
//        }
//        //else
//        else if (selectedIngredient != null) {
//            //removeFromStock(ingredient)
//            Stock.getInstance().removeFromStock(selectedIngredient);
//        }
        if (selectedFood == null)
            return;
        else
            stockList.remove(selectedFood);


        updateDisplay();


        //OR simply removeFromStock(slectedItem) and given the item type the compiler knows which removeFromStock() to use?


    }

    private void updateDisplay() {
//        mProduceListView.refresh();
//        mIngredientListView.refresh();
//        mMeatListView.refresh();
        mStockListView.refresh();
    }
}
