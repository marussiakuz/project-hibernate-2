package com.javarush.service;

import lombok.extern.slf4j.Slf4j;
import com.javarush.dao.CityDao;
import com.javarush.dao.CustomerDao;
import com.javarush.dao.StoreDao;
import com.javarush.entity.Customer;
import com.javarush.exception.NotFoundException;

@Slf4j
public class CustomerService {
    private final CustomerDao customerDao;
    private final StoreDao storeDao;
    private final CityDao cityDao;

    public CustomerService() {
        this.storeDao = new StoreDao();
        this.cityDao = new CityDao();
        customerDao = new CustomerDao();
    }

    public Customer saveNewCustomer(Customer customer, short cityId, byte storeId) {
        customer.getAddress().setCity(cityDao.getById(cityId)
                .orElseThrow(() -> new NotFoundException(String.format("City id=%s not found", cityId))));

        customer.setStore(storeDao.getById(storeId)
                .orElseThrow(() -> new NotFoundException(String.format("Store id=%s not found", storeId))));

        Customer saved = customerDao.save(customer);

        log.info("customer id={} and address id={} have been successfully saved, field store id={}", saved.getId(),
                saved.getAddress().getId(), saved.getStore().getId());

        return saved;
    }
}
