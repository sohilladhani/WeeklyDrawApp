package com.sohilladhani.weeklydrawapplication.weeklydraw.models;

import com.sohilladhani.weeklydrawapplication.customerinfo.entities.Customer;
import com.sohilladhani.weeklydrawapplication.util.datetime.LocalWeek;
import com.sohilladhani.weeklydrawapplication.weeklydraw.dao.WeeklyDrawDAO;

import java.util.List;

public class WeeklyDrawModel {

    private WeeklyDrawDAO weeklyDrawDAO;

    public WeeklyDrawModel(WeeklyDrawDAO weeklyDrawDAO) {
        this.weeklyDrawDAO = weeklyDrawDAO;
        setup(weeklyDrawDAO);
        connect(weeklyDrawDAO);
    }

    private void setup(WeeklyDrawDAO weeklyDrawDAO) {
        try {
            weeklyDrawDAO.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect(WeeklyDrawDAO weeklyDrawDAO) {
        try {
            weeklyDrawDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            weeklyDrawDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWeeklyDrawWinner(Customer customer) {
        weeklyDrawDAO.insertWeeklyDrawWinner(customer);
    }

    public void deleteCurrentWeeklyDrawWinner() {
        Customer currentWeeklyDrawWinner = getCurrentWeeklyDrawWinner();
        weeklyDrawDAO.deleteWeeklyDrawWinner(currentWeeklyDrawWinner);
    }

    public Customer getCurrentWeeklyDrawWinner() {
        return weeklyDrawDAO.findWeeklyDrawWinnerByWeek(LocalWeek.getCurrentWeek());
    }

    public List<Customer> findAllWeeklyDrawWinners(){
        return weeklyDrawDAO.findAllWeeklyDrawWinners();
    }
}
