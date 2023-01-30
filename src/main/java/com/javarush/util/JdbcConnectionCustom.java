package com.javarush.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import com.javarush.entity.*;
import com.javarush.entity.enums.Rating;

import java.util.Properties;
import java.util.ResourceBundle;

public class JdbcConnectionCustom {
    private final SessionFactory sessionFactory;

    public JdbcConnectionCustom(String filename) {
        final ResourceBundle bundle = ResourceBundle.getBundle(filename);

        Properties properties = new Properties();
        properties.put(Environment.DRIVER, bundle.getString("db.driver"));
        properties.put(Environment.URL, bundle.getString("db.url"));
        properties.put(Environment.DIALECT, bundle.getString("db.dialect"));
        properties.put(Environment.HBM2DDL_AUTO, bundle.getString("db.hbm2ddl"));
        properties.put(Environment.USER, bundle.getString("db.user"));
        properties.put(Environment.PASS, bundle.getString("db.password"));

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmShort.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Rating.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
