package be.gschrooyen.restaurantdag.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Entity
@Table
public class Inschrijving {

    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @NonNull
    private String naam;
    private String groep;
    @OneToMany
    private List<Bestelling> bestellingen;
    @NotNull
    @NonNull
    private LocalDateTime tijdstip;
}
