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

public class OwnersController {

    @FXML
    private TableView<?> tableOwners;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_middlename;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_surname;

    @FXML
    void onAddButtonClick(ActionEvent event) {

    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        txt_surname.getScene().getWindow().hide();
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {

    }

}


