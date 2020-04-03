package be.gschrooyen.restaurantdag.model;

import javax.persistence.*;

@Entity
@Table
public class Bestelling {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Gerecht gerecht;
    private int aantal;

    public Bestelling() {
    }

    public Bestelling(Gerecht gerecht, int aantal) {
        this.gerecht = gerecht;
        this.aantal = aantal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
