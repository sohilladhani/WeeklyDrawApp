package com.sohilladhani.weeklydrawapplication.customerinfo.dao;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SQLiteCustomerDAO implements CustomerDAO {

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

        String createTableSQLQuery = "CREATE TABLE IF NOT EXISTS Customers (id INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, name TEXT, address TEXT, phone BIGINT, email TEXT, week TEXT)";
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
    public int insertCustomer(Customer customer) {
        try {
            return queryRunner.insert(connection, "INSERT INTO Customers (name, address, phone, " +
                            "email, week) VALUES (?, ?, ?, ?, ?)", new ScalarHandler<>(),
                    customer.getName(), customer.getAddress(), customer.getPhone(),
                    customer.getEmail(), customer.getWeek());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            queryRunner.update(connection, "UPDATE Customers SET name=?, address=?, phone=?, email=? " +
                            "week=? WHERE ID=?", customer.getName(), customer.getAddress(), customer.getPhone(),
                    customer.getEmail(), customer.getWeek(), customer.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        try {
            queryRunner.update(connection, "DELETE FROM Customers WHERE ID=?", customer.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer findCustomerById(int id) {
        try {
            return queryRunner.query(connection, "SELECT * FROM CUSTOMERS WHERE ID=?",
                    new BeanHandler<>(Customer.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() {
        try {
            return queryRunner.query(connection, "SELECT * FROM CUSTOMERS",
                    new BeanListHandler<>(Customer.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
