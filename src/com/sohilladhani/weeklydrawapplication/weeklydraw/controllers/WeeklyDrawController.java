package com.sohilladhani.weeklydrawapplication.weeklydraw.controllers;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import com.sohilladhani.weeklydrawapplication.customerinfo.models.CustomerInfoModel;
import com.sohilladhani.weeklydrawapplication.util.datetime.LocalWeek;
import com.sohilladhani.weeklydrawapplication.weeklydraw.models.WeeklyDrawModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class WeeklyDrawController implements Initializable {

    @FXML
    private Label weeklyWinnerName;
    @FXML
    private Label weeklyWinnerAddress;
    @FXML
    private Label weeklyWinnerPhone;
    @FXML
    private Label weeklyWinnerEmail;
    @FXML
    private Label weeklyWinnerWeek;

    private CustomerInfoModel customerInfoModel;
    private Stage weeklyDrawWinnerStage;
    private WeeklyDrawModel weeklyDrawModel;
    private Customer currentWeekWinner;
    private Alert alertBox;

    public WeeklyDrawController(CustomerInfoModel customerInfoModel,
                                WeeklyDrawModel weeklyDrawModel, Stage stage) {
        this.customerInfoModel = customerInfoModel;
        this.weeklyDrawModel = weeklyDrawModel;
        this.weeklyDrawWinnerStage = stage;
        this.weeklyDrawWinnerStage.setOnCloseRequest(windowEvent -> {
            this.customerInfoModel.close();
            this.weeklyDrawModel.close();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Customer> allCustomers = customerInfoModel.findAllCustomers();

        List<Customer> currentWeekCustomers = getCurrentWeekCustomers(allCustomers);

        if (noCustomerPresentForTheCurrentWeek(currentWeekCustomers)) {
            alertBox = new Alert(Alert.AlertType.WARNING, "No customer for this week is present.");
            alertBox.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alertBox.showAndWait();
            showNoWinners();
        }

        if (isCurrentWeekWinnerAlreadyDeclared()) {
            alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Winner for the week is already " +
                    "declared. Want to declare again?", ButtonType.YES, ButtonType.NO);
            alertBox.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alertBox.showAndWait();
            if (alertBox.getResult() == ButtonType.YES) {
                deleteCurrentWinner();
                declareNewCurrentWinner(currentWeekCustomers);
            }
        } else {
            declareNewCurrentWinner(currentWeekCustomers);
        }

        showCurrentWeekWinner();
    }

    private void showNoWinners() {
        String notAvailable = "N/A";
        weeklyWinnerName.setText(notAvailable);
        weeklyWinnerAddress.setText(notAvailable);
        weeklyWinnerPhone.setText(notAvailable);
        weeklyWinnerEmail.setText(notAvailable);
        weeklyWinnerWeek.setText(notAvailable);
    }

    private void declareNewCurrentWinner(List<Customer> currentWeekCustomers) {
        if (getRandomIndex(currentWeekCustomers) != -1) {
            currentWeekWinner = currentWeekCustomers.get(getRandomIndex(currentWeekCustomers));
            weeklyDrawModel.addWeeklyDrawWinner(currentWeekWinner);
        }
    }

    private int getRandomIndex(List<Customer> currentWeekCustomers) {
        Random random = new Random();
        if (currentWeekCustomers.size() != 0) {
            return random.nextInt(currentWeekCustomers.size());
        }
        return -1;
    }

    private void deleteCurrentWinner() {
        weeklyDrawModel.deleteCurrentWeeklyDrawWinner();
    }

    private void showCurrentWeekWinner() {
        currentWeekWinner = weeklyDrawModel.getCurrentWeeklyDrawWinner();
        if (currentWeekWinner != null) {
            weeklyWinnerName.setText(currentWeekWinner.getName());
            weeklyWinnerAddress.setText(currentWeekWinner.getAddress());
            weeklyWinnerPhone.setText(Long.toString(currentWeekWinner.getPhone()));
            weeklyWinnerEmail.setText(currentWeekWinner.getEmail());
            weeklyWinnerWeek.setText(currentWeekWinner.getWeek());
        } else {
            showNoWinners();
        }

    }

    private boolean noCustomerPresentForTheCurrentWeek(List<Customer> currentWeekCustomers) {
        return currentWeekCustomers.size() == 0;
    }

    private boolean isCurrentWeekWinnerAlreadyDeclared() {
        return weeklyDrawModel.getCurrentWeeklyDrawWinner() != null;
    }

    private List<Customer> getCurrentWeekCustomers(List<Customer> allCustomers) {
        List<Customer> currentWeekCustomers = new ArrayList<>();
        for (Customer customer : allCustomers) {
            if (customer.getWeek().equals(LocalWeek.getCurrentWeek())) {
                currentWeekCustomers.add(customer);
            }
        }
        return currentWeekCustomers;
    }

    public WeeklyDrawController() {
    }
}
