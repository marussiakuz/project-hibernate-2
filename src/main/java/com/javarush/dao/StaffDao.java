package com.javarush.dao;

import org.hibernate.Session;
import com.javarush.entity.Staff;
import com.javarush.entity.Store;

public class StaffDao extends AbstractDao<Staff> {

    public StaffDao() {
        super(Staff.class);
    }

    public Staff getManagerByStoreId(final byte id) {
        try (Session session = getCurrentSession()) {
            Store store = session.load(Store.class, id);
            return store.getManager();
        }
    }
}
