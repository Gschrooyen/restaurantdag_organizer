package be.gschrooyen.restaurantdag.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantdagDto {

    private String naam;
    private LocalDateTime epochDate;
    private List<GerechtDto> gerechten;
    private int inschrijvingen;

    public RestaurantdagDto() {
    }

    public RestaurantdagDto(String naam, LocalDateTime epochDate, List<GerechtDto> gerechten, int inschrijvingen) {
        this.naam = naam;
        this.epochDate = epochDate;
        this.gerechten = gerechten;
        this.inschrijvingen = inschrijvingen;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public LocalDateTime getEpochDate() {
        return this.epochDate;
    }

    public void setEpochDate(LocalDateTime epochDate) {
        this.epochDate = epochDate;
    }

    public List<GerechtDto> getGerechten() {
        return gerechten;
    }

    public void setGerechten(List<GerechtDto> gerechten) {
        this.gerechten = gerechten;
    }

    public int getInschrijvingen() {
        return inschrijvingen;
    }

    public void setInschrijvingen(int inschrijvingen) {
        this.inschrijvingen = inschrijvingen;
    }
}
