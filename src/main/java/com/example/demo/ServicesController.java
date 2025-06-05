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
import static com.example.demo.Functions.isNumeric;

public class ServicesController implements Initializable {
    DBAdapter adapter = new DBAdapter();

    @FXML
    private TableView<Services> tableServices;

    @FXML
    private TextField txt_service_name;

    @FXML
    void onAddButtonClick(ActionEvent event)  throws IOException, SQLException {
        String service_name = txt_service_name.getText();


        if (service_name.isEmpty() ) {

        }else if (!isNumeric(service_name)){
            adapter.insertServices(service_name);
        }
        updateTable();
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        txt_service_name.getScene().getWindow().hide();
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) throws IOException, SQLException {
        Services services = tableServices.getSelectionModel().getSelectedItem();
        adapter.delete_dataServices(services.getId_service());
        updateTable();
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) throws IOException, SQLException {
        Services services = tableServices.getSelectionModel().getSelectedItem();
        System.out.println(services.getId_service());
        String service_name = txt_service_name.getText();
        if (service_name.isEmpty() ) {

        }else if (!isNumeric(service_name)){
            adapter.update_dataServices(services.getId_service(), service_name);
            updateTable();
        }
    }

    private void updateTable() throws IOException, SQLException {

        ArrayList<Services> data = adapter.select_dataServices();

        TableColumn<Services, Integer> id_service = new TableColumn<>("id_service");
        id_service.setCellValueFactory(new PropertyValueFactory<>("id_service"));
        TableColumn<Services, String> service_name = new TableColumn<>("service_name");
        service_name.setCellValueFactory(new PropertyValueFactory<>("service_name"));



        tableServices.getColumns().addAll(id_service,service_name);

        ObservableList<Services> data_new = FXCollections.observableArrayList(data);
        tableServices.setItems(data_new);

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
