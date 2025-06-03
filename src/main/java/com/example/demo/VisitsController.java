package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VisitsController {

    @FXML
    private TableView<?> tableVisits;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_dogID;

    @FXML
    private TextField txt_incident;

    @FXML
    private TextField txt_serviceID;

    @FXML
    private TextField txt_time1;

    @FXML
    private TextField txt_time12;

    @FXML
    private TextField txt_time2;

    @FXML
    private TextField txt_workerID;

    @FXML
    void onAddButtonClick(ActionEvent event) {

    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        tableVisits.getScene().getWindow().hide();

    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {

    }

}

