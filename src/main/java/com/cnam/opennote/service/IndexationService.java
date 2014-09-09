/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.dao.IndexationDAO;
import com.cnam.opennote.domain.Book;
import com.cnam.opennote.domain.Content;
import com.cnam.opennote.domain.Indexation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Administrateur
 */
@Stateless
public class IndexationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //handle all contents services
    @PersistenceContext(unitName = "opennotePU")
    EntityManager em;
    @EJB
    IndexationDAO indexationDAO;

    public void handleContentIndexes(Content content) {
        System.out.println("Content content");
    }

    private void deleteContentIndexes() {
    }

    public void updateContentIndexation(Content content) {
    }
    /*List<Content>*/

    public void findContents(String criteria) {
    }

    public void findSimilarContents(Content content) {
    }

    public void upsertBookIndexes(Book book) {
        try {
            this.deleteBookIndexes(book);
            Content content = book.getContent();
            JSONParser parser = new JSONParser();
            System.out.println(content.getData());
            JSONObject obj = (JSONObject) parser.parse(content.getData());
            JSONArray indexation = (JSONArray)obj.get("__indexation__"); 
            System.out.println(indexation);
            /* chaque champs dans */
        } catch (Exception e) {
            
        }





    }

    void deleteIndexes(List<Indexation> indexes) {
    }

    private void deleteBookIndexes(Book book) {
        indexationDAO.deleteBookIndexes(book);
    }
}
