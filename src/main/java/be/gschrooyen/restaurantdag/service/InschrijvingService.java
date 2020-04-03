package be.gschrooyen.restaurantdag.service;

import be.gschrooyen.restaurantdag.model.Bestelling;
import be.gschrooyen.restaurantdag.model.Inschrijving;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.BestellingDto;
import be.gschrooyen.restaurantdag.repositories.BestellingRepository;
import be.gschrooyen.restaurantdag.repositories.GerechtRepository;
import be.gschrooyen.restaurantdag.repositories.InschrijvingRepository;
import be.gschrooyen.restaurantdag.repositories.RestaurantdagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InschrijvingService {

    private final RestaurantdagRepository restaurantdagRepository;
    private final GerechtRepository gerechtRepository;
    private final InschrijvingRepository inschrijvingRepository;
    private final BestellingRepository bestellingRepository;

    public InschrijvingService(RestaurantdagRepository restaurantdagRepository, GerechtRepository gerechtRepository, InschrijvingRepository inschrijvingRepository, BestellingRepository bestellingRepository) {
        this.restaurantdagRepository = restaurantdagRepository;
        this.gerechtRepository = gerechtRepository;
        this.inschrijvingRepository = inschrijvingRepository;
        this.bestellingRepository = bestellingRepository;
    }

    @Transactional
    public Inschrijving nieuw(String naam, String groep, String tijdstip, Collection<BestellingDto> bestelling, long restaurantdagId) {
        Restaurantdag r = restaurantdagRepository.getOne(restaurantdagId);
        Inschrijving i = new Inschrijving(naam, groep, new ArrayList<Bestelling>(), r.getDatum().withHour(Integer.parseInt(tijdstip.substring(0, 2))).withMinute(Integer.parseInt(tijdstip.substring(3))), r);
        List<Bestelling> bestellingen = bestelling.stream().map(bestellingDto -> new Bestelling(gerechtRepository.getOne(bestellingDto.getGerechtId()), bestellingDto.getAantal())).collect(Collectors.toList());
        i.setBestellingen(bestellingen);
        r.getInschrijvingen().add(i);
        i.setRestaurantdag(r);
        for (Bestelling bestelling1 : bestellingen) {
            bestellingRepository.save(bestelling1);
        }
        Inschrijving insch = inschrijvingRepository.save(i);
        restaurantdagRepository.save(r);
        return insch;
    }
}
