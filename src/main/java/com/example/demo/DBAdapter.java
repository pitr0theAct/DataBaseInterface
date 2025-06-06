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
                    "    date_of_birth  TEXT not null," +
                    "    address        TEXT null  " +
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
                    "    date_of_visit  TEXT not null, " +
                    "    coming_time    TEXT not null, " +
                    "    leaving_time   TEXT not null, " +
                    "    walking_time   integer not null," +
                    "    id_service     integer not null," +
                    "    incident       TEXT not null  " +
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

    /// Услуги ///
    public void insertServices(String service_name) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "INSERT INTO services(service_name) " +
                "VALUES('"+service_name+"')";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertServices': " + e);
        }
        System.out.println("Inserted in Services Table");
    }


    ArrayList<Services> select_dataServices() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        ArrayList<Services> services = new ArrayList<>();

        String sql = "SELECT *  FROM services";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id_service = rs.getInt("id_service");
            String service_name = rs.getString("service_name");
            services.add(new Services(id_service,service_name));
        }
        return services;
    }

    void delete_dataServices(Integer id) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "DELETE FROM services WHERE id_service='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }


    void  update_dataServices(Integer id_service,String service_name) throws SQLException {
        String sql = "UPDATE services SET  service_name='"+service_name+"'  WHERE id_service='"+id_service+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Updated data");
    }

    /// Работники ///
    public void insertEmployees(String surname, String name, String middlename, String date_of_birth, String address) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "INSERT INTO employees (surname, name, middlename, date_of_birth, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, surname);
            pstmt.setString(2, name);
            pstmt.setString(3, middlename);
            pstmt.setString(4, date_of_birth);
            pstmt.setString(5, address);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in 'insertOwners': " + e.getMessage());
        }
    }

    ArrayList<Employees> select_dataEmployees() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        ArrayList<Employees> employees = new ArrayList<>();

        String sql = "SELECT *  FROM employees";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id_employees = rs.getInt("id_employee");
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            String middle_name = rs.getString("middlename");
            String date_of_birth = rs.getString("date_of_birth");
            String address = rs.getString("address");
            employees.add(new Employees(id_employees,surname, name, middle_name, date_of_birth, address));
        }
        return employees;
    }

    void delete_dataEmployees(Integer id) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "DELETE FROM employees WHERE id_employee='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }


    void  update_dataEmployees(Integer id_employee,String surname, String name, String middle_name, String date_of_birth, String address) throws SQLException {
        String sql = "UPDATE employees SET  surname ='"+surname+"', name='"+name+"' , middlename ='"+middle_name+"' , " +
                "date_of_birth='"+date_of_birth+"' , address ='"+address+"'  WHERE id_employee='"+id_employee+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Updated data");
    }



    /// Посещения ///
    public void insertVisits(Integer id_employee, Integer id_dog, String date_of_visit, String coming_time, String leaving_time, Integer walking_time, Integer id_service, String incident) {
        String sql = "insert into visits (id_employee, id_dog, date_of_visit, coming_time, leaving_time, walking_time, id_service, incident) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id_employee);
            pstmt.setInt(2, id_dog);
            pstmt.setString(3, date_of_visit);
            pstmt.setString(4, coming_time);
            pstmt.setString(5, leaving_time);
            pstmt.setInt(6, walking_time);
            pstmt.setInt(7, id_service);
            pstmt.setString(8, incident);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in 'insertVisits': " + e.getMessage());
        }
    }


    ArrayList<Visits> select_dataVisits() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        ArrayList<Visits> visits = new ArrayList<>();

        String sql = "SELECT *  FROM visits";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id_visits = rs.getInt("id_visit");
            int id_employee = rs.getInt("id_employee");
            int id_dog = rs.getInt("id_dog");
            String date_of_visit = rs.getString("date_of_visit");
            String coming_time = rs.getString("coming_time");
            String leaving_time = rs.getString("leaving_time");
            int walking_time = rs.getInt("walking_time");
            int id_service = rs.getInt("id_service");
            String incident = rs.getString("incident");
            visits.add(new Visits(id_visits,id_employee,id_dog, date_of_visit, coming_time, leaving_time, walking_time, id_service, incident));
        }
        return visits;
    }

    void delete_dataVisits(Integer id) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:dogWalkers.sqlite");
        String sql = "DELETE FROM visits WHERE id_visit='"+id+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Deleted data");
    }


    void  update_dataVisits(Integer id_visit,Integer id_employee, Integer id_dog, String date_of_visit, String coming_time, String leaving_time, Integer walking_time, Integer id_service, String incident) throws SQLException {
        String sql = "UPDATE visits SET  id_employee ='"+id_employee+"', id_dog='"+id_dog+"' , date_of_visit ='"+date_of_visit+"' , " +
                "coming_time='"+coming_time+"' , leaving_time ='"+leaving_time+"' " +
                ", walking_time ='"+walking_time+"' , id_service ='"+id_service+"' , incident ='"+incident+"' WHERE id_visit='"+id_visit+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Updated data");
    }


}
