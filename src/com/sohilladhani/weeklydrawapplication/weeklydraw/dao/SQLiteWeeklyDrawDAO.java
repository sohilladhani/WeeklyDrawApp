package com.sohilladhani.weeklydrawapplication.weeklydraw.dao;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SQLiteWeeklyDrawDAO implements WeeklyDrawDAO {

    private Connection connection;
    private SQLiteDataSource sqLiteDataSource;
    private QueryRunner queryRunner;

    @Override
    public void setup() throws Exception {
        String connectionString = "jdbc:sqlite:customerinfo.sqlite";
        sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(connectionString);
        queryRunner = new QueryRunner();
        connection = sqLiteDataSource.getConnection();

        String createTableSQLQuery = "CREATE TABLE IF NOT EXISTS WeeklyDrawWinners (id INTEGER, " +
                "name TEXT, address TEXT, phone BIGINT, email TEXT, week TEXT)";
        queryRunner.update(connection, createTableSQLQuery);
    }

    @Override
    public void connect() throws Exception {
        connection = sqLiteDataSource.getConnection();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public boolean isConnected() throws Exception {
        return !connection.isClosed();
    }

    @Override
    public int insertWeeklyDrawWinner(Customer customer) {
        try {
            return queryRunner.insert(connection, "INSERT INTO WeeklyDrawWinners (id, name, " +
                            "address, phone, email, " +
                            "week) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    new ScalarHandler<>(), customer.getId(), customer.getName(),
                    customer.getAddress(), customer.getPhone(), customer.getEmail(),
                    customer.getWeek());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateWeeklyDrawWinner(Customer customer) {
        try {
            queryRunner.update(connection, "UPDATE WeeklyDrawWinners SET name=?, " +
                            "address=?, phone=?, email=? " +
                            "week=? WHERE id=?", customer.getName(),
                    customer.getAddress(), customer.getAddress(), customer.getPhone(),
                    customer.getEmail(), customer.getWeek(), customer.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteWeeklyDrawWinner(Customer customer) {
        try {
            queryRunner.update(connection, "DELETE FROM WeeklyDrawWinners where ID=?",
                    customer.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer findWeeklyDrawWinnerById(int id) {
        try {
            return queryRunner.query(connection, "SELECT * FROM WeeklyDrawWinners WHERE ID=?",
                    new BeanHandler<>(Customer.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findWeeklyDrawWinnerByWeek(String week) {
        try {
            return queryRunner.query(connection, "SELECT * FROM WeeklyDrawWinners WHERE week=?",
                    new BeanHandler<>(Customer.class), week);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAllWeeklyDrawWinners() {
        try {
            return queryRunner.query(connection, "SELECT * FROM WeeklyDrawWinners",
                    new BeanListHandler<>(Customer.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
