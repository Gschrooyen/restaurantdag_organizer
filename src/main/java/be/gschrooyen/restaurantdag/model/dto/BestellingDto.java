package be.gschrooyen.restaurantdag.model.dto;

public class BestellingDto {

    private String gerechtNaam;
    private int aantal;

    public BestellingDto() {
    }

    public BestellingDto(String gerechtNaam, int aantal) {
        this.gerechtNaam = gerechtNaam;
        this.aantal = aantal;
    }
}
