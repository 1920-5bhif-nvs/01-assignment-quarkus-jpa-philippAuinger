package at.htl.supermarketjpa.rest;


import at.htl.supermarketjpa.model.Product;
import at.htl.supermarketjpa.model.Storage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("storage")
public class StorageEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAll(){
        TypedQuery<Storage> query = em.createNamedQuery("Storage.getAll", Storage.class);
        List<Storage> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{Id}")
    @Transactional
    public Response getById(@PathParam("Id") long id){
        TypedQuery<Storage> query = em.createNamedQuery("Storage.getById", Storage.class);
        query.setParameter("id",id);
        List<Storage> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{Id}/getProducts")
    @Transactional
    public Response getProducts(@PathParam("Id") long id){
        TypedQuery<Product> query = em.createNamedQuery("Product." +
                "", Product.class);
        query.setParameter("id" , id);
        List<Product> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

}
