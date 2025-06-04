package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Services {
    private SimpleIntegerProperty id_service;
    private SimpleStringProperty service_name;
    public Services(int id_service, String service_name) {
        this.id_service = new SimpleIntegerProperty(id_service);
        this.service_name = new SimpleStringProperty(service_name);
    }

    public int getId_service() {
        return id_service.get();
    }

    public void setId_service(int id_serviceIn) {
        id_service.set(id_serviceIn);
    }

    public String getService_name() {
        return service_name.get();
    }

    public void setService_name(String Service_nameIn) {
        service_name.set(Service_nameIn);
    }

}
