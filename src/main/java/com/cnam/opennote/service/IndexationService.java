/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.domain.Book;
import com.cnam.opennote.domain.Content;
import com.cnam.opennote.domain.Indexation;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 *
 * @author Administrateur
 */
@Stateless
public class IndexationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //handle all contents services
    @Resource(mappedName = "jms/IndexationFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jsm/indexation")
    private Destination queue;

    public void handleContentIndexes(Content content) {
        System.out.println("Content content");
    }

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
    }

    void deleteIndexes(List<Indexation> indexes) {
    }

    void deleteBookIndexes(Book book) {
    }
}
