package com.sohilladhani.weeklydrawapplication.weeklydraw.dao;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import com.sohilladhani.weeklydrawapplication.util.db.DAO;

import java.util.List;

public interface WeeklyDrawDAO extends DAO {
    int insertWeeklyDrawWinner(Customer customer);
    boolean updateWeeklyDrawWinner(Customer customer);
    boolean deleteWeeklyDrawWinner(Customer customer);

    Customer findWeeklyDrawWinnerById(int id);
    Customer findWeeklyDrawWinnerByWeek(String week);
    List<Customer> findAllWeeklyDrawWinners();
}
