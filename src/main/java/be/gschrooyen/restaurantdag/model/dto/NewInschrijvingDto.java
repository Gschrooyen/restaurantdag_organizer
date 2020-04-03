package be.gschrooyen.restaurantdag.model.dto;

import java.util.List;

public class NewInschrijvingDto {

    private String naam;
    private String groep;
    private String tijdstip;
    private List<BestellingDto> bestelling;

    public NewInschrijvingDto() {
    }

    public NewInschrijvingDto(String naam, String groep, String tijdstip, List<BestellingDto> bestelling) {
        this.naam = naam;
        this.groep = groep;
        this.tijdstip = tijdstip;
        this.bestelling = bestelling;
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

    public List<BestellingDto> getBestelling() {
        return bestelling;
    }

    public void setBestelling(List<BestellingDto> bestelling) {
        this.bestelling = bestelling;
    }
}
