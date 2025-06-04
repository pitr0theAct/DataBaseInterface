package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Employees {
    private SimpleIntegerProperty id_employee;
    private SimpleStringProperty surname;
    private  SimpleStringProperty name;
    private  SimpleStringProperty middlename;
    private  SimpleStringProperty date_of_birth;
    private  SimpleStringProperty address;
    public Employees(int id_employee, String surname, String name, String middlename, String date_of_birth, String address) {
        this.id_employee = new SimpleIntegerProperty(id_employee);
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.middlename = new SimpleStringProperty(middlename);
        this.date_of_birth = new SimpleStringProperty(date_of_birth);
        this.address = new SimpleStringProperty(address);
    }
    public int getId_employee() {
        return id_employee.get();
    }

    public void setId_employee(int id_owner1) {
        id_employee.set(id_owner1);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String val_name) {
        surname.set(val_name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String val_spicies) {
        name.set(val_spicies);
    }

    public String getMiddlename() {
        return middlename.get();
    }

    public void setMiddlename(String value) {
        middlename.set(value);
    }

    public String getDate_of_birth() {
        return date_of_birth.get();
    }

    public void setDate_of_birth(String value) {
        date_of_birth.set(value);
    }


    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }


}
