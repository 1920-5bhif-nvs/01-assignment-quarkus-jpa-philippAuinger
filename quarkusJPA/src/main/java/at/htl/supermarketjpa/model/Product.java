package at.htl.supermarketjpa.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.getAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.getByBrand", query = "select p from Product p where p.brand = :brand"),
        @NamedQuery(name = "Product.getByStorageId", query = "select p from Product p where p.storage.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private double price;
    private LocalDate best_before_date;
    private int quantity;

    @ManyToOne()
    private Storage storage;

    public Product() {
    }

    public Product(String name, double price, LocalDate best_before_date, String brand, int quantity, Storage storage) {
        this.name = name;
        this.price = price;
        this.best_before_date = best_before_date;
        this.brand = brand;
        this.quantity = quantity;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getBest_before_date() {
        return best_before_date;
    }

    public void setBest_before_date(LocalDate best_before_date) {
        this.best_before_date = best_before_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

