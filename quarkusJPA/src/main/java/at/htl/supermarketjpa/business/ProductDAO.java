package at.htl.supermarketjpa.business;

import at.htl.supermarketjpa.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAO {

    @PersistenceContext
    EntityManager em;

    public List<Product> getProducts(){
        TypedQuery<Product> query = em.createNamedQuery("Product.getAll", Product.class);
        return query.getResultList();
    }

    public List<Product> getProduct(String brand){
        TypedQuery<Product> query = em.createNamedQuery("Product.getByBrand", Product.class);
        query.setParameter("brand",brand);
        return query.getResultList();
    }

    public Product get(long id){
        Product product = em.find(Product.class, id);
        return product;
    }

    public void remove(Product entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }

    public void save(Product entity) {
        em.persist(entity);
        em.flush();
    }

    public Product update(Product entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
