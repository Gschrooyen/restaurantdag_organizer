package be.gschrooyen.restaurantdag.model.dto;

import java.util.List;

public class InschrijvingDto {

    private long id;
    private String naam;
    private String groep;
    private List<BestellingDto> bestelling;
    private String tijdstip;

    public InschrijvingDto() {
    }

    public InschrijvingDto(long id, String naam, String groep, List<BestellingDto> bestelling, String tijdstip) {
        this.id = id;
        this.naam = naam;
        this.groep = groep;
        this.bestelling = bestelling;
        this.tijdstip = tijdstip;
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

    public List<BestellingDto> getBestelling() {
        return bestelling;
    }

    public void setBestelling(List<BestellingDto> bestelling) {
        this.bestelling = bestelling;
    }

    public String getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(String tijdstip) {
        this.tijdstip = tijdstip;
    }
}
