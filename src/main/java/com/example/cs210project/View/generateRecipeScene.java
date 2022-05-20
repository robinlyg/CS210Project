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
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

import java.util.ArrayList;

public class generateRecipeScene extends Scene {

    Controller controller = Controller.getInstance();
    private Label titleLabel = new Label("Recipe List");
    private Label mainIngredientErrLabel = new Label("Must Choose a Main Ingredient");
    private ObservableList<Recipe> recipeList;
    private ListView<Recipe> mRecipeListView = new ListView<>();
    private TextArea viewRecipeTA = new TextArea();
    private Recipe selectedRecipe;
    private CheckBox fromStockCheck = new CheckBox("Generate from Stock");
    private ComboBox<String> dishTypeCB = new ComboBox<>();
    private ComboBox<String> mainIngredientCB = new ComboBox<>();
    private ComboBox<String> prepCB = new ComboBox<>();
    private TextField produceTF = new TextField();
    private Button generateButton = new Button("Generate");
    private Button removeButton = new Button("Remove");
    private Button returnButton = new Button("Return");
    private boolean isMainDish;

    public generateRecipeScene() {

        super(new GridPane(), MainScene.WIDTH, MainScene.HEIGHT);
        GridPane pane = new GridPane();

        RowConstraints row1 = new RowConstraints();
        row1.setPrefHeight(25);
        pane.getRowConstraints().add(row1);
        ColumnConstraints colC = new ColumnConstraints();
        colC.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(colC);
        pane.setAlignment(Pos.CENTER);



        VBox generateRecVB = new VBox();
        generateRecVB.setSpacing(15);

        HBox generateHB = new HBox();
        generateHB.setSpacing(15);
        generateHB.getChildren().add(new Label("Dish Type"));
        dishTypeCB.getItems().addAll("Main Dish", "Side Dish");
        dishTypeCB.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> dishDisplay(newValue));
        generateHB.getChildren().add(dishTypeCB);
        generateHB.getChildren().add(mainIngredientCB);
        generateHB.getChildren().add(produceTF);
        produceTF.setVisible(false);
        generateHB.getChildren().add(generateButton);

        generateRecVB.getChildren().add(generateHB);
        //Prep type label and combo box
        HBox prepHB = new HBox();
        prepHB.setSpacing(15);
        prepHB.getChildren().add(new Label("Preparation"));
        prepHB.getChildren().add(prepCB);
        prepCB.getItems().addAll("Select","Baked", "Grilled", "Fried", "Boiled");
        prepHB.getChildren().add(fromStockCheck);
        prepHB.getChildren().add(mainIngredientErrLabel);
        mainIngredientErrLabel.setTextFill(Color.RED);
        mainIngredientErrLabel.setVisible(false);

        generateRecVB.getChildren().add(prepHB);
        
