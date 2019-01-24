package com.sohilladhani.weeklydrawapplication.customerinfo.models;

import com.sohilladhani.weeklydrawapplication.customerinfo.dao.CustomerDAO;
import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;

import java.util.List;

public class CustomerInfoModel {

    private CustomerDAO customerDAO;

    public CustomerInfoModel(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
        setup(customerDAO);
        connect(customerDAO);
    }

    private void setup(CustomerDAO customerDAO) {
        try {
            customerDAO.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect(CustomerDAO customerDAO) {
        try {
            customerDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            customerDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(String name, String address, long phone, String email, String week) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setWeek(week);

        customerDAO.insertCustomer(customer);
    }

    public void deleteCustomer(int id) {
        Customer customer = customerDAO.findCustomerById(id);
        if(customer == null) {
            System.out.println("Delete customer: Customer not found");
        } else {
            customerDAO.deleteCustomer(customer);
        }
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerDAO.findAllCustomers();
    }
}
