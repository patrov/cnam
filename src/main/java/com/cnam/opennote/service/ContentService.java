/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.domain.Content;
import java.util.Date;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author h.baptiste
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

    @POST
    @Path("create")
    @Produces({"application/json"})
    public Response createJson(@FormParam("params") String jsonContent) {
        Response response = Response.ok().build();
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonContent);
            /* create */
            Content content = new Content();
            content.setModel((String) obj.get("model"));
            content.setOwnerUid(2);
            content.setCreatedAt(new Date());
            content.setUpdatedAt(new Date());
            JSONObject data = (JSONObject) obj.get("data");
            content.setData(data.toJSONString());
            super.create(content);
            obj.put("uid", content.getUid());
            /*handle indexation here*/
            response = Response.ok(obj.toJSONString()).build();
        } catch (Exception e) {
            response = Response.ok(e.getMessage()).build();
        }
        return response;
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
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

    /* OK */
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
