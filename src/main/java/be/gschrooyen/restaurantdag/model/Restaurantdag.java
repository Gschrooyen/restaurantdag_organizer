package be.gschrooyen.restaurantdag.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Restaurantdag {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String naam;
    @NotNull
    private LocalDateTime datum;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Inschrijving> inschrijvingen;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Gerecht> gerechten;

    public Restaurantdag() {
    }

    public Restaurantdag(String naam, LocalDateTime datum) {
        this.naam = naam;
        this.datum = datum;
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

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingen;
    }

    public void setInschrijvingen(List<Inschrijving> inschrijvingen) {
        this.inschrijvingen = inschrijvingen;
    }

    public List<Gerecht> getGerechten() {
        return gerechten;
    }

    public void setGerechten(List<Gerecht> gerechten) {
        this.gerechten = gerechten;
    }

    @Override
    public String toString() {
        return "Restaurantdag{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", datum=" + datum +
                ", inschrijvingen=" + inschrijvingen +
                ", gerechten=" + gerechten +
                '}';
    }
}
