package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class DogsController {
    DBAdapter adapter = new DBAdapter();

    @FXML
    private TableView<Dogs> tableDogs;

    @FXML
    private TextField txt_dogname;

    @FXML
    private TextField txt_ownerID;

    @FXML
    private TextField txt_species;

    @FXML
    void onAddButtonClick(ActionEvent event) throws IOException, SQLException {
        String dog_name = txt_dogname.getText();
        String id_owners = txt_ownerID.getText();
        String species = txt_species.getText();


        if (dog_name.isEmpty() || id_owners.isEmpty() || species.isEmpty()) {

        }else {
            adapter.insertDogs(id_owners, dog_name, species);
        }
        updateTable();
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

    private void updateTable() throws IOException, SQLException {

        ArrayList<Dogs> data = adapter.select_dataDogs();

        TableColumn<Dogs, Integer> id_dog = new TableColumn<>("id_dog");
        id_dog.setCellValueFactory(new PropertyValueFactory<>("id_dog"));
        TableColumn<Dogs, String> id_owners = new TableColumn<>("id_owners");
        id_owners.setCellValueFactory(new PropertyValueFactory<>("id_owners"));
        TableColumn<Dogs, String> dog_name = new TableColumn<>("dog_name");
        dog_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
        TableColumn<Dogs, String> species = new TableColumn<>("species");
        species.setCellValueFactory(new PropertyValueFactory<>("species"));


        tableDogs.getColumns().addAll(id_dog,id_owners,dog_name,species);

        ObservableList<Dogs> data_new = FXCollections.observableArrayList(data);
        tableDogs.setItems(data_new);

    }




}

