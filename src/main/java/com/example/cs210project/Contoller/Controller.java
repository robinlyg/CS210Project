package com.example.cs210project.Contoller;



import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {

    //our controller theInstance
    private static Controller theInstance;
//    private ObservableList<Ingredient> mAllIngredients;
//    private ObservableList<Produce> mAllProduce;
//    private ObservableList<Meat> mAllMeat;
//    private String fileName = "";
    //will need our observable lists

    //private constructor
    private Controller(){}

    //getter
    public static Controller getInstance()
    {
        if(theInstance == null)
        {
            theInstance = new Controller();
            //TODO connect theInstance to our observableLists and their Model.populate()
            //  theInstance.mAllIngredients = Stock.populateList(fileName);
        }

        return theInstance;

    }
    // public ObservableList<Ingredient> getAllIngredients() {return ingredients;}

    //TODO getters for our observable lists

    //TODO a void save method to write to txt/binary file
    public void saveData(){}


    /*commented out what is different from 112 example
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}