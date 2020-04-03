package be.gschrooyen.restaurantdag.model.dto;

import java.util.List;

public class InschrijvingDto {

    private long id;
    private String naam;
    private String groep;
    private double personen;
    private String tijdstip;

    public InschrijvingDto() {
    }

    public InschrijvingDto(long id, String naam, String groep, String tijdstip, double personen) {
        this.id = id;
        this.naam = naam;
        this.groep = groep;
        this.tijdstip = tijdstip;
        this.personen = personen;
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

    public String getGroep() {
        return groep;
    }

    public void setGroep(String groep) {
        this.groep = groep;
    }

    public String getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(String tijdstip) {
        this.tijdstip = tijdstip;
    }

    public double getPersonen() {
        return personen;
    }

    public void setPersonen(double personen) {
        this.personen = personen;
    }
}
