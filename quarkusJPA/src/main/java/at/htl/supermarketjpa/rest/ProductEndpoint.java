package at.htl.supermarketjpa.rest;


import at.htl.supermarketjpa.business.ProductDAO;
import at.htl.supermarketjpa.model.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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

   /* @GET
    @Path("/getByBrand/{brand}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getByBrand(@PathParam("brand") String brand){
        List<Product> list = productDAO.getProduct(brand);
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
        Product product = productDAO.get(id);
        if(product != null)
            return Response.ok().entity(product).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(Product product){
        System.out.println(product.getBrand());
        productDAO.save(product);
        return Response.created(URI.create("http://localhost:8085/supermarket/rest/product/" + product.getId())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") long id){
        Product entity = productDAO.get(id);
        if(entity != null){
            productDAO.remove(entity);
        }
        return Response.noContent().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Product product){
        product = productDAO.update(product);
        return Response.ok().entity(product).build();
    }*/
}