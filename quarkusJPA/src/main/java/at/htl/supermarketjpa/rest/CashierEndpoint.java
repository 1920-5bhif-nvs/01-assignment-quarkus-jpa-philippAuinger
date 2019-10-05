package at.htl.supermarketjpa.rest;

import at.htl.supermarketjpa.model.Cashier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cashier")
public class CashierEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getCashiers(){
        TypedQuery<Cashier> query = em.createNamedQuery("Cashier.getAll", Cashier.class);
        List<Cashier> cashiers = query.getResultList();
        return Response.ok().entity(cashiers).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postCashier(Cashier cashier){
        em.persist(cashier);
        em.flush();
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteCashier(@PathParam("id") long id){
        Cashier c = em.find(Cashier.class, id);
        if(c != null){
            c = em.merge(c);
            em.remove(c);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
