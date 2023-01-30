package com.javarush.dao;

import com.javarush.entity.City;

public class CityDao extends AbstractDao<City> {

    public CityDao() {
        super(City.class);
    }
}
