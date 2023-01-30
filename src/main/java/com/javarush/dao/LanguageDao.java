package com.javarush.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.javarush.entity.Language;

import java.util.Optional;

public class LanguageDao extends AbstractDao<Language> {

    public LanguageDao() {
        super(Language.class);
    }

    public Optional<Language> getLanguageByName(String name) {
        try (Session session = getCurrentSession()) {
            Query<Language> query = session.createQuery("from Language l where lower(l.name) like " +
                    "concat('%', :name, '%')", Language.class);
            query
                    .setParameter("name", name)
                    .setMaxResults(1);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
