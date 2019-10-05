package at.htl.supermarketjpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cashier.getAll", query = "select c from Cashier c")
})
public class Cashier extends Person {
    @Id
    private Long id;

    private LocalDate begin_date;
    private double salary;


    //@OneToOne
    //private Person person;

    public Cashier() {
    }

    public Cashier(String firstname, String lastname, LocalDate birthdate, String mobilePhone, String email,
                   LocalDate begin_date, double salary) {
        super(firstname, lastname, birthdate, mobilePhone, email);
        this.begin_date = begin_date;
        this.salary = salary;
        //this.person = person;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