        generateRecVB.getChildren().add(titleLabel);
        mRecipeListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue,
                                                                                newValue) -> displaySelectedRecipe(newValue));
        mRecipeListView.setPrefWidth(MainScene.WIDTH);
        recipeList = controller.getAllRecipes();
        mRecipeListView.setItems(recipeList);

        generateRecVB.getChildren().add(mRecipeListView);

        viewRecipeTA.setPrefWidth(MainScene.WIDTH/2.0);
        viewRecipeTA.setPrefHeight(MainScene.HEIGHT/2.0);
        generateRecVB.getChildren().add(viewRecipeTA);

        HBox buttonsHB = new HBox();
        buttonsHB.setSpacing(15);
        buttonsHB.getChildren().add(removeButton);
        buttonsHB.getChildren().add(returnButton);

        removeButton.setOnAction(event -> removeRecipe());
        returnButton.setOnAction(event -> returnToMain());
        generateButton.setOnAction(event -> generateRecipe());

        pane.add(generateRecVB, 1, 1);
        pane.add(buttonsHB, 1, 2);

        this.setRoot(pane);

    }

    private void dishDisplay(String newValue) {
        ObservableList<String> mainIngredients = FXCollections.observableArrayList();
        switch(newValue)
        {
            case "Main Dish":
                isMainDish = true;
                mainIngredients = FXCollections.observableArrayList("Select", "Poultry", "Beef", "Pork", "Fish");
                break;
            case "Side Dish":
                isMainDish = false;
                produceTF.setVisible(true);
                mainIngredients = FXCollections.observableArrayList("Select", "Vegetable", "Fruit", "Root");
                break;
        }

        mainIngredientCB.setItems(mainIngredients);
        mainIngredientErrLabel.setVisible(false);
    }

    private void generateRecipe() {
        Recipe newRecipe = new Recipe();
        MainDish mainDish;
        SideDish sideDish;
        String name;
        String prep = prepCB.getSelectionModel().getSelectedItem();
        Meat mainMeat = null;
        Produce mainProduce = null;
        ArrayList<Meat> meats = new ArrayList<>();
        ArrayList<Produce> produce = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Ingredient> seasonings = new ArrayList<>();
        ArrayList<Ingredient> pantry = new ArrayList<>();
        ArrayList<Ingredient> sauces = new ArrayList<>();
        ArrayList<Ingredient> dairy = new ArrayList<>();
        String directions;
        String baked = "", fried = "", boiled = "", grilled = "";
        int temp;

        //add prep type to recipe
        newRecipe.setPreparation(prep);

        //determine what the main ingredient is
        if (isMainDish){
            mainMeat = new Meat(mainIngredientCB.getSelectionModel().getSelectedItem());
        } else {
            mainProduce = new Produce(mainIngredientCB.getSelectionModel().getSelectedItem(), produceTF.getText());
        }

        //get ingredients pool
        if (fromStockCheck.isSelected()) {
            ObservableList<Food> stock = controller.getAllInStock();
            for (Food food: stock) {
                if (food instanceof Meat)
                    meats.add((Meat) food);
                if (food instanceof Produce)
                    produce.add((Produce) food);
                if (food instanceof Ingredient)
                    ingredients.add((Ingredient) food);
                //filter ingredients
                seasonings = filterSeasonings(ingredients);
                pantry = filterPantry(ingredients);
                dairy = filterDairy(ingredients);
                sauces = filterSauces(ingredients);
            }
        } else {
            seasonings = getRandomSeasoningList();
            pantry = getRandomPantryList();
            dairy = getRandomDairyList();
            sauces = getRandomSauceList();
        }
        if (mainMeat != null) {
            switch (mainMeat.getType()) {
                case "Poultry" -> {
                    temp = 165;
                    baked = "Heat oven to a temperature of 400 degrees F.\n" +
                            "Bake poultry for 20 to 30 minutes or to an internal temperature of " + temp + " degrees.\n";
                    fried = "Heat enough oil to completely submerge poultry to 350 degrees F.\n" +
                            "Fry each piece for about 14 minutes or to an internal temperature of " + temp + " degrees.\n";
                    boiled = "Bring a pot of chicken broth to a boil then set heat to medium.\n" +
                            "Place poultry into pot and cover for about 10 min,\ninternal temperature should be " +
                            temp + "  degrees.\n";
                    grilled = "Heat grill to a temperature of 350 degrees F.\n" +
                            "If poultry is boneless: cook 5 to 6 minutes per side in direct heat.\n" +
                            "If poultry is bone-in: cook 15 to 20 minutes per side in indirect heat.\n" +
                            "Check that poultry has reached an internal temperature of " + temp + " degrees.\n";
                }
                case "Beef" -> {
                    temp = 145;
                    baked = "Let beef come to room temperature. Heat oven to a temperature of 450 degrees F.\n" +
                            "Bake beef for 12 to 16 minutes or to an internal temperature of " + temp + " degrees.\n";
                    fried = "Heat enough oil to cover a pan and heat up for a couple of minutes on High heat.\n" +
                            "Fry beef for about 2.5 minutes per side\n" +
                            "or to an internal temperature of " + temp + " degrees.\n";
                    boiled = "Brown beef in the bottom of a stew pot on medium high heat.\n" +
                            "Fill pot with beef broth and turn heat to low to simmer for 1.5 hours.\n" +
                            "Beef should be at an internal temperature of " + temp + " degrees.\n";
                    grilled = "Let beef come to room temperature. Set grill to High heat and allow to heat for a few minutes.\n" +
                            "Grill beef for about 3 minutes per side over direct heat.\n" +
                            "Check that beef has reached an internal temperature of " + temp + " degrees.\n";
                }
                case "Pork" -> {
                    temp = 145;
                    baked = "Preheat oven to a temperature of 350 degrees F.\n" +
                            "Bake pork for 20 to 40 minutes (depending on thickness)\n" +
                            "or to an internal temperature of " + temp + " degrees.\n";
                    fried = "Heat enough oil to cover a pan and heat up for a couple of minutes on High heat.\n" +
                            "Fry pork for about 2.5 minutes per side\n" +
                            "or to an internal temperature of " + temp + " degrees.\n";
                    boiled = "Brown pork in the bottom of a stew pot on medium high heat.\n" +
                            "Fill pot with beef or chicken broth and turn heat to low to simmer for 1.5 hours.\n" +
                            "Pork should be at an internal temperature of " + temp + " degrees.\n";
                    grilled = "Let pork come to room temperature. Let the grill preheat to about 450.\n" +
                            "Grill pork for about 4 to 6 minutes per side over direct heat.\n" +
                            "Check that pork has reached an internal temperature of " + temp + " degrees.\n";
                }
                case "Fish" -> {
                    temp = 155;
                    baked = "Preheat oven to a temperature of 375 degrees F.\n" +
                            "Bake fish for 15 to 20 minutes or to an internal temperature of " + temp + " degrees.\n" +
                            "Fish should flake easily with a fork.\n";
                    fried = "Heat enough oil to completely submerge fish to 375 degrees F.\n" +
                            "Fry each piece for about 6 minutes or to an internal temperature of " + temp + " degrees.\n";
                    boiled = "Bring a pot of water to a boil and add fish in a wire basket for easy removal.\n" +
                            "After about 10 to 12 minutes, take fish out of water. Fish should flake easily with a fork.\n" +
                            "Internal temperature should be " + temp + "  degrees.\n";
                    grilled = "Wrap fish in foil and preheat grill to about 400 degrees F.\n" +
                            "Let cook for 4 to 6 minutes per side in direct heat.\n" +
                            "Check that fish has reached an internal temperature of " + temp + " degrees.\n";
                }
                default -> {
                    mainIngredientErrLabel.setVisible(true);
                    return;
                }
            }
        }
        if (mainProduce != null) {
            switch (mainProduce.getType()) {
                case "Vegetable" -> {
                    baked = "Placeholder";
                    fried = "Placeholder";
                    boiled = "Placeholder";
                    grilled = "Placeholder";
                }
                case "Fruit" -> {
                    baked = "Placeholder";
                    fried = "Placeholder";
                    boiled = "Placeholder";
                    grilled = "Placeholder";
                }
                case "Root" -> {
                    baked = "Placeholder";
                    fried = "Placeholder";
                    boiled = "Placeholder";
                    grilled = "Placeholder";
                }
                default -> {
                    mainIngredientErrLabel.setVisible(true);
                    return;
                }
            }
        }


        //set up dish so that indexes 0 - 2 are seasonings, 3 is dairy, 4 is pantry, and 5 is sauce
        ArrayList<Ingredient> dishIngredients = new ArrayList<>();
        String dairyItem = "";
        String pantryItem = "";
        if (isMainDish) {
            assert mainMeat != null;
            name = prep + " " + mainMeat.getType(); //possibly generate a fun flourish "ala king"
            //get 3 seasonings
            for (int i = 0; i < 3; i++){
                dishIngredients.add(getRandomSeasoning(seasonings));
            }
            //get 1 dairy
            dishIngredients.add(getRandomSeasoning(dairy));
            //get 1 pantry
            dishIngredients.add(getRandomSeasoning(pantry));
            //get 1 sauce
            dishIngredients.add(getRandomSeasoning(sauces));
            mainDish = new MainDish(name, dishIngredients, mainMeat);
            newRecipe.setMainIngredient(mainDish);  //add to recipe

            //start assembling directions
            //mix ingredients except dairy (index 3)
            directions = "Season " + mainMeat.getType() + " with " + mainDish.getIngredients().get(0).getName() + ", " +
                    mainDish.getIngredients().get(1).getName() + ", and " + mainDish.getIngredients().get(2).getName() +
                    ".\nThen take " + mainDish.getIngredients().get(4).getName() + " and place in a pot of boiling water.\n" +
                    "Bring down heat and simmer for 15 to 20 min to preferred softness.\n" +
                    "If desired, add some " + mainDish.getIngredients().get(5).getName() + " before or after cooking.\n";
            dairyItem = mainDish.getIngredients().get(3).getName();
            pantryItem = mainDish.getIngredients().get(4).getName();
        } else {
            assert mainProduce != null;
            name = prep + " " + mainProduce.getName();
            //get 3 seasonings
            for (int i = 0; i < 3; i++){
                dishIngredients.add(getRandomSeasoning(seasonings));
            }
            //get 1 dairy
            dishIngredients.add(getRandomSeasoning(dairy));
            //get 1 pantry
            dishIngredients.add(getRandomSeasoning(pantry));
            //get 1 sauce
            dishIngredients.add(getRandomSeasoning(sauces));
            sideDish = new SideDish(name, dishIngredients, mainProduce);
            newRecipe.setSideIngredient(sideDish);  //add to recipe

            //start assembling directions
            //mix ingredients except dairy
            directions = "Season " + mainProduce.getName() + " with " + sideDish.getIngredients().get(0).getName() + ", " +
                    sideDish.getIngredients().get(1).getName() + ", and " + sideDish.getIngredients().get(2).getName() +
                    ".\nThen take " + sideDish.getIngredients().get(4).getName() + " and place in a pot of boiling water.\n" +
                    "Bring down heat and simmer for 15 to 20 min to preferred softness.\n" +
                    "If desired, add some " + sideDish.getIngredients().get(5).getName() + " before or after cooking.\n";
            dairyItem = sideDish.getIngredients().get(3).getName();
            pantryItem = sideDish.getIngredients().get(4).getName();
        }

        //set recipe name
        newRecipe.setName(name);

        switch (prep) {
            case "Baked" -> directions += baked;
            case "Fried" -> directions += fried;
            case "Boiled" -> directions += boiled;
            case "Grilled" -> directions += grilled;
        }

        directions += "Place on a bed of " + pantryItem + " with some fresh " + dairyItem + " added to the top.\nEnjoy!";

        newRecipe.setDirections(directions);
        recipeList.add(newRecipe);
        updateDisplay();
    }

    private void displaySelectedRecipe(Recipe newValue) {
        selectedRecipe = newValue;
        viewRecipeTA.setText("");   //clear any previous text before displaying new selection
        if (newValue != null)
            viewRecipeTA.setText(newValue.show());
    }

    private void removeRecipe(){
        if (selectedRecipe == null)
            return;
        else
            recipeList.remove(selectedRecipe);
        updateDisplay();
    }

    private ArrayList<Ingredient> filterSeasonings(ArrayList<Ingredient> ingredients) {
        ArrayList<Ingredient> seasonings = new ArrayList<>();
        for (Ingredient item : ingredients) {
            if (item.getType().equals("Seasoning")) {
                seasonings.add(item);
            }
        }
        return seasonings;
    }

    private ArrayList<Ingredient> getRandomSeasoningList() {
        ArrayList<Ingredient> seasoningList = new ArrayList<>();
        String seasonings = "Salt,Pepper,Old Bay,Italian Seasoning,Rosemary,Thyme,Oregano,Tarragon,Red Pepper," +
                "Sugar,Montreal Steak,Kansas City,Chicago,Seasoned Salt,Cayenne Pepper,Cinnamon,Flour,Almond Flour," +
                "Fried Onions, Bread Crumbs,Onion Powder,Garlic Powder";
        ArrayList<String> seasoningNames = new ArrayList<>(List.of(seasonings.split(",")));

        for (String name: seasoningNames) {
            seasoningList.add(new Ingredient("Seasoning", name));
        }

        return seasoningList;
    }

    private Ingredient getRandomSeasoning(ArrayList<Ingredient> seasonings) {
        Random rand = new Random();
        int index = rand.nextInt(seasonings.size());
        return seasonings.get(index);
    }

    private ArrayList<Ingredient> filterPantry(ArrayList<Ingredient> ingredients) {
        ArrayList<Ingredient> pantry = new ArrayList<>();
        for (Ingredient item : ingredients) {
            if (item.getType().equals("Pantry")) {
                pantry.add(item);
            }
        }
        return pantry;
    }

    private ArrayList<Ingredient> getRandomPantryList() {
        ArrayList<Ingredient> pantry = new ArrayList<>();
        String pantryItems = "Rice,Spaghetti,Ziti,Macaroni,Pasta Shells,Linguine,Rotini";
        ArrayList<String> itemNames = new ArrayList<>(List.of(pantryItems.split(",")));

        for (String name : itemNames) {
            pantry.add(new Ingredient("Pantry", name));
        }
        return pantry;
    }

    private Ingredient getRandomPantryItem(ArrayList<Ingredient> pantry) {
        Random rand = new Random();
        int index = rand.nextInt(pantry.size());
        return pantry.get(index);
    }

    private ArrayList<Ingredient> filterSauces(ArrayList<Ingredient> ingredients) {
        ArrayList<Ingredient> sauces = new ArrayList<>();
        for (Ingredient item : ingredients) {
            if (item.getType().equals("Sauce")) {
                sauces.add(item);
            }
        }
        return sauces;
    }

    private ArrayList<Ingredient> getRandomSauceList() {
        ArrayList<Ingredient> sauceList = new ArrayList<>();
        String sauces = "Vinegar,Olive Oil,Soy Sauce,BBQ Sauce,Ketchup,Mustard,Mayonnaise,Ranch,Italian Dressing," +
                "Water,Orange Juice,Hot Sauce";
        ArrayList<String> sauceNames = new ArrayList<>(List.of(sauces.split(",")));

        for (String name : sauceNames) {
            sauceList.add(new Ingredient("Sauce", name));
        }

        return sauceList;
    }

    private Ingredient getRandomSauce(ArrayList<Ingredient> sauces) {
        Random rand = new Random();
        int index = rand.nextInt(sauces.size());
        return sauces.get(index);
    }

    private ArrayList<Ingredient> filterDairy(ArrayList<Ingredient> ingredients) {
        ArrayList<Ingredient> dairy = new ArrayList<>();
        for (Ingredient item : ingredients) {
            if (item.getType().equals("Dairy")) {
                dairy.add(item);
            }
        }
        return dairy;
    }

    private ArrayList<Ingredient> getRandomDairyList() {
        ArrayList<Ingredient> dairyList = new ArrayList<>();
        String dairy = "Cheddar Cheese,Provolone Cheese,Parmesan Cheese,Cotijan Cheese,American Cheese,Swiss Cheese," +
                "Egg,Mexican Cheese,Pizza Blend Cheese,Cottage Cheese,Sour Cream,Milk,Heavy Cream";
        ArrayList<String> dairyNames = new ArrayList<>(List.of(dairy.split(",")));

        for (String name : dairyNames) {
            dairyList.add(new Ingredient("Dairy", name));
        }
        return dairyList;
    }

    private Ingredient getRandomDairy(ArrayList<Ingredient> dairy) {
        Random rand = new Random();
        int index = rand.nextInt(dairy.size());
        return dairy.get(index);
    }

    private String getRandomProduce(ArrayList<Produce> produce) {
        Random rand = new Random();
        int index = rand.nextInt(produce.size());
        return produce.get(index).getName();
    }

    private void updateDisplay() { mRecipeListView.refresh(); }

    private void returnToMain() {
        ViewNavigator.loadScene("Nothing To Eat", new MainScene());
    }
}
