package be.gschrooyen.restaurantdag.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Entity
public class Inschrijving {

    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @NonNull
    private String naam;
    private String groep;
    @OneToMany
    private List<Bestelling> bestellingen;
    @NotNull
    @NonNull
    private LocalDateTime tijdstip;
    @ManyToOne
    private Restaurantdag restaurantdag;

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

    public String getGroep() {
        return groep;
    }

    public void setGroep(String groep) {
        this.groep = groep;
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }

    public LocalDateTime getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(LocalDateTime tijdstip) {
        this.tijdstip = tijdstip;
    }

    public Restaurantdag getRestaurantdag() {
        return restaurantdag;
    }

    public void setRestaurantdag(Restaurantdag restaurantdag) {
        this.restaurantdag = restaurantdag;
    }
}
