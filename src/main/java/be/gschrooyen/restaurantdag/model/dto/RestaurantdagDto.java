package be.gschrooyen.restaurantdag.model.dto;

import be.gschrooyen.restaurantdag.model.Gerecht;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RestaurantdagDto {

    @NotNull
    private String naam;
    @NotNull
    @Min(0)
    private long epochDate;
    @NotEmpty
    private List<GerechtDto> gerechten;

    public RestaurantdagDto() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public LocalDate getEpochDate() {
        return LocalDate.ofEpochDay(this.epochDate);
    }

    public void setEpochDate(long epochDate) {
        this.epochDate = epochDate;
    }

    public List<GerechtDto> getGerechten() {
        return gerechten;
    }

    public void setGerechten(List<GerechtDto> gerechten) {
        this.gerechten = gerechten;
    }
}
