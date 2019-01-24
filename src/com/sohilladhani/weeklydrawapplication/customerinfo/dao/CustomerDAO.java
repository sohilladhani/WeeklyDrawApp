package com.sohilladhani.weeklydrawapplication.customerinfo.dao;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import com.sohilladhani.weeklydrawapplication.util.db.DAO;

import java.util.List;

public interface CustomerDAO extends DAO {
    int insertCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);

    Customer findCustomerById(int id);
    List<Customer> findAllCustomers();
}
