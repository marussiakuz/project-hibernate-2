package com.javarush.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.javarush.entity.FilmShort;
import com.javarush.entity.Inventory;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
public class InventoryDao extends AbstractDao<Inventory> {

    public InventoryDao() {
        super(Inventory.class);
    }

    @Override
    public Inventory save(Inventory inventory) {
        boolean isFilmNew = isNull(inventory.getFilm().getId());

        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(inventory);
            if (isFilmNew) saveFilmShort(inventory, session);
            transaction.commit();
        }
        return inventory;
    }

    public List<Inventory> findAnyAvailableByFilmTitleAndStoreId(byte storeId, int pageNumber, int pageSize) {
        try (Session session = getCurrentSession()) {
            Query<Inventory> query = session.createQuery("from Inventory i where i.store.id = :storeId and i.id " +
                    "not in (select r.inventory.id from Rental r where r.returnDate is null)", Inventory.class);
            return query
                    .setParameter("storeId", storeId)
                    .setFirstResult(pageNumber * pageSize )
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    private void saveFilmShort(Inventory inventory, Session session) {
        FilmShort filmShort = FilmShort.builder()
                .id(inventory.getFilm().getId())
                .title(inventory.getFilm().getTitle())
                .description(inventory.getFilm().getDescription())
                .build();
        session.saveOrUpdate(filmShort);
        log.debug("new film text saved: {}", filmShort.toString());
    }
}
