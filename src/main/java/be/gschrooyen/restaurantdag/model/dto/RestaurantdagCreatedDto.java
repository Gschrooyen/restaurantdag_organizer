package be.gschrooyen.restaurantdag.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RestaurantdagCreatedDto {
    private long id;
    private String naam;
    private LocalDateTime epochDate;

    public RestaurantdagCreatedDto(long id, String naam, LocalDateTime datum) {
        this.id = id;
        this.naam = naam;
        this.epochDate = datum;
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

    public LocalDateTime getEpochDate() {
        return epochDate;
    }

    public void setEpochDate(LocalDateTime epochDate) {
        this.epochDate = epochDate;
    }
}
