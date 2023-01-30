package com.javarush.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.javarush.entity.Rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalDao extends AbstractDao<Rental> {

    public RentalDao() {
        super(Rental.class);
    }

    public List<Rental> getAllRentalNotReturned(Byte storeId, int pageNumber, int pageSize) {
        try (Session session = getCurrentSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Rental> criteriaQuery = criteriaBuilder.createQuery(Rental.class);
            Root<Rental> rental = criteriaQuery.from(Rental.class);

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isNull(rental.get("returnDate")));

            if (storeId != null) {
                predicates.add(criteriaBuilder.equal(rental.get("inventory").get("store").get("id"), storeId));
            }

            return session
                    .createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0])))
                    .setFirstResult(pageNumber * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public Optional<Rental> findRentalByCustomerIdAndRentalIdAndStoreId(short customerId, short filmId, byte storeId) {
        try (Session session = getCurrentSession()) {
            Query<Rental> query = session.createQuery("from Rental r where r.customer.id = :customerId and " +
                    "r.inventory.film.id = :filmId and r.inventory.store.id = :storeId", Rental.class);
            query
                    .setParameter("customerId", customerId)
                    .setParameter("filmId", filmId)
                    .setParameter("storeId", storeId);

            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
