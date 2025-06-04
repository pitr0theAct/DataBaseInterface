package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OwnersController implements Initializable {
    DBAdapter adapter = new DBAdapter();

    @FXML
    private TableView<Owners> tableOwners;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_middlename;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_surname;

    @FXML
    void onAddButtonClick(ActionEvent event) throws IOException, SQLException {
        String surname = txt_surname.getText();
        String name = txt_name.getText();
        String middlename = txt_middlename.getText();
        String address = txt_address.getText();


        if (surname.isEmpty() || name.isEmpty() || middlename.isEmpty() || address.isEmpty()) {

        }else {
            adapter.insertOwners(surname, name, middlename, address);
        }
        updateTableOwners();
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        txt_surname.getScene().getWindow().hide();
    }

    @FXML
    public void onDeleteButtonClick() throws IOException, SQLException {
        Owners owners = tableOwners.getSelectionModel().getSelectedItem();
        adapter.delete_dataOwners(owners.getId_owner());
        updateTableOwners();
    }

    @FXML
    public void onUpdateButtonClick() throws IOException, SQLException {
        Owners owners = tableOwners.getSelectionModel().getSelectedItem();
        System.out.println(owners.getId_owner());
        String surname = txt_surname.getText();
        String name = txt_name.getText();
        String middlename = txt_middlename.getText();
        String address = txt_address.getText();
        adapter.update_dataOwners(owners.getId_owner(), surname, name, middlename, address);
        updateTableOwners();
    }

    private void updateTableOwners() throws IOException, SQLException {

        ArrayList<Owners> data = adapter.select_dataOwners();

        TableColumn<Owners, Integer> id_owner = new TableColumn<>("id_owner");
        id_owner.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
        TableColumn<Owners, String> surname = new TableColumn<>("surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn<Owners, String> name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Owners, String> middlename = new TableColumn<>("middlename");
        middlename.setCellValueFactory(new PropertyValueFactory<>("middlename"));
        TableColumn<Owners, String> address = new TableColumn<>("address");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));


        tableOwners.getColumns().addAll(id_owner,surname,name,middlename,address);

        ObservableList<Owners> data_new = FXCollections.observableArrayList(data);
        tableOwners.setItems(data_new);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adapter.connect();
        try {
            updateTableOwners();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}


