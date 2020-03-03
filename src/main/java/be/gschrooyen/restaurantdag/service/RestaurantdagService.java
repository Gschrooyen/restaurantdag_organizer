package be.gschrooyen.restaurantdag.service;

import be.gschrooyen.restaurantdag.model.Gerecht;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.GerechtDto;
import be.gschrooyen.restaurantdag.repositories.RestaurantdagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantdagService {
    private final RestaurantdagRepository repository;

    public RestaurantdagService(RestaurantdagRepository repository) {
        this.repository = repository;
    }

    public Restaurantdag createRestaurantdag(String naam, LocalDate datum, List<GerechtDto> gerechten){
        List<Gerecht> ger = gerechten.stream().map(g -> {
            return new Gerecht(g.getNaam(), g.getPrijs(), g.isKindergerecht());
        }).collect(Collectors.toList());
        return repository.save(new Restaurantdag(naam, datum, ger));
    }
}
