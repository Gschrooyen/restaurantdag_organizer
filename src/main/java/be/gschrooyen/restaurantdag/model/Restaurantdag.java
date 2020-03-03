package be.gschrooyen.restaurantdag.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Restaurantdag {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String naam;
    @NotNull
    private LocalDate datum;
    @OneToMany
    private List<Inschrijving> inschrijvingen;
    @OneToMany
    private List<Gerecht> gerechten;

    public Restaurantdag() {
    }

    public Restaurantdag(String naam, LocalDate datum, List<Gerecht> ger) {
        this.naam = naam;
        this.datum = datum;
        this.gerechten = ger;
    }

}
