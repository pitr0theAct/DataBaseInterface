package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class DBAdapter {
    private Connection con;

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");

            Statement stmt = con.createStatement();
            String sql = "create table if not exists dogs" +
                    "(" +
                    "    id_dog       integer primary key autoincrement," +
                    "    id_owners    TEXT not null," +
                    "    dog_name     TEXT not null, " +
                    "    species      TEXT not null" +
                    ");" +
                    "create table if not exists owners" +
                    "(" +
                    "    id_owner   integer primary key autoincrement," +
                    "    surname    TEXT not null," +
                    "    name       TEXT not null, " +
                    "    middlename TEXT null, " +
                    "    address    TEXT not null" +
                    ");" +
                    "create table if not exists employees" +
                    "(" +
                    "    id_employee    integer primary key autoincrement," +
                    "    surname        TEXT not null," +
                    "    name           TEXT not null, " +
                    "    middlename     TEXT null, " +
                    "    date_of_birth  date not null," +
                    "    address        binary(1) null  " +//Навыки
                    ");" +
                    "create table if not exists services" +
                    "(" +
                    "    id_service     integer primary key autoincrement," +
                    "    service_name   TEXT" +
                    ");" +
                    "create table if not exists visits" +
                    "(" +
                    "    id_visit       integer primary key autoincrement," +
                    "    id_employee    integer not null," +
                    "    id_dog         integer not null," +
                    "    date_of_visit  date not null, " +
                    "    coming_time    time not null, " +
                    "    leaving_time   time not null, " +
                    "    walking_time   integer not null," +
                    "    id_service     integer not null," +
                    "    incident       text null  " +
                    ");";
            stmt.execute(sql);
            System.out.println("Tables created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertDogs(String id_owners, String dog_name, String species) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "INSERT INTO dogs(id_owners, dog_name, species) " +
                "VALUES('"+id_owners+"','"+dog_name+"','"+species+"')";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertDogs': " + e);
        }
        System.out.println("Inserted in Dogs Table");
    }


    ArrayList<Dogs> select_dataDogs() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        ArrayList<Dogs> dogs = new ArrayList<>();

        String sql = "SELECT *  FROM dogs";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id_dog = rs.getInt("id_dog");
            String id_owners = rs.getString("id_owners");
            String dog_name = rs.getString("dog_name");
            String species = rs.getString("species");
            dogs.add(new Dogs(id_dog,id_owners,dog_name,species));
        }
        return dogs;
    }

    void delete_dataDogs(Integer id) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "DELETE FROM dogs WHERE id_dog='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }


    void  update_dataDogs(Integer id_dog,String id_owners, String dog_name,String species) throws SQLException {
        String sql = "UPDATE dogs SET id_owners='"+id_owners+"', dog_name='"+dog_name+"' , species='"+species+"' WHERE id_dog='"+id_dog+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Updated data");
    }

/*
    public void insertOwners(String surname, String name, String middlename, String address) {
        String sql = "insert into owners (surname, name, middlename, address)" +
                "values (" + surname + ", " + name + ", " + middlename + ", " + address + ")";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertOwners': " + e);
        }
        System.out.println("Inserted in Owners Table");
    }
    public void insertEmployees(String surname, String name, String middlename, java.sql.Date date_of_birth, Boolean address) {
        String sql = "insert into employees (surname, name, middlename, date_of_birth, address)" +
                "values (" + surname + ", " + name + ", " + middlename + ", " + date_of_birth + "," + address + ")";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertEmployees': " + e);
        }
        System.out.println("Inserted in Owners Table");
    }

    public void insertServices(String service_name) {
        String sql = "insert into services (service_name)" +
                "values (" + service_name + ")";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertServices': " + e);
        }
        System.out.println("Inserted in Services Table");
    }

    public void insertVisits(Integer id_employee, Integer id_dog, java.sql.Date date_of_visit, java.sql.Time coming_time, java.sql.Time leaving_time, Integer walking_time, Integer id_service, String incident) {
        String sql = "insert into visits (id_employee, id_dog, date_of_visit, coming_time, leaving_time, walking_time, id_service, incident)" +
                "values (" + id_employee + ", " + id_dog + ", " + date_of_visit + ", " + coming_time + "," + leaving_time + "," + walking_time + "," + id_service + "," +  incident + ")";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertVisits': " + e);
        }
        System.out.println("Inserted in Visits Table");
    }
*/


}
