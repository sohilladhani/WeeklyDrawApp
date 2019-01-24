package com.sohilladhani.weeklydrawapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeeklyDrawApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/sohilladhani/weeklydrawapplication" +
                "/main/ui/main.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        stage.setScene(mainScene);
        stage.setTitle("Welcome to Weekly Draw Application");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
