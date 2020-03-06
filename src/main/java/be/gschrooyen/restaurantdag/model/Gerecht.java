package be.gschrooyen.restaurantdag.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gerecht {

    @Id
    @GeneratedValue
    private long id;
    private String naam;
    private String type;
    @ManyToOne
    private Restaurantdag restaurantdag;
    @ManyToMany
    private List<Bestelling> bestellingen;

    public Gerecht() {
    }

    public Gerecht(String naam, String type, Restaurantdag resto) {
        this.naam = naam;
        this.type = type;
        this.restaurantdag = resto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
