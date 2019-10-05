package at.htl.supermarketjpa.rest;

import at.htl.supermarketjpa.model.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("customer")
public class CustomerEndpoint {
    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.getAll", Customer.class);
        List<Customer> list = query.getResultList();
        return Response.ok().entity(list).build();
    }
    /*
    {
     "birthdate":"2000-12-13",
     "email":"philipp-email.com",
     "firstname":"Philipp",
     "lastname":"Auinger",
     "mobilePhone":"+4306804423123",
     "accession_date":"2014-01-10",
     "card_number":45352,
     "loyalty_points":940,
     "rank":"Friend"
     }
    */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postCustomer(Customer customer){
        em.persist(customer);
        em.flush();
        return Response.created(URI.create("http://localhost:8080/supermarket/api/customer/" + customer.getId())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteCustomer(@PathParam("id") long id){
        Customer c = em.find(Customer.class, id);
        if(c != null){
            c = em.merge(c);
            em.remove(c);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
