package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class DogsController {

    @FXML
    private TableView<?> tableDogs;

    @FXML
    private TextField txt_dogname;

    @FXML
    private TextField txt_ownerID;

    @FXML
    private TextField txt_species;

    @FXML
    void onAddButtonClick(ActionEvent event) {

    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        txt_dogname.getScene().getWindow().hide();

    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {

    }


}

