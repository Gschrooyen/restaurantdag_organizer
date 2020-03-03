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
}
