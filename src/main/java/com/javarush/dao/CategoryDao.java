package com.javarush.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;

import com.javarush.entity.Category;

import java.util.*;

public class CategoryDao extends AbstractDao<Category> {

    public CategoryDao() {
        super(Category.class);
    }

    public List<Category> getCategoriesByNames(String... names) {
        try (Session session = getCurrentSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
            Root<Category> categoryRoot = criteriaQuery.from(Category.class);

            List<Predicate> predicates = new ArrayList<>();

            Arrays.stream(names)
                            .forEach(name -> predicates.add(criteriaBuilder.equal(criteriaBuilder
                                        .upper(categoryRoot.get("name")), name.toUpperCase())));

            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
