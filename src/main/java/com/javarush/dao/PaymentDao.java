package com.javarush.dao;

import com.javarush.entity.Payment;

public class PaymentDao extends AbstractDao<Payment> {

    public PaymentDao() {
        super(Payment.class);
    }
}
