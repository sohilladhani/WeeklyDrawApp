package com.sohilladhani.weeklydrawapplication.weeklydraw.winners.controllers;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import com.sohilladhani.weeklydrawapplication.weeklydraw.models.WeeklyDrawModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WeeklyWinnersController implements Initializable {

    @FXML
    private TableView<Customer> weeklyWinnersTableView;
    @FXML
    private TableColumn<Customer, Integer> weeklyWinnerIdColumn;
    @FXML
    private TableColumn<Customer, String> weeklyWinnerNameColumn;
    @FXML
    private TableColumn<Customer, String> weeklyWinnerAddressColumn;
    @FXML
    private TableColumn<Customer, BigDecimal> weeklyWinnerPhoneColumn;
    @FXML
    private TableColumn<Customer, String> weeklyWinnerEmailColumn;
    @FXML
    private TableColumn<Customer, String> weeklyWinnerWeekColumn;


    private WeeklyDrawModel weeklyDrawModel;
    private Stage weeklyWinnersStage;

    public WeeklyWinnersController(WeeklyDrawModel weeklyDrawModel, Stage weeklyWinnersStage) {
        this.weeklyDrawModel = weeklyDrawModel;
        this.weeklyWinnersStage = weeklyWinnersStage;
        this.weeklyWinnersStage.setOnCloseRequest(t -> weeklyDrawModel.close());
    }

    public WeeklyWinnersController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadWeeklyWinnersDataInTableView();
    }

    private void loadWeeklyWinnersDataInTableView() {
        List<Customer> allWeeklyWinners = weeklyDrawModel.findAllWeeklyDrawWinners();
        ObservableList<Customer> customerObservableList =
                FXCollections.observableList(allWeeklyWinners);

        weeklyWinnerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        weeklyWinnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weeklyWinnerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        weeklyWinnerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        weeklyWinnerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        weeklyWinnerWeekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));

        weeklyWinnersTableView.getColumns().setAll(weeklyWinnerIdColumn, weeklyWinnerNameColumn,
                weeklyWinnerAddressColumn, weeklyWinnerPhoneColumn, weeklyWinnerEmailColumn, weeklyWinnerWeekColumn);
        weeklyWinnersTableView.setItems(customerObservableList);
    }
}
