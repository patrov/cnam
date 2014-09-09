/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

<<<<<<< HEAD
import com.cnam.opennote.dao.IndexationDAO;
=======
>>>>>>> 1af79a63c27a6f66289fc6962e2df76384fa74da
import com.cnam.opennote.domain.Book;
import com.cnam.opennote.domain.Content;
import com.cnam.opennote.domain.Indexation;
import java.util.List;
<<<<<<< HEAD
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
=======
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
>>>>>>> 1af79a63c27a6f66289fc6962e2df76384fa74da

/**
 *
 * @author Administrateur
 */
@Stateless
public class IndexationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //handle all contents services
<<<<<<< HEAD
    @PersistenceContext(unitName = "opennotePU")
    EntityManager em;
    @EJB
    IndexationDAO indexationDAO;
=======
    @Resource(mappedName = "jms/IndexationFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jsm/indexation")
    private Destination queue;
>>>>>>> 1af79a63c27a6f66289fc6962e2df76384fa74da

    public void handleContentIndexes(Content content) {
        System.out.println("Content content");
    }

<<<<<<< HEAD
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





=======
    private void sendIndexationMessage() {
        try {
            /* Create Session */
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            ObjectMessage message = session.createObjectMessage();
            message.setObject("radical blaze let me tell you this nigga");
            messageProducer.send(message);
            messageProducer.close();
            connection.close();
        } catch (JMSException e) {
        }

    }

    public void deleteContentIndexes() {
    }

    void updateContentIndexation(Content content) {
    }
    /*List<Content>*/

    void findContents(String criteria) {
    }

    void findSimilarContents(Content content) {
    }

    void upsertBookIndex(Book book) {
>>>>>>> 1af79a63c27a6f66289fc6962e2df76384fa74da
    }

    void deleteIndexes(List<Indexation> indexes) {
    }

<<<<<<< HEAD
    private void deleteBookIndexes(Book book) {
        indexationDAO.deleteBookIndexes(book);
=======
    void deleteBookIndexes(Book book) {
>>>>>>> 1af79a63c27a6f66289fc6962e2df76384fa74da
    }
}
