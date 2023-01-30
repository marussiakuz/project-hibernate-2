package com.javarush.service;

import lombok.extern.slf4j.Slf4j;
import com.javarush.dao.PaymentDao;
import com.javarush.entity.Payment;

@Slf4j
public class PaymentService {
    private final PaymentDao paymentDao;

    public PaymentService() {
        this.paymentDao = new PaymentDao();
    }

    public Payment savePayment(Payment payment) {
        payment.getCustomer().setStore(payment.getRental().getInventory().getStore());

        Payment saved = paymentDao.save(payment);

        log.info("customer with id={} rented inventory id={}. Rental created: {}, payment created: {}",
                saved.getCustomer().getId(), saved.getRental().getInventory().getId(), saved.getRental(), saved);
        log.debug("now customer with id={} has updated store id={}", saved.getCustomer().getId(),
                saved.getCustomer().getStore().getId());

        return saved;
    }
}
