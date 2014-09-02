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
    @Consumes({"application/xml", "application/json"})
    public Content find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJson(@PathParam("id") Integer id, @PathParam("callback") String callback) {
        Response response = Response.ok("{'est':'passe'}").build();
        try {
            Content content = super.find(id);
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content.getData());
            obj.put("uid", content.getUid());
            response = Response.ok(obj.toJSONString()).build();
        } catch (Exception e) {
            response = Response.ok("{error:true}").build();
        }
        return response;
    }

    /* OK */
    @GET
    @Path("findAll/{model}")
    @Produces({"application/json"})
    public Response findAllJson(@PathParam("model") String model) {
        Response response = Response.ok("{'method':'findAllJson'}").build();
        try {
            JSONArray jsonCollection = new JSONArray();
            JSONParser parser = new JSONParser();
            List<Content> contents = super.findAll();
            for (Content content : contents) {
                JSONObject obj = (JSONObject) parser.parse(content.getData());
                obj.put("uid", content.getUid());
                jsonCollection.add(obj);
            }
            response = Response.ok(jsonCollection.toJSONString()).build();
        } catch (Exception e) {
            response = Response.ok("{'error':" + e.getMessage() + "}").build();
        }
        return response;
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
        return "10";//String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
