package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Visits {
    private SimpleIntegerProperty id_visit;
    private SimpleIntegerProperty id_employee;
    private SimpleIntegerProperty id_dog;
    private  SimpleStringProperty date_of_visit;
    private  SimpleStringProperty coming_time;
    private  SimpleStringProperty leaving_time;
    private SimpleIntegerProperty walking_time;
    private SimpleIntegerProperty id_service;
    private  SimpleStringProperty incident;
    public Visits(int id_visit, int id_employee, int id_dog, String date_of_visit, String coming_time, String leaving_time, int walking_time, int id_service, String incident) {
        this.id_visit = new SimpleIntegerProperty(id_visit);
        this.id_employee = new SimpleIntegerProperty(id_employee);
        this.id_dog = new SimpleIntegerProperty(id_dog);
        this.date_of_visit = new SimpleStringProperty(date_of_visit);
        this.coming_time= new SimpleStringProperty(coming_time);
        this.leaving_time = new SimpleStringProperty(leaving_time);
        this.walking_time = new SimpleIntegerProperty(walking_time);
        this.id_service = new SimpleIntegerProperty(id_service);
        this.incident = new SimpleStringProperty(incident);
    }

    public int getId_visit() {
        return id_visit.get();
    }

    public void setId_visit(int id_owner1) {
        id_visit.set(id_owner1);
    }

    public int getId_employee() {
        return id_employee.get();
    }

    public void setId_employee(int id_owner1) {
        id_employee.set(id_owner1);
    }

    public int getId_dog() {
        return id_dog.get();
    }

    public void setId_dog(int val_name) {
        id_dog.set(val_name);
    }

    public String getDate_of_visit() {
        return date_of_visit.get();
    }

    public void setDate_of_visit(String val_spicies) {
        date_of_visit.set(val_spicies);
    }

    public String getComing_time() {
        return coming_time.get();
    }

    public void setComing_time(String value) {
        coming_time.set(value);
    }

    public String getLeaving_time() {
        return leaving_time.get();
    }

    public void setLeaving_time(String value) {
        leaving_time.set(value);
    }

    public int getWalking_time() {
        return walking_time.get();
    }

    public void setWalking_time(int value) {
        walking_time.set(value);
    }

    public int getId_service() {
        return id_service.get();
    }

    public void setId_service(int id_owner1) {
        id_service.set(id_owner1);
    }


    public String getIncident() {
        return incident.get();
    }

    public void setIncident(String value) {
        incident.set(value);
    }


}
