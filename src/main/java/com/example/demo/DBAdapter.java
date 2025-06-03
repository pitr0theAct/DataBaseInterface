package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdapter {
    private Connection con;

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:dog-walkers.sqlite");

            Statement stmt = con.createStatement();
            String sql = "create table if not exists dogs" +
                    "(" +
                    "    id_dog       integer primary key autoincrement," +
                    "    id_owners    integer not null," +
                    "    dog_name     varchar(255) not null, " +
                    "    species      varchar(255) not null" +
                    ");" +
                    "create table if not exists owners" +
                    "(" +
                    "    id_owner   integer primary key autoincrement," +
                    "    surname    varchar(255) not null," +
                    "    name       varchar(255) not null, " +
                    "    middlename varchar(255) null, " +
                    "    address    varchar(255) not null" +
                    ");" +
                    "create table if not exists employees" +
                    "(" +
                    "    id_employee    integer primary key autoincrement," +
                    "    surname        varchar(255) not null," +
                    "    name           varchar(255) not null, " +
                    "    middlename     varchar(255) null, " +
                    "    date_of_birth  date not null," +
                    "    address        binary(1) null  " +//Навыки
                    ");" +
                    "create table if not exists services" +
                    "(" +
                    "    id_service     integer primary key autoincrement," +
                    "    service_name   varchar(255)" +
                    ");" +
                    "create table if not exists visits" +
                    "(" +
                    "    id_visit       integer primary key autoincrement," +
                    "    id_employee    integer not null," +
                    "    id_dog         integer not null," +
                    "    date_of_visit  date not null, " +
                    "    coming_time    time not null, " +
                    "    leaving_time   time not null, " +
                    "    walking_time   time not null," +
                    "    id_service     integer not null," +
                    "    incident       text null  " +
                    ");";
            stmt.execute(sql);
            System.out.println("Tables created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
/*
    public void insertFirst(Integer firstNumber, Integer secondNumber) {
        String sql = "insert into firstTable (firstNumber, secondNumber)" +
                "values (" + firstNumber + ", " + secondNumber + ")";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertFirst': " + e);
        }
        System.out.println("Inserted in firstTable");
    }

    public void insertSecond(Integer checksCount, Integer value) {
        String sql = "insert into secondTable (checksCount, value)" +
                "values (" + checksCount + ", " + value + ")";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("error in 'insertFirst': " + e);
        }
        System.out.println("Inserted in secondTable");
    }
}
*/
}