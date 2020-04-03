package be.gschrooyen.restaurantdag.model.dto;

public class BestellingDto {

    private long gerechtId;
    private String gerechtNaam;
    private int aantal;
    private double prijs;

    public BestellingDto() {
    }

    public BestellingDto(String gerechtNaam, int aantal, long gerechtId, double prijs) {
        this.gerechtId = gerechtId;
        this.gerechtNaam = gerechtNaam;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public long getGerechtId() {
        return gerechtId;
    }

    public void setGerechtId(long gerechtId) {
        this.gerechtId = gerechtId;
    }

    public String getGerechtNaam() {
        return gerechtNaam;
    }

    public void setGerechtNaam(String gerechtNaam) {
        this.gerechtNaam = gerechtNaam;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
