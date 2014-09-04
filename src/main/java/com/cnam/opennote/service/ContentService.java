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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Administrateur
 */
@Stateless
@Path("contents")
public class ContentService extends AbstractFacade<Content> {
    
    @PersistenceContext(unitName = "opennotePU")
    private EntityManager em;
    
    public ContentService() {
        super(Content.class);
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Content entity) {
        super.create(entity);
    }
    
    @POST
    @Path("update")
    public void update(@FormParam("data") String data) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(data);
            long uid = (Long) json.get("uid");
            Content content = super.find((int)uid);
            content.setData(data);
            super.edit(content);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
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
    
    @GET
    @Path("/find/{id}")
    @Produces({"application/json"})
    public Response findJson(@PathParam("id") Integer id) {
        Response response = Response.ok().build();
        try {
            Content content = super.find(id);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", content.toJson());
            jsonResponse.put("error", false);
            jsonResponse.put("status", "ok");
            response = Response.ok(jsonResponse.toJSONString()).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            response = Response.ok().build();
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
    @Produces({"application/json"})
    @Path("findAll/{type}")
    public Response findAllJson(@PathParam("type") String type) {
        Response response = Response.ok().build();
        try {
            List<Content> contents = super.findAll(type);
            JSONArray jsonColl = new JSONArray();
            for (Content content : contents) {
                JSONObject jsonContent = content.toJson();
                jsonColl.add(jsonContent);
            }
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", jsonColl);
            jsonResponse.put("status", "ok");
            jsonResponse.put("error", false);
            response = Response.ok(jsonResponse.toJSONString()).build();
        } catch (Exception e) {
        }
        return response;
        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
