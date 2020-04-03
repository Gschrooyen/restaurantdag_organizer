package be.gschrooyen.restaurantdag.model.dto;

public class RestaurantdagOverzichtDto {

    private long id;
    private String naam;
    private String datum;
    private int inschrijvingen;
    private double personen;

    public RestaurantdagOverzichtDto() {
    }

    public RestaurantdagOverzichtDto(String naam, String datum, int inschrijvingen, double personen, long id) {
        this.naam = naam;
        this.datum = datum;
        this.inschrijvingen = inschrijvingen;
        this.personen = personen;
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getInschrijvingen() {
        return inschrijvingen;
    }

    public void setInschrijvingen(int inschrijvingen) {
        this.inschrijvingen = inschrijvingen;
    }

    public double getPersonen() {
        return personen;
    }

    public void setPersonen(double personen) {
        this.personen = personen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
