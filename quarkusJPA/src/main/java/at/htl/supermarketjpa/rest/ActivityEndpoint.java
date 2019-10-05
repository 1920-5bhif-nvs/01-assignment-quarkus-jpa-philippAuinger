package at.htl.supermarketjpa.rest;

import at.htl.supermarketjpa.model.Activity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("activity")
public class ActivityEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getActivities(){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getAll", Activity.class);
        List<Activity> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/getByBrand/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getByBrand(@PathParam("name") String name){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getByBrand", Activity.class);
        query.setParameter("brand",name);
        List<Activity> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/getByCustomerLastname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getByCustomerLastname(@PathParam("name") String name){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getByCustomerLastname", Activity.class);
        query.setParameter("name",name);
        List<Activity> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postActivity(Activity activity){
        em.persist(activity);
        em.flush();
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteActivity(@PathParam("id") long id){
        Activity a = em.find(Activity.class, id);
        if(a != null){
            a = em.merge(a);
            em.remove(a);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
