package com.javarush.service;

import com.javarush.dao.CategoryDao;
import com.javarush.entity.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public List<Category> findAllCategoriesWithNames(String... names) {
        return categoryDao.getCategoriesByNames(names);
    }
}
