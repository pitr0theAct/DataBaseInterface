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

public class ServicesController {

    @FXML
    private TableView<?> tableServices;

    @FXML
    private TextField txt_service_name;

    @FXML
    void onAddButtonClick(ActionEvent event) {

    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        tableServices.getScene().getWindow().hide();
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {

    }

}
