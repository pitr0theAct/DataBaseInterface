package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class DBAdapter {
    private Connection con;

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");

            Statement stmt = con.createStatement();
            String dogs = "create table if not exists dogs" +
                    "(" +
                    "    id_dog       integer primary key autoincrement," +
                    "    id_owners    TEXT not null," +
                    "    dog_name     TEXT not null, " +
                    "    species      TEXT not null" +
                    ");";
            String owners = "create table if not exists owners" +
                    "(" +
                    "    id_owner   integer primary key autoincrement," +
                    "    surname    TEXT not null," +
                    "    name       TEXT not null, " +
                    "    middlename TEXT  null, " +
                    "    address    TEXT not null" +
                    ");";
            String employees = "create table if not exists employees" +
                    "(" +
                    "    id_employee    integer primary key autoincrement," +
                    "    surname        TEXT not null," +
                    "    name           TEXT not null, " +
                    "    middlename     TEXT null, " +
                    "    date_of_birth  date not null," +
                    "    address        binary(1) null  " +
                    ");";
            String services = "create table if not exists services" +
                    "(" +
                    "    id_service     integer primary key autoincrement," +
                    "    service_name   TEXT not null " +
                    ");";
            String visits = "create table if not exists visits" +
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
            stmt.execute(dogs);
            stmt.execute(owners);
            stmt.execute(employees);
            stmt.execute(services);
            stmt.execute(visits);
            System.out.println("Tables created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /// Собаки ///
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



    /// Хозяева ///
    /*public void insertOwners(String surname, String name, String middlename, String address) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "INSERT INTO owners(surname, name, middlename, address) " +
                "VALUES('"+surname+"','"+name+"','"+middlename+",'"+address+"')";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertOwners': " + e);
        }
        System.out.println("Inserted in Owners Table");
    }*/

    public void insertOwners(String surname, String name, String middlename, String address) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "INSERT INTO owners (surname, name, middlename, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, middlename);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in 'insertOwners': " + e.getMessage());
        }
    }


    ArrayList<Owners> select_dataOwners() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        ArrayList<Owners> owners = new ArrayList<Owners>();

        String sql = "SELECT *  FROM owners";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id_owner = rs.getInt("id_owner");
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            String middlename = rs.getString("middlename");
            String address = rs.getString("address");
            owners.add(new Owners(id_owner,surname,name,middlename,address));
        }
        return owners;
    }

    void delete_dataOwners(Integer id) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "DELETE FROM owners WHERE id_owner='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }

    void update_dataOwners(Integer id_owner,String surname, String name, String middlename, String address) throws SQLException {
        String sql = "UPDATE owners SET middlename='"+middlename+"', surname='"+surname+"' , name='"+name+"' , address='"+address+"' WHERE id_owner='"+id_owner+"'";
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
