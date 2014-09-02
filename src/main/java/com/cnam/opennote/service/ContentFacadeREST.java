/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.domain.Content;
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
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Administrateur
 */
@Stateless
@Path("contents")
public class ContentFacadeREST extends AbstractFacade<Content> {

    @PersistenceContext(unitName = "opennotePU")
    private EntityManager em;

    public ContentFacadeREST() {
        super(Content.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Content entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Content entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Content find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    /*OK 
     */
    @GET
    @Path("/find/{id}")
    @Produces({"application/json"})
    public Response findJson(@PathParam("id") Integer id) {
        Response response = Response.ok().build(); 
        try {
            Content content = super.find(id);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content.getData());
            json.put("uid",content.getUid());
            response = response.ok(json.toJSONString()).build();
        } catch (Exception e) {
        }
        return response;
    }

    /* @PUT
     @Path("create")
     @Produce({"application/json"})
     @Consumes({"application/json"})
     public Response createJson(@)
     public Response.ok("{status:'ok'}").build();*/
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Content> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Content> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
