package com.javarush.dao;

import com.javarush.entity.Customer;

public class CustomerDao extends AbstractDao<Customer> {

    public CustomerDao() {
        super(Customer.class);
    }
}
