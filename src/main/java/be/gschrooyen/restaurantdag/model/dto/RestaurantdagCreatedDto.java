package be.gschrooyen.restaurantdag.model.dto;

import java.time.LocalDate;

public class RestaurantdagCreatedDto {
    private long id;
    private String naam;
    private long epochDate;

    public RestaurantdagCreatedDto(long id, String naam, LocalDate datum) {
        this.id = id;
        this.naam = naam;
        this.setEpochDate(datum);
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

    public long getEpochDate() {
        return epochDate;
    }

    public void setEpochDate(LocalDate epochDate) {
        this.epochDate = epochDate.toEpochDay();
    }
}
