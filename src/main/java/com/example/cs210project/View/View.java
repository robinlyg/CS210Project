package com.example.cs210project.View;

import com.example.cs210project.Contoller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Nothing To Eat", new MainScene());
    }

    @Override
    public void stop() throws Exception {
        Controller.getInstance().saveData();
    }


    public static void main(String[] args) {
        launch();
    }
}
