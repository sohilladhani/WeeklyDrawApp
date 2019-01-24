package com.sohilladhani.weeklydrawapplication.main.controllers;

import com.sohilladhani.weeklydrawapplication.customerinfo.controllers.CustomerInfoController;
import com.sohilladhani.weeklydrawapplication.customerinfo.dao.CustomerDAO;
import com.sohilladhani.weeklydrawapplication.customerinfo.dao.SQLiteCustomerDAO;
import com.sohilladhani.weeklydrawapplication.customerinfo.models.CustomerInfoModel;
import com.sohilladhani.weeklydrawapplication.weeklydraw.controllers.WeeklyDrawController;
import com.sohilladhani.weeklydrawapplication.weeklydraw.dao.SQLiteWeeklyDrawDAO;
import com.sohilladhani.weeklydrawapplication.weeklydraw.dao.WeeklyDrawDAO;
import com.sohilladhani.weeklydrawapplication.weeklydraw.models.WeeklyDrawModel;
import com.sohilladhani.weeklydrawapplication.weeklydraw.winners.controllers.WeeklyWinnersController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    Button customerInfoButton;
    @FXML
    Button doWeeklyDrawButton;
    @FXML
    Button weeklyWinnersButton;

    private CustomerDAO buildCustomerDAO() {
        return new SQLiteCustomerDAO();
    }

    private CustomerInfoModel buildCustomerInfoModel() {
        return new CustomerInfoModel(buildCustomerDAO());
    }

    private CustomerInfoController buildCustomerInfoController(Stage stage) {
        return new CustomerInfoController(buildCustomerInfoModel(), stage);
    }

    private WeeklyDrawDAO buildWeeklyDrawDAO() {
        return new SQLiteWeeklyDrawDAO();
    }

    private WeeklyDrawModel buildWeeklyDrawModel() {
        return new WeeklyDrawModel(buildWeeklyDrawDAO());
    }

    private WeeklyDrawController buildWeeklyDrawController(Stage stage) {
        return new WeeklyDrawController(buildCustomerInfoModel(), buildWeeklyDrawModel(), stage);
    }

    private WeeklyWinnersController buildWeeklyWinnersController(Stage stage) {
        return new WeeklyWinnersController(buildWeeklyDrawModel(), stage);
    }

    public void onCustomerInfoButtonPressed(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/sohilladhani/weeklydrawapplication" +
                "/customerinfo/ui/customer_info.fxml"));
        Stage customerInfoStage = new Stage();
        fxmlLoader.setControllerFactory(c -> buildCustomerInfoController(customerInfoStage));
        Scene customerInfoScene = null;
        try {
            customerInfoScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage mainStage = (Stage) customerInfoButton.getScene().getWindow();
        mainStage.toBack();

        customerInfoStage.setScene(customerInfoScene);
        customerInfoStage.setTitle("Customer Information");
        customerInfoStage.setResizable(false);
        customerInfoStage.show();
        customerInfoStage.setOnCloseRequest(t -> mainStage.toFront());
    }

    public void onDoWeeklyDrawButtonPressed(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/sohilladhani/weeklydrawapplication" +
                "/weeklydraw/ui/weekly_draw_winner.fxml"));
        Stage weeklyDrawWinnerStage = new Stage();
        fxmlLoader.setControllerFactory(c -> buildWeeklyDrawController(weeklyDrawWinnerStage));
        Scene weeklyDrawWinnerScene = null;
        try {
            weeklyDrawWinnerScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage mainStage = (Stage) doWeeklyDrawButton.getScene().getWindow();
        mainStage.toBack();

        weeklyDrawWinnerStage.setScene(weeklyDrawWinnerScene);
        weeklyDrawWinnerStage.setTitle("Weekly Draw Results");
        weeklyDrawWinnerStage.setResizable(false);
        weeklyDrawWinnerStage.show();
        weeklyDrawWinnerStage.setOnCloseRequest(t -> mainStage.toFront());
    }

    public void onWeeklyWinnersButtonPressed(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/sohilladhani/weeklydrawapplication" +
                "/weeklydraw/winners/ui/weekly_winners_details.fxml"));
        Stage weeklyWinnersStage = new Stage();
        fxmlLoader.setControllerFactory(c -> buildWeeklyWinnersController(weeklyWinnersStage));
        Scene weeklyWinnersScene = null;
        try {
            weeklyWinnersScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage mainStage = (Stage) weeklyWinnersButton.getScene().getWindow();
        mainStage.toBack();

        weeklyWinnersStage.setScene(weeklyWinnersScene);
        weeklyWinnersStage.setTitle("Weekly Winners");
        weeklyWinnersStage.setResizable(false);
        weeklyWinnersStage.show();
        weeklyWinnersStage.setOnCloseRequest(t -> mainStage.toFront());
    }
}