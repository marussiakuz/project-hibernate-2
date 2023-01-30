package com.javarush.service;

import com.javarush.dao.StoreDao;
import com.javarush.entity.Store;
import com.javarush.exception.NotFoundException;

public class StoreService {
    private final StoreDao storeDao;

    public StoreService() {
        this.storeDao = new StoreDao();
    }

    public Store getStoreById(byte storeId) {
        return storeDao.getById(storeId)
                .orElseThrow(() -> new NotFoundException(String.format("store with id=%s not found", storeId)));
    }
}
