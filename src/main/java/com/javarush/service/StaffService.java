package com.javarush.service;

import com.javarush.dao.StaffDao;
import com.javarush.entity.Staff;

public class StaffService {
    private final StaffDao staffDao;

    public StaffService() {
        this.staffDao = new StaffDao();
    }

    public Staff getManagerByStoreId(byte storeId) {
        return staffDao.getManagerByStoreId(storeId);
    }
}
