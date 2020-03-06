package be.gschrooyen.restaurantdag.service;

import be.gschrooyen.restaurantdag.model.Dessert;
import be.gschrooyen.restaurantdag.model.Gerecht;
import be.gschrooyen.restaurantdag.model.HoofdGerecht;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.GerechtDto;
import be.gschrooyen.restaurantdag.repositories.RestaurantdagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantdagService {
    private final RestaurantdagRepository repository;

    public RestaurantdagService(RestaurantdagRepository repository) {
        this.repository = repository;
    }

    public Restaurantdag createRestaurantdag(String naam, LocalDateTime datum, List<GerechtDto> gerechten){
        Restaurantdag resto = new Restaurantdag(naam, datum);
        List<Gerecht> ger = gerechten.stream().map(g -> {
            if (g.getType().equals("dessert")){
                return new Dessert(g.getNaam(), resto);
            }
            return new HoofdGerecht(g.getNaam(), resto, g.getPrijs(), g.isKindergerecht());
        }).collect(Collectors.toList());
        resto.setGerechten(ger);
        return repository.save(resto);
    }

    public Restaurantdag getNext() {
        return repository.getFirstByDatumAfter(LocalDateTime.now().minusMonths(1));
    }
}
