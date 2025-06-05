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

public class VisitsController implements Initializable {
    DBAdapter adapter = new DBAdapter();

    @FXML
    private TableView<Visits> tableVisits;

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
    void onAddButtonClick(ActionEvent event) throws IOException, SQLException {
        int id_employee = Integer.parseInt(txt_workerID.getText());
        int id_dog = Integer.parseInt(txt_dogID.getText());
        String date_of_visit = txt_date.getText();
        String coming_time = txt_time1.getText();
        String leaving_time = txt_time2.getText();
        int walking_time = Integer.parseInt(txt_time12.getText());
        int id_service = Integer.parseInt(txt_serviceID.getText());
        String incident = txt_incident.getText();


        if (date_of_visit.isEmpty() || txt_workerID.getText().isEmpty() || txt_dogID.getText().isEmpty() || coming_time.isEmpty()
        || leaving_time.isEmpty() || txt_time12.getText().isEmpty() || txt_serviceID.getText().isEmpty() || incident.isEmpty()) {

        }else {
            adapter.insertVisits(id_employee, id_dog, date_of_visit, coming_time,leaving_time, walking_time, id_service, incident);
        }
        updateTable();
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        txt_date.getScene().getWindow().hide();

    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) throws IOException, SQLException {
        Visits visits = tableVisits.getSelectionModel().getSelectedItem();
        adapter.delete_dataVisits(visits.getId_visit());
        updateTable();
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) throws IOException, SQLException {
        Visits visits = tableVisits.getSelectionModel().getSelectedItem();
        System.out.println(visits.getId_visit());
        int id_employee = Integer.parseInt(txt_workerID.getText());
        int id_dog = Integer.parseInt(txt_dogID.getText());
        String date_of_visit = txt_date.getText();
        String coming_time = txt_time1.getText();
        String leaving_time = txt_time2.getText();
        int walking_time = Integer.parseInt(txt_time12.getText());
        int id_service = Integer.parseInt(txt_serviceID.getText());
        String incident = txt_incident.getText();
        adapter.update_dataVisits(visits.getId_visit(), id_employee, id_dog, date_of_visit, coming_time,leaving_time, walking_time, id_service, incident);
        updateTable();
    }

    private void updateTable() throws IOException, SQLException {

        ArrayList<Visits> data = adapter.select_dataVisits();

        TableColumn<Visits, Integer> id_visit = new TableColumn<>("id_visit");
        id_visit.setCellValueFactory(new PropertyValueFactory<>("id_visit"));
        TableColumn<Visits, Integer> id_employee = new TableColumn<>("id_employee");
        id_employee.setCellValueFactory(new PropertyValueFactory<>("id_employee"));
        TableColumn<Visits, Integer> id_dog = new TableColumn<>("id_dog");
        id_dog.setCellValueFactory(new PropertyValueFactory<>("id_dog"));
        TableColumn<Visits, String> date_of_visit = new TableColumn<>("date_of_visit");
        date_of_visit.setCellValueFactory(new PropertyValueFactory<>("date_of_visit"));
        TableColumn<Visits, String> coming_time = new TableColumn<>("coming_time");
        coming_time.setCellValueFactory(new PropertyValueFactory<>("coming_time"));
        TableColumn<Visits, String> leaving_time = new TableColumn<>("leaving_time");
        leaving_time.setCellValueFactory(new PropertyValueFactory<>("leaving_time"));
        TableColumn<Visits, Integer> walking_time = new TableColumn<>("walking_time");
        walking_time.setCellValueFactory(new PropertyValueFactory<>("walking_time"));
        TableColumn<Visits, Integer> id_service = new TableColumn<>("id_service");
        id_service.setCellValueFactory(new PropertyValueFactory<>("id_service"));
        TableColumn<Visits, String> incident = new TableColumn<>("incident");
        incident.setCellValueFactory(new PropertyValueFactory<>("incident"));


        tableVisits.getColumns().addAll(id_visit, id_employee, id_dog, date_of_visit, coming_time, leaving_time, walking_time, id_service, incident);

        ObservableList<Visits> data_new = FXCollections.observableArrayList(data);
        tableVisits.setItems(data_new);

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

