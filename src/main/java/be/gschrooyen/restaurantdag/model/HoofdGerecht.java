package be.gschrooyen.restaurantdag.model;

import javax.persistence.Entity;

@Entity
public class HoofdGerecht extends Gerecht {

    private double prijs;
    private boolean isKinderGerecht;

    public HoofdGerecht() {
    }

    public HoofdGerecht(String naam, Restaurantdag resto, double prijs, boolean isKinderGerecht) {
        super(naam, "hoofdgerecht", resto);
        this.prijs = prijs;
        this.isKinderGerecht = isKinderGerecht;
    }


    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public boolean isKinderGerecht() {
        return isKinderGerecht;
    }

    public void setKinderGerecht(boolean kinderGerecht) {
        isKinderGerecht = kinderGerecht;
    }
}
