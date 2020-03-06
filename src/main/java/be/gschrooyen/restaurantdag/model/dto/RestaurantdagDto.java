package be.gschrooyen.restaurantdag.model.dto;

import be.gschrooyen.restaurantdag.model.Gerecht;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RestaurantdagDto {

    private String naam;
    private LocalDateTime epochDate;
    private List<GerechtDto> gerechten;

    public RestaurantdagDto() {
    }

    public RestaurantdagDto(String naam, LocalDateTime epochDate, List<GerechtDto> gerechten) {
        this.naam = naam;
        this.epochDate = epochDate;
        this.gerechten = gerechten;
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
}
