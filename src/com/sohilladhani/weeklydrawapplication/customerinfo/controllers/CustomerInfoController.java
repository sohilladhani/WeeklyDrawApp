package com.sohilladhani.weeklydrawapplication.customerinfo.controllers;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import com.sohilladhani.weeklydrawapplication.customerinfo.models.CustomerInfoModel;
import com.sohilladhani.weeklydrawapplication.util.datetime.LocalWeek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerInfoController implements Initializable {

    @FXML
    private TextField customerName;
    @FXML
    private TextField customerAddress;
    @FXML
    private TextField customerPhone;
    @FXML
    private TextField customerEmail;
    @FXML
    private TextField customerWeek;
    @FXML
    private TableView<Customer> customerInfoModelTableView;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> customerAddressColumn;
    @FXML
    private TableColumn<Customer, BigDecimal> customerPhoneColumn;
    @FXML
    private TableColumn<Customer, String> customerEmailColumn;
    @FXML
    private TableColumn<Customer, String> customerWeekColumn;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button clearFormButton;
    @FXML
    private Button loadDataButton;

    private CustomerInfoModel customerInfoModel;
    private Stage customerInfoStage;

    public CustomerInfoController(CustomerInfoModel customerInfoModel, Stage customerInfoStage) {
        this.customerInfoModel = customerInfoModel;
        this.customerInfoStage = customerInfoStage;
        this.customerInfoStage.setOnCloseRequest(e -> customerInfoModel.close());
    }

    public CustomerInfoController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomerDataInTableView();
        customerWeek.setDisable(true);
        customerWeek.setText(LocalWeek.getCurrentWeek());
    }

    public void onAddCustomerButtonAction(ActionEvent actionEvent) {
        String name = customerName.getText();
        String address = customerAddress.getText();
        long phone = Long.parseLong(customerPhone.getText());
        String email = customerEmail.getText();
        String week = customerWeek.getText();

        this.customerInfoModel.addCustomer(name, address, phone, email, week);
        loadCustomerDataInTableView();
    }

    public void onClearFormButtonAction(ActionEvent actionEvent) {
        customerName.clear();
        customerAddress.clear();
        customerPhone.clear();
        customerEmail.clear();
        customerWeek.clear();
    }

    public void onLoadDataButtonAction(ActionEvent actionEvent) {
        loadCustomerDataInTableView();
    }

    private void loadCustomerDataInTableView() {
        List<Customer> listOfAllCustomers = customerInfoModel.findAllCustomers();
        ObservableList<Customer> customerObservableList =
                FXCollections.observableList(listOfAllCustomers);

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        customerWeekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));

        customerInfoModelTableView.getColumns().setAll(customerIdColumn, customerNameColumn,
                customerAddressColumn, customerPhoneColumn, customerEmailColumn, customerWeekColumn);
        customerInfoModelTableView.setItems(customerObservableList);
    }
}
