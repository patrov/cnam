/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.dao.IndexationDAO;
import com.cnam.opennote.domain.Book;
import com.cnam.opennote.domain.Content;
import com.cnam.opennote.domain.Indexation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Stateless
public class IndexationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //handle all contents services
    @EJB
    IndexationDAO indexationDAO;

    /**
     * Mettre à jour les contenus associés au contenu.
     *
     * @param content un contenu dont les données doivent être indexées
     *
     */
    public void handleContentIndexes(Content content) {
        try {
            this.deleteContentIndexes(content);
            this.handleIndexation(content);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * @param book
     */
    public void upsertBookIndexes(Book book) {
        try {
            this.deleteBookIndexes(book);
            Content content = book.getContent();
            this.handleIndexation(content);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void deleteContentIndexes(Content content) {
        try {
            indexationDAO.deleteContentIndexes(content);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    /*handle Content indexation*/
    private void handleIndexation(Content content) {
        try {
            Collection<Indexation> indexationsList = new ArrayList<Indexation>();
            /*delete previous index Indexation*/
            JSONParser jsonParser = new JSONParser();
            Indexation index;
            JSONObject contentJson = (JSONObject) jsonParser.parse(content.getData());
            JSONArray indexInfos = (JSONArray) contentJson.get("__indexation__");
            if (indexInfos == null) {
                return;
            }
            String container = null;
            Boolean hasContainer = false;
            if(indexInfos.contains("container")){
                hasContainer = true; 
            }
            
            for (int i = 0; i < indexInfos.size(); i++) {
                String fieldName = (String) indexInfos.get(i);
                String[] fieldInfos = fieldName.split(":");
                String contentType = (String) contentJson.get("__entity__");
                if(hasContainer){
                   container =  (String) contentJson.get("container");
                }
                if (fieldInfos.length < 2) {
                    /*We assume the fieldvalue is a String*/
                    String fieldname = fieldInfos[0];
                    String fieldValue = (String) contentJson.get(fieldname);
                    index = new Indexation(fieldName, fieldValue, contentType);
                    index.setContent(content);
                    index.setContainerUid(container);
                    indexationsList.add(index);
                } else {
                    String fieldname = fieldInfos[0];
                    String indexType = fieldInfos[1];
                    /* comma separated value */
                    if (indexType.equals("csv")) {
                        String valueCol = (String) contentJson.get(fieldname);
                        String[] valueList = valueCol.split(",");
                        if (valueList.length > 0) {
                            int c;
                            for (c = 0; c < valueList.length; c++) {
                                index = new Indexation(fieldname, valueList[c], contentType);
                                index.setContent(content);
                                index.setContainerUid(container);
                                indexationsList.add(index);
                            }
                        }
                    }
                }
            }
            indexationDAO.create(indexationsList);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
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

    private void deleteBookIndexes(Book book) {
        indexationDAO.deleteBookIndexes(book);
    }
}
