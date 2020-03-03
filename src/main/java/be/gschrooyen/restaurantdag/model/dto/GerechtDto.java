package be.gschrooyen.restaurantdag.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GerechtDto {
    @NotNull
    private String naam;
    @NotNull
    @Min(0)
    private double prijs;
    @NotNull
    private boolean isKindergerecht;

    public GerechtDto() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public boolean isKindergerecht() {
        return isKindergerecht;
    }

    public void setKindergerecht(boolean kindergerecht) {
        this.isKindergerecht = kindergerecht;
    }
}
