/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.service;

import com.cnam.opennote.domain.Content;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @EJB
    private IndexationService indexationService;
    @PersistenceContext(unitName = "opennotePU")
   
    
    
    
    private EntityManager em;

    public ContentService() {
        super(Content.class);
    }

    @POST
    @Path("update")
    public Response update(@FormParam("data") String data) {
        Response response = Response.ok().build();
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(data);
            long uid = (Long) json.get("uid");
            Content content = super.find((int) uid);
            content.setData(data);
            super.edit(content);
            /* handle indexation here */
            
            indexationService.handleContentIndexes(content);
            response = this.getJsonResponse(content.toJson());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return response;
    }

    @POST
    @Produces({"application/json"})
    public Response createJson(@FormParam("data") String jsonContent) {
        Response response = Response.ok().build();
        try {
            System.out.println(jsonContent);
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonContent);
            /* create */
            String model = (String) obj.get("__entity__");
            Content content = new Content();
            content.setModel(model);
            content.setOwnerUid(2); //get from user service

            content.setCreatedAt(new Date());
            content.setUpdatedAt(new Date());
            content.setData(jsonContent);
            super.create(content);
            obj.put("uid", content.getUid());
            /* handle JsonResponse here */
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", content.toJson());
            jsonResponse.put("error", false);
            jsonResponse.put("status", "ok");
            /* handle indexation */
            response = Response.ok(jsonResponse.toJSONString()).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
            response = Response.ok(e.getMessage()).build();
        }
        return response;
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            super.remove(super.find(id));
            /*delete indexation too*/
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @GET
    @Path("/{id}")
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

    /* move to indexationService */
    @GET
    @Produces({"application/json"})
    @Path("subcontents")
    public Response findAllSubcontents(
            @QueryParam("container") String container,
            @QueryParam("order") String order,
            @QueryParam("start") String start,
            @QueryParam("end") String end) {
        Response response = Response.ok().build();
        try {
            List<Content> contents = super.findSubcontents(container, order);
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

    /* OK */
    @GET
    @Produces({"application/json"})
    @Path("findAll/{type}")
    public Response findAllJson(
            @PathParam("type") String type,
            @QueryParam("container") String container,
            @QueryParam("order") String order,
            @QueryParam("start") String start,
            @QueryParam("end") String end) {

        Response response = Response.ok().build();
        try {
            List<Content> contents = super.findAll(type);
            JSONArray jsonColl = new JSONArray();
            for (Content content : contents) {
                JSONObject jsonContent = content.toJson();
                jsonColl.add(jsonContent);
            }
            return this.getJsonResponse(jsonColl);
        } catch (Exception e) {
        }
        return response;
    }

    private Response getJsonResponse(Object result) {
        Response response = Response.ok().build();
        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", result);
            jsonResponse.put("status", "ok");
            jsonResponse.put("error", false);
            return Response.ok(jsonResponse.toJSONString()).build();
        } catch (Exception e) {
        
        }
        return response;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
