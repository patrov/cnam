/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.domain.Book;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Administrateur
 */
@Stateless
@Path("books")
public class BookService extends AbstractFacade<Book> {
    @PersistenceContext(unitName = "opennotePU")
    private EntityManager em;

    public BookService() {
        super(Book.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Book entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Book entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        /*only owner can delete content */
        super.remove(super.find(id));
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Book> findAll() {
        //return super.findAll();
        List<Book> BookList = new ArrayList<Book>(); 
        return BookList;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }  
}
