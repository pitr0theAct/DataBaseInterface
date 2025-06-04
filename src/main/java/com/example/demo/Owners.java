package com.example.demo;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Owners {
    private SimpleIntegerProperty id_owner;
    private SimpleStringProperty surname;
    private  SimpleStringProperty name;
    private  SimpleStringProperty middlename;
    private  SimpleStringProperty address;
    public Owners(int id_owner, String surname, String name, String middlename, String address) {
        this.id_owner = new SimpleIntegerProperty(id_owner);
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.middlename = new SimpleStringProperty(middlename);
        this.address = new SimpleStringProperty(address);
    }
    public int getId_owner() {
        return id_owner.get();
    }

    public void setId(int id_owner1) {
        id_owner.set(id_owner1);
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

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }


}