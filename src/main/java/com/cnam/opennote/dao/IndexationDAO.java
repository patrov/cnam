/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.dao;

import com.cnam.opennote.domain.Book;
import com.cnam.opennote.domain.Content;
import com.cnam.opennote.domain.Indexation;
import java.util.Collection;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
@Singleton
public class IndexationDAO {

    @PersistenceContext(unitName = "opennotePU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Indexation find(int id) {
        return em.find(Indexation.class, id);
    }

    public void deleteBookIndexes(Book book) {
        this.deleteContentIndexes(book.getContent());
    }

    public void deleteContentIndexes(Content content) {
        Query query = em.createQuery("delete from Indexation i where content = :content");
        query.setParameter("content", content);
        query.executeUpdate();
    }

    public void create(Indexation index) {
        em.persist(index);
    }

    public void create(Collection<Indexation> indexes) {
        if (indexes.size() > 0) {
            for (Indexation index : indexes) {
                em.persist(index);
            }
        }
        em.flush();
    }
}
