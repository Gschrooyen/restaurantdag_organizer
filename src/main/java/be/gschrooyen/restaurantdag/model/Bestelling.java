package be.gschrooyen.restaurantdag.model;

import javax.persistence.*;

@Entity
@Table
public class Bestelling {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Inschrijving inschrijving;
    @ManyToOne
    private Gerecht gerecht;
    private int aantal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inschrijving getInschrijving() {
        return inschrijving;
    }

    public void setInschrijving(Inschrijving inschrijving) {
        this.inschrijving = inschrijving;
    }

    public Gerecht getGerecht() {
        return gerecht;
    }

    public void setGerecht(Gerecht gerecht) {
        this.gerecht = gerecht;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
