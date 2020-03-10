package be.gschrooyen.restaurantdag.model;

import javax.persistence.Entity;

@Entity
public class Dessert extends Gerecht {

    public Dessert(String naam, Restaurantdag resto) {
        super(naam, "dessert", resto);
    }

    public Dessert() {
    }
}
