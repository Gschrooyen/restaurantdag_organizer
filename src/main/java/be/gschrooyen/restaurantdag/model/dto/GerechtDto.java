package be.gschrooyen.restaurantdag.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GerechtDto {

    private Long id;
    private String naam;
    private Double prijs;
    private Boolean isKindergerecht;
    private String type;

    public GerechtDto() {
    }

    public GerechtDto(String naam, Double prijs, Boolean isKindergerecht, String type, Long id) {
        this.id = id;
        this.naam = naam;
        this.prijs = prijs;
        this.isKindergerecht = isKindergerecht;
        this.type = type;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public Boolean isKindergerecht() {
        return isKindergerecht;
    }

    public void setKindergerecht(Boolean kindergerecht) {
        this.isKindergerecht = kindergerecht;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
