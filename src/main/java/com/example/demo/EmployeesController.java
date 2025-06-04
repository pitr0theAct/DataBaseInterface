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

public class EmployeesController implements Initializable {
    DBAdapter adapter = new DBAdapter();


    @FXML
    private TableView<Employees> tableEmployees;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_birthdate;

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
        String middle_name = txt_middlename.getText();
        String date_of_birth = txt_birthdate.getText();
        String address = txt_address.getText();


        if (surname.isEmpty() || name.isEmpty() || date_of_birth.isEmpty() ) {

        }else {
            adapter.insertEmployees( surname, name, middle_name, date_of_birth, address);
        }
        updateTable();
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        txt_surname.getScene().getWindow().hide();
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) throws IOException, SQLException {
        Employees employees = tableEmployees.getSelectionModel().getSelectedItem();
        adapter.delete_dataEmployees(employees.getId_employee());
        updateTable();
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) throws IOException, SQLException {
        Employees employees = tableEmployees.getSelectionModel().getSelectedItem();
        System.out.println(employees.getId_employee());
        String surname = txt_surname.getText();
        String name = txt_name.getText();
        String middle_name = txt_middlename.getText();
        String date_of_birth = txt_birthdate.getText();
        String address = txt_address.getText();
        adapter.update_dataEmployees(employees.getId_employee(), surname, name, middle_name, date_of_birth,address);
        updateTable();
    }


    private void updateTable() throws IOException, SQLException {

        ArrayList<Employees> data = adapter.select_dataEmployees();

        TableColumn<Employees, Integer> id_employee = new TableColumn<>("id_employee");
        id_employee.setCellValueFactory(new PropertyValueFactory<>("id_employee"));
        TableColumn<Employees, String> surname = new TableColumn<>("surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn<Employees, String> name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Employees, String> middle_name = new TableColumn<>("middlename");
        middle_name.setCellValueFactory(new PropertyValueFactory<>("middlename"));
        TableColumn<Employees, String> date_of_birth = new TableColumn<>("date_of_birth");
        date_of_birth.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        TableColumn<Employees, String> address = new TableColumn<>("skills");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));


        tableEmployees.getColumns().addAll(id_employee,surname,name,middle_name,date_of_birth,address);

        ObservableList<Employees> data_new = FXCollections.observableArrayList(data);
        tableEmployees.setItems(data_new);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adapter.connect();
        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
