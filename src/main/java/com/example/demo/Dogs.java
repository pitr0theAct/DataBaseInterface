package com.example.demo;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Dogs {
    private SimpleIntegerProperty id_dog;
    private SimpleStringProperty id_owners;
    private  SimpleStringProperty dog_name;
    private  SimpleStringProperty species;
    public Dogs(int id_dog, String id_owners, String dog_name, String species) {
        this.id_dog = new SimpleIntegerProperty(id_dog);
        this.id_owners = new SimpleStringProperty(id_owners);
        this.dog_name = new SimpleStringProperty(dog_name);
        this.species = new SimpleStringProperty(species);
    }
    public int getId_dog() {
        return id_dog.get();
    }

    public void setId_dog(int id_dog1) {
        id_dog.set(id_dog1);
    }

    public String getDog_name() {
        return dog_name.get();
    }

    public void setDog_name(String val_name) {
        dog_name.set(val_name);
    }

    public String getSpecies() {
        return species.get();
    }

    public void setSpecies(String val_spicies) {
        species.set(val_spicies);
    }

    public String getId_owners() {
        return id_owners.get();
    }

    public void setId_owners(String value) {
        id_owners.set(value);
    }


}
