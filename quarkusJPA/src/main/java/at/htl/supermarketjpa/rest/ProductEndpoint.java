package at.htl.supermarketjpa.rest;


import at.htl.supermarketjpa.model.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;


@Path("product")
public class ProductEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getProducts(){
        return Response.ok(em.createNamedQuery("Product.getAll", Product.class).getResultList()).build();
    }

    @GET
    @Path("/getByBrand/{brand}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getByBrand(@PathParam("brand") String brand){
        TypedQuery<Product> query = em.createNamedQuery("Product.getByBrand", Product.class);
        query.setParameter("brand",brand);
        List<Product> list = query.getResultList();
        if(list != null)
            return Response.ok().entity(list).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getProduct(@PathParam("id") long id) {
        Product product = em.find(Product.class, id);
        if(product != null)
            return Response.ok().entity(product).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    /*
    {
        "best_before_date":"2019-12-19",
        "brand":"Lecker",
        "name":"Apfel",
        "price":1.00,
        "quantity":10,
        "storage":{"id":2}
     }
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postProduct(Product product){
        em.persist(product);
        em.flush();
        return Response.created(URI.create("http://localhost:8080/api/product/" + product.getId())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteProduct(@PathParam("id") long id){
        Product entity = em.find(Product.class, id);
        if(entity != null){
            entity = em.merge(entity);
            em.remove(entity);
        }
        return Response.noContent().build();
    }

    /*
    {
        "best_before_date":"2019-12-24",
        "brand":"Neuer",
        "name":"Apfel",
        "id":2,
        "price":20.00,
        "quantity":11,
        "storage":{"id":2}
    }
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Product product){
        product = em.merge(product);
        em.flush();
        em.refresh(product);
        return Response.ok().entity(product).build();
    }
}