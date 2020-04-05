package be.gschrooyen.restaurantdag.model.dto;

public class InschrijvingDto {

    private long id;
    private String naam;
    private String groep;
    private double volwassenen;
    private double kinderen;
    private String tijdstip;

    public InschrijvingDto() {
    }

    public InschrijvingDto(long id, String naam, String groep, String tijdstip, double personen, double kinderen) {
        this.id = id;
        this.naam = naam;
        this.groep = groep;
        this.tijdstip = tijdstip;
        this.volwassenen = personen;
        this.kinderen = kinderen;
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

    public double getVolwassenen() {
        return volwassenen;
    }

    public void setVolwassenen(double volwassenen) {
        this.volwassenen = volwassenen;
    }

    public double getKinderen() {
        return kinderen;
    }

    public void setKinderen(double kinderen) {
        this.kinderen = kinderen;
    }
}
