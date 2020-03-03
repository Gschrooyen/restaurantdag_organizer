package be.gschrooyen.restaurantdag.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Gerecht {

    @Id
    @GeneratedValue
    private long id;
    private String naam;
    private double prijs;
    private boolean isKinderGerecht;
    @ManyToOne
    private Restaurantdag restaurantdag;
    @ManyToMany
    private List<Bestelling> bestellingen;

    public Gerecht() {
    }

    public Gerecht(String naam, double prijs, boolean kindergerecht) {
        this.naam = naam;
        this.prijs = prijs;
        this.isKinderGerecht = kindergerecht;
    }
}
