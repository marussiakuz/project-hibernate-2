package com.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.javarush.util.JdbcConnectionCustom;

import java.util.Optional;
import java.util.ResourceBundle;

public class AbstractDao<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractDao(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
        final ResourceBundle bundle = ResourceBundle.getBundle("application");
        sessionFactory = new JdbcConnectionCustom(bundle.getString("db.properties")).getSessionFactory();
    }

    public Optional<T> getById(final int id) {
        try (Session session = getCurrentSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        }
    }

    public Optional<T> getById(final byte id) {
        try (Session session = getCurrentSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        }
    }

    public Optional<T> getById(final short id) {
        try (Session session = getCurrentSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        }
    }

    public T save(final T entity) {
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
        return entity;
    }

    public T update(final T entity) {
        T updated;

        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            updated = (T) session.merge(entity);
            transaction.commit();
        }

        return updated;
    }

    protected Session getCurrentSession() {
        return sessionFactory.openSession();
    }
}
