package be.gschrooyen.restaurantdag.service;

import be.gschrooyen.restaurantdag.model.Dessert;
import be.gschrooyen.restaurantdag.model.Gerecht;
import be.gschrooyen.restaurantdag.model.HoofdGerecht;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.GerechtDto;
import be.gschrooyen.restaurantdag.repositories.GerechtRepository;
import be.gschrooyen.restaurantdag.repositories.RestaurantdagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestaurantdagService {
    private final RestaurantdagRepository repository;
    private final GerechtRepository gerechtRepository;

    public RestaurantdagService(RestaurantdagRepository repository, GerechtRepository gerechtRepository) {
        this.repository = repository;
        this.gerechtRepository = gerechtRepository;
    }

    public Restaurantdag createRestaurantdag(String naam, LocalDateTime datum, List<GerechtDto> gerechten) {
        Restaurantdag resto = new Restaurantdag(naam, datum);
        List<Gerecht> ger = gerechten.stream().map(g -> {
            if (g.getType().equals("dessert")) {
                return new Dessert(g.getNaam(), resto);
            }
            return new HoofdGerecht(g.getNaam(), resto, g.getPrijs(), g.isKindergerecht());
        }).collect(Collectors.toList());
        resto.setGerechten(ger);
        Restaurantdag restaurantdag = repository.save(resto);
        gerechtRepository.saveAll(ger);
        return restaurantdag;
    }

    public Restaurantdag getNext() {
        return repository.getFirstByDatumAfterOrderByDatumDesc(LocalDateTime.now().minusMonths(1));
    }

    public List<Restaurantdag> getAll() {
        return repository.findAll();
    }

    public Restaurantdag getRestaurantdag(long id) {
        return repository.getOne(id);
    }

    public Restaurantdag getById(long id) {
        return repository.getOne(id);
    }

    public Restaurantdag update(String naam, LocalDateTime epochDate, List<GerechtDto> gerechten, long id) {
        Restaurantdag restaurantdag = repository.getOne(id);
        restaurantdag.setNaam(naam);
        restaurantdag.setDatum(epochDate);
        List<Long> updatedIds = gerechten.stream().map(GerechtDto::getId).filter(Objects::nonNull).collect(Collectors.toList());
        try {
            restaurantdag.getGerechten().stream().map(Gerecht::getId).filter(aLong -> !updatedIds.contains(aLong)).forEach(aLong -> gerechtRepository.delete(gerechtRepository.getOne(aLong)));
        }
        catch (ConcurrentModificationException ignored) {}
        restaurantdag.setGerechten(
                gerechten.stream().map(g -> {
                    if (g.getId() == null) {
                        if (g.getType().equals("dessert")) {
                            return new Dessert(g.getNaam(), restaurantdag);
                        }
                        return new HoofdGerecht(g.getNaam(), restaurantdag, g.getPrijs(), g.isKindergerecht());
                    } else {
                        Gerecht gerecht = gerechtRepository.getOne(g.getId());
                        if (gerecht instanceof HoofdGerecht){
                            gerecht.setNaam(g.getNaam());
                            ((HoofdGerecht) gerecht).setPrijs(g.getPrijs());
                            ((HoofdGerecht) gerecht).setKinderGerecht(g.isKindergerecht());
                        }else if (gerecht instanceof Dessert) {
                            gerecht.setNaam(g.getNaam());
                        }
                        return gerecht;
                    }
                }).collect(Collectors.toList())
        );
        return repository.save(restaurantdag);
    }

    public void delete(long id) {
        List<Gerecht> gerechts = gerechtRepository.getAllByRestaurantdag(repository.getOne(id));
        gerechtRepository.deleteAll(gerechts);
        repository.delete(repository.getOne(id));
    }
}
